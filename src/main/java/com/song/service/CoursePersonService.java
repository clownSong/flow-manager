package com.song.service;

import com.song.entity.CoursePerson;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-21 9:37
 */
public interface CoursePersonService {
    /**
     * 增加过程审批实例
     *
     * @param person
     * @return
     */
    Map<String, Object> insert(CoursePerson person);

    /**
     * 修改过程审批实例
     *
     * @param person
     * @return
     */
    Map<String, Object> update(CoursePerson person);

    /**
     * 删除过程审批实例
     *
     * @param id 审批实例id
     * @return
     */
    Map<String, Object> delete(String id);

    /**
     * 删除过程审批实例
     *
     * @param courseId 过程id
     * @return
     */
    Map<String, Object> deleteByCourse(String courseId);

    /**
     * 所有查询过程实例
     *
     * @param courseId 必须制定过程id
     * @return
     */
    List<CoursePerson> queryByCourse(String courseId);

    /**
     * 查询过程实例
     *
     * @param id 过程实例主键
     * @return
     */
    CoursePerson queryById(String id);
}
