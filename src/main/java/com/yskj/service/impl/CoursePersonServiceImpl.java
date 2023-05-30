package com.yskj.service.impl;

import com.yskj.entity.BaseEntity;
import com.yskj.entity.CoursePerson;
import com.yskj.mapper.CoursePersonMapper;
import com.yskj.model.SystemPersonModel;
import com.yskj.service.CoursePersonService;
import com.yskj.service.CourseService;
import com.yskj.service.SystemPersonService;
import com.yskj.utils.DateUtils;
import com.yskj.utils.EntityVerifyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.yskj.utils.Constant.*;

/**
 * @author XiaoSong
 * @date 2019-12-21 9:45
 */
@Service
public class CoursePersonServiceImpl extends BaseServiceImplAbstract implements CoursePersonService {
    @Resource
    private CoursePersonMapper coursePersonMapper;
    @Resource
    private CourseService courseService;
    @Resource
    private SystemPersonService systemPersonService;

    @Override
    public Map<String, Object> insert(CoursePerson person) {
        return insert(person, coursePersonMapper);
    }

    @Override
    public Map<String, Object> update(CoursePerson person) {
        return update(person, coursePersonMapper);
    }

    @Override
    public Map<String, Object> delete(String id) {
        return delete(id, coursePersonMapper);
    }

    @Override
    public Map<String, Object> deleteByCourse(String courseId) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(RESULT_STATE_KEY, coursePersonMapper.deleteByCourse(courseId));
        return result;
    }

    @Override
    public List<CoursePerson> queryByCourse(String courseId) {
        return coursePersonMapper.queryByCourse(courseId);
    }

    @Override
    public CoursePerson queryById(String id) {
        return coursePersonMapper.queryById(id);
    }

    @Override
    protected Map<String, Object> verify(BaseEntity baseEntity) {
        Map<String, Object> result = new HashMap<>(4);
        CoursePerson coursePerson = (CoursePerson) baseEntity;
        result.put("name", "请指定处理实例名称");
        result.put("type", "请指定处理实例类型");
        result.put("dispose", "请指定处理方式");
        result.put("courseId", "请指定过程主体");
        result = EntityVerifyUtils.verifyString(result, coursePerson);
        if (result.isEmpty()) {
//            验证成功
            if (StringUtils.isBlank(coursePerson.getId())) {
//                设置UUID主键
                coursePerson.setId(UUID.randomUUID().toString());
//                设置添加时间
                coursePerson.setDatetime(DateUtils.getNowDatetime());
            }
            if (courseService.queryById(coursePerson.getCourseId()) == null) {
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

    @Override
    public List<SystemPersonModel> getPersonList(String courseId,String userId) {
        final ArrayList<SystemPersonModel> personModels = new ArrayList<>();
        List<CoursePerson> coursePeople = queryByCourse(courseId);
        coursePeople.forEach(coursePerson -> {
            personModels.addAll(getPersonListByType(Objects.isNull(coursePerson.getPointId()) ? userId : coursePerson.getPointId(), coursePerson.getType() + ""));
        });
        return personModels;
    }

    @Override
    public List<SystemPersonModel> getPersonListByType(String pointId, String type) {
        return systemPersonService.getByPerson(pointId, type);
    }
}
