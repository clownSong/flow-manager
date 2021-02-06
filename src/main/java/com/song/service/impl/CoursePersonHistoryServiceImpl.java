package com.song.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.song.entity.CoursePerson;
import com.song.entity.CoursePersonHistory;
import com.song.mapper.CoursePersonHistoryMapper;
import com.song.service.CoursePersonHistoryService;
import com.song.utils.EntityVerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CoursePersonHistoryServiceImpl implements CoursePersonHistoryService {
    private Logger log = LoggerFactory.getLogger(CoursePersonHistoryServiceImpl.class);
    @Resource
    private CoursePersonHistoryMapper coursePersonHistoryMapper;

    @Override
    public CoursePersonHistory insert(CoursePersonHistory coursePersonHistory) {
        Map<String, Object> verify = new HashMap<>(16);
        verify.put("name", "请指定处理人名称");
        verify.put("type", "请指定处理对象类型");
        verify.put("pointId", "请指定处理对象唯一主键");
        verify.put("dispose", "请指定处理方式");
        verify.put("courseId", "过程记录id不能为空");
        verify = EntityVerifyUtils.verifyString(verify, coursePersonHistory);
        if (verify.isEmpty()) {
            coursePersonHistory.setId(UUID.randomUUID().toString());
            coursePersonHistoryMapper.insert(coursePersonHistory);
        } else {
            log.warn("添加审批人员记录失败：" + verify.toString());
            return null;
        }
        return coursePersonHistory;
    }

    @Override
    @Transactional
    public CoursePersonHistory insertByPerson(CoursePerson coursePerson) {
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return insert(jsonMapper.readValue(jsonMapper.writeValueAsString(coursePerson), CoursePersonHistory.class));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public CoursePersonHistory queryById(String id) {
        return (CoursePersonHistory) coursePersonHistoryMapper.selectById(id);
    }

    @Override
    public List<CoursePersonHistory> queryByCourseHistoryId(String courseHistoryId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_history_id", courseHistoryId);
        return coursePersonHistoryMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteByCourseHistoryId(String courseHistoryId) {
        return coursePersonHistoryMapper.delete(new UpdateWrapper<CoursePersonHistory>().lambda().eq(CoursePersonHistory::getCourseId, courseHistoryId));
    }
}
