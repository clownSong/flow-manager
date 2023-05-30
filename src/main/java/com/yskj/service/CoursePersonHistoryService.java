package com.yskj.service;

import com.yskj.entity.CoursePerson;
import com.yskj.entity.CoursePersonHistory;

import java.util.List;

public interface CoursePersonHistoryService {
    /**
     * 添加审批人员记录
     *
     * @param coursePersonHistory
     * @return
     */
    CoursePersonHistory insert(CoursePersonHistory coursePersonHistory);

    /**
     * 添加审批人员记录
     *
     * @param coursePerson
     * @return
     */
    CoursePersonHistory insertByPerson(CoursePerson coursePerson);

    /**
     * 查询审批人员记录
     *
     * @param id 主键
     * @return
     */
    CoursePersonHistory queryById(String id);

    /**
     * 查询审批人员记录集合
     *
     * @param courseHistoryId 过程记录主键
     * @return
     */
    List<CoursePersonHistory> queryByCourseHistoryId(String courseHistoryId);

    /**
     * 删除审批过程人员记录
     *
     * @param id
     */
    int deleteByCourseHistoryId(String id);
}
