package com.yskj.service;

import com.yskj.entity.Course;
import com.yskj.entity.CourseHistory;
import com.yskj.model.CourseHistoryListModel;
import com.yskj.model.CourseHistoryResultModel;
import com.yskj.model.SystemPersonModel;

import java.util.List;

/**
 * 过程使用记录服务.
 *
 * @author xiaoSong
 * @date 2020-07-18
 */
public interface CourseHistoryService {
    /**
     * 添加过程使用记录
     *
     * @param courseHistory
     * @return
     */
    CourseHistory insert(CourseHistory courseHistory);

    /**
     * 添加过程使用记录
     *
     * @param course
     * @return
     */
    CourseHistory insertByCourse(Course course, String historyId);

    /**
     * 获取过程使用记录
     *
     * @param id
     * @return
     */
    CourseHistory queryById(String id);

    /**
     * 获取过程使用记录
     *
     * @param historyFlowId 流程使用记录主键
     * @return
     */
    List<CourseHistory> queryByHistoryFlowId(String historyFlowId);

    /**
     * 根据过程记录id，流转到下一步过程，注意：此接口默认实现类会判断是否为最后一步审批，如果是则调用:flowInstanceService.success()函数
     *
     * @param courseHistoryId 当前过程记录id
     * @param flowInstanceId  流程实例id
     * @param personModel     发送人信息
     */
    CourseHistoryResultModel goNext(String courseHistoryId, String flowInstanceId, SystemPersonModel personModel);

    /**
     * 根据上一步骤，获取下一步流程集合
     *
     * @param courseHistoryId 上级步骤id
     * @param flowHistoryId 流程记录id
     * @return
     */
    List<CourseHistory> queryByParentId(String courseHistoryId,String flowHistoryId);

    /**
     * 根据流程记录id删除审批过程记录
     *
     * @param historyFlowId 流程记录id
     * @return
     */
    int deleteByFlowHistoryId(String historyFlowId);

    /**
     * 获取流程实例中过程列表
     *
     * @param flowInstanceId 流程实例id
     * @return
     */
    List<CourseHistoryListModel> queryByFlowHistoryId(String flowInstanceId);
}
