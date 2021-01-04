package com.song.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.song.entity.BaseEntity;
import com.song.entity.Course;
import com.song.mapper.CourseMapper;
import com.song.service.CourseConditionService;
import com.song.service.CoursePersonService;
import com.song.service.CourseService;
import com.song.utils.EntityVerifyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.song.utils.Constant.*;

/**
 * @author XiaoSong
 * @date 2019-12-19 16:29
 */
@Service
public class CourseServiceImpl extends BaseServiceImplAbstract implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CoursePersonService coursePersonService;
    @Resource
    private CourseConditionService courseConditionService;

    @Override
    public Map<String, Object> insert(Course course) {
        return insert(course, courseMapper);
    }

    @Override
    public Map<String, Object> update(Course course) {
        if (queryById(course.getId()) != null) {
            return update(course, courseMapper);
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
            result.put(RESULT_STATE_MSG_KEY, "修改的过程不存在");
            return result;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>(4);
        /*
        设置当前过程子元素
         */
        Course course = queryById(id);
        if (course != null) {
            if (course.getParentCourseId() != null) {
                //有父元素，重新设置直接子元素的父元素id
                QueryWrapper<Course> wrapper = new QueryWrapper();
                wrapper.lambda().eq(Course::getParentCourseId, course.getId());
                List<Course> courses = courseMapper.selectList(wrapper);
                courses.forEach(course1 -> {
                    if (Objects.isNull(course1)) {
                        course1.setParentCourseId(course.getParentCourseId());
                        update(course1);
                    }
                });
            }
            //删除过程中所有人员
            coursePersonService.deleteByCourse(course.getId());
            //删除过程条件判断集合
            courseConditionService.deleteByCourse(course.getId());
            //删除过程
            result.put(RESULT_STATE_KEY, courseMapper.delete(id));
            result.put(RESULT_STATE_MSG_KEY, "删除成功");
        } else {
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
            result.put(RESULT_STATE_MSG_KEY, "指定删除的过程不存在");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteByFlow(String flowId) {
        Map<String, Object> result = new HashMap<>(4);
        List<Course> courseList = queryByFlowId(flowId);
        courseList.forEach(course -> {
            delete(course.getId());
        });
        result.put(RESULT_STATE_MSG_KEY, "删除成功");
        return result;
    }

    @Override
    public List<Course> queryByFlowId(String flowId) {
        return courseMapper.queryByFlowId(flowId);
    }

    @Override
    public Course queryById(String id) {
        return courseMapper.selectById(id);
    }

    @Override
    public boolean isFreedom(String flowId) {
        Course course = queryFirst(flowId);
        if (!Objects.isNull(course)) {
            return Objects.isNull(course.getFreedom()) ? false : course.getFreedom();
        }
        return false;
    }

    @Override
    public Course queryFirst(String flowId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flow_id", flowId);
        queryWrapper.and(wrapper -> {
            wrapper.isNull("parent_course_id");
        });
//                eq("flow_id",flowId).isNull("parent_course_id");
        return (Course) courseMapper.selectOne(queryWrapper);
    }

    @Override
    protected Map<String, Object> verify(BaseEntity baseEntity) {
        Course course = (Course) baseEntity;
        Map<String, Object> result = new HashMap<>(4);
        result.put("name", "过程名称不能为空");
        result.put("flowId", "此过程未指定流程主体");
        result = EntityVerifyUtils.verifyString(result, course);
        if (result.isEmpty()) {
//            验证通过
            if (course.getJudge() == null) {
//                默认没有判断条件
                course.setJudge(false);
            }
            if (course.getFreedom() == null) {
//                默认不是自由选人
                course.setFreedom(false);
            }
            if (course.getCountersign() == null) {
//                默认不是会签
                course.setCountersign(false);
            }
            if (StringUtils.isBlank(course.getId())) {
//                设置UUID主键
                course.setId(UUID.randomUUID().toString());
            }
            if (course.getId().equals(course.getParentCourseId())) {
                //不能修改上级过程为自身过程
                result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
                result.put(RESULT_STATE_MSG_KEY, "禁止修改上级过程为自身过程");
                return result;
            } else if (StringUtils.isNotBlank(course.getParentCourseId()) && queryById(course.getParentCourseId()) == null) {
//                指定的上级过程不存在
                result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
                result.put(RESULT_STATE_MSG_KEY, "指定的上级过程不存在");
                return result;
            }
            if (course.getOvertime() == null) {
                course.setOvertime(-1);
            }
            if (course.getOvertimeDispose() == null) {
                course.setOvertimeDispose(1);
            }
            result.put(RESULT_STATE_KEY, RESULT_STATE_SUCCESS);
        } else {
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
        }
        return result;
    }
}
