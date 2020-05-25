package com.song.service.impl;

import com.song.entity.BaseEntity;
import com.song.entity.Course;
import com.song.mapper.CourseMapper;
import com.song.service.CourseService;
import com.song.utils.EntityVerifyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.song.utils.Constant.*;

/**
 * @author XiaoSong
 * @date 2019-12-19 16:29
 */
@Service
public class CourseServiceImpl extends BaseServiceImplAbstract implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, Object> insert(Course course) {
        return insert(course, courseMapper);
    }

    @Override
    public Map<String, Object> update(Course course) {
        if (queryById(course.getId()) != null) {
            return update(course, courseMapper);
        } else {
            Map<String,Object> result = new HashMap<>();
            result.put(RESULT_STATE_KEY,RESULT_STATE_FAIL);
            result.put(RESULT_STATE_MSG_KEY,"修改的过程不存在");
            return result;
        }
    }

    @Override
    public Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(RESULT_STATE_KEY, courseMapper.delete(id));
        return result;
    }

    @Override
    public Map<String, Object> deleteByFlow(String flowId) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(RESULT_STATE_KEY, courseMapper.deleteByFlow(flowId));
        return result;
    }

    @Override
    public List<Course> queryByFlowId(String flowId) {
        return courseMapper.queryByFlowId(flowId);
    }

    @Override
    public Course queryById(String id) {
        return courseMapper.queryById(id);
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
