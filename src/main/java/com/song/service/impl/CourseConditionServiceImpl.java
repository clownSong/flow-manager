package com.song.service.impl;

import com.song.entity.BaseEntity;
import com.song.entity.CourseCondition;
import com.song.mapper.CourseConditionMapper;
import com.song.service.CourseConditionService;
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
 * @date 2019-12-21 10:50
 */
@Service
public class CourseConditionServiceImpl extends BaseServiceImplAbstract implements CourseConditionService {
    @Autowired
    private CourseConditionMapper courseConditionMapper;
    @Autowired
    private CourseService courseService;

    @Override
    public Map<String, Object> insert(CourseCondition condition) {
        return insert(condition, courseConditionMapper);
    }

    @Override
    public Map<String, Object> update(CourseCondition condition) {
        if (queryById(condition.getId()) == null) {
            HashMap<String,Object> result = new HashMap<>(2);
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
            result.put(RESULT_STATE_MSG_KEY, "指定的数据不存在");
            return result;
        } else {
            return update(condition, courseConditionMapper);
        }
    }

    @Override
    public CourseCondition queryById(String id) {
        return courseConditionMapper.queryById(id);
    }

    @Override
    public List<CourseCondition> queryByCourse(String courseId) {
        return courseConditionMapper.queryByCourse(courseId);
    }

    @Override
    public Map<String, Object> delete(String id) {
        return delete(id, courseConditionMapper);
    }

    @Override
    public Map<String, Object> deleteByCourse(String courseId) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(RESULT_STATE_KEY, courseConditionMapper.deleteByCourse(courseId));
        return result;
    }

    @Override
    protected Map<String, Object> verify(BaseEntity baseEntity) {
        Map<String, Object> result = new HashMap<>(16);
        CourseCondition courseCondition = (CourseCondition) baseEntity;
        result.put("courseId", "请指定过程主体");
        result.put("value", "请指定判断值");
        result.put("type", "请指定判断类型");
        result.put("fieldName", "请指定比较字段");
        result = EntityVerifyUtils.verifyString(result, courseCondition);
        if (result.isEmpty()) {
//            验证成功
            if (StringUtils.isBlank(courseCondition.getId())) {
//                生成主键UUID
                courseCondition.setId(UUID.randomUUID().toString());
            }
            if (courseService.queryById(courseCondition.getCourseId()) == null) {
                result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
                result.put(RESULT_STATE_MSG_KEY, "指定的过程主体不存在");
                return result;
            }
            result.put(RESULT_STATE_KEY, RESULT_STATE_SUCCESS);
        } else {
//            验证失败
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
        }
        return result;
    }
}
