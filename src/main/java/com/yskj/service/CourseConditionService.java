package com.yskj.service;

import com.yskj.entity.CourseCondition;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-21 10:35
 * 过程判断服务接口
 */
public interface CourseConditionService {
    /**
     * 添加判断条件
     *
     * @param condition
     * @return
     */
    Map<String, Object> insert(CourseCondition condition);

    /**
     * 修改判断条件
     *
     * @param condition
     * @return
     */
    Map<String, Object> update(CourseCondition condition);

    /**
     * 获取判断条件对象
     *
     * @param id 判断条件主键
     * @return
     */
    CourseCondition queryById(String id);

    /**
     * 获取判断条件集合
     *
     * @param courseId 过程id
     * @return
     */
    List<CourseCondition> queryByCourse(String courseId);

    /**
     * 删除过程判断条件
     *
     * @param id 判断条件主键
     * @return
     */
    Map<String, Object> delete(String id);

    /**
     * 删除判断条件集合
     *
     * @param courseId 过程主体id
     * @return
     */
    Map<String, Object> deleteByCourse(String courseId);
}
