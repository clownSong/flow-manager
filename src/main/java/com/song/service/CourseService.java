package com.song.service;

import com.song.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-19 16:19
 * 流程过程服务接口
 */
public interface CourseService {
    /**
     * 添加过程主体
     *
     * @param course
     * @return
     */
    Map<String, Object> insert(Course course);

    /**
     * 更新过程主体
     *
     * @param course
     * @return
     */
    Map<String, Object> update(Course course);

    /**
     * 删除过程
     *
     * @param id 过程主键
     * @return
     */
    Map<String, Object> delete(String id);

    /**
     * 通过流程id,删除步骤集合
     *
     * @param flowId 流程id
     * @return
     */
    Map<String, Object> deleteByFlow(String flowId);

    /**
     * 获取过程集合
     *
     * @param flowId 流程主体id
     * @return
     */
    List<Course> queryByFlowId(String flowId);

    /**
     * 获取过程对象
     *
     * @param id 过程主键
     * @return
     */
    Course queryById(String id);

}
