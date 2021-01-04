package com.song.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.song.entity.FlowApprove;
import com.song.entity.FlowInstance;
import com.song.model.CourseHistoryResultModel;
import com.song.model.MyFlowApprove;
import com.song.model.RequestListParamModel;
import com.song.model.SystemPersonModel;

import java.util.List;
import java.util.Map;

/**
 * 审批消息服务
 */
public interface FlowApproveService {
    /**
     * 添加审批消息
     *
     * @param approve
     * @return
     */
    FlowApprove insert(FlowApprove approve);

    /**
     * 通过主键删除审批消息
     *
     * @param id 主键
     * @return
     */
    int delete(String id);

    /**
     * 通过流程发起实例删除审批消息
     *
     * @param flowInstanceId
     * @return
     */
    int deleteByFlowInstance(String flowInstanceId);

    /**
     * 修改审批消息状态
     *
     * @param flowApprove
     * @return
     */
    int updateState(FlowApprove flowApprove);

    /**
     * 通过主键获取审批消息对象
     *
     * @param id
     * @return
     */
    FlowApprove queryById(String id);

    /**
     * 通过过程实例获取审批消息集合
     *
     * @param courseHistoryId 审批过程记录id
     * @return
     */
    List<FlowApprove> queryByCourseId(String courseHistoryId);

    /**
     * 通过过程实例id删除审批消息
     *
     * @param courseHistoryId 审批过程记录id
     * @return
     */
    int deleteByCourseId(String courseHistoryId);

    /**
     * 添加审批人员消息
     *
     * @param courseId        过程主体id
     * @param flowInstance    流程发起实例对象
     * @param courseHistoryId 过程主体记录id
     * @return
     */
    List<FlowApprove> insertByCourseId(String courseId, FlowInstance flowInstance, String courseHistoryId);

    /**
     * 发起人与审批人是否为同一人，如果是则自动同意
     *
     * @param flowInstance
     * @param courseHistoryId
     * @return
     */
    boolean isAutoApprove(FlowInstance flowInstance, String courseHistoryId);

    /**
     * 审批通过
     *
     * @param flowApprove 我的审批消息对象
     * @return
     */
    Map<String, Object> pass(FlowApprove flowApprove);

    /**
     * 给定过程记录id，判断此过程内的审批人员记录是否全部通过
     *
     * @param courseHistoryId
     * @return
     */
    boolean isApprove(String courseHistoryId);

    /**
     * 添加审批消息
     *
     * @param courseHistoryId 过程记录id
     * @param flowInstanceId  流程实例id
     * @param sendPerson      发送人信息
     */
    void insertByCourseHistoryId(String courseHistoryId, String flowInstanceId, SystemPersonModel sendPerson);

    /**
     * 取消当前流程所有的审批人审批，已审批的除外
     *
     * @param flowInstanceId 流程发起实例id
     * @return
     */
    boolean cancel(String flowInstanceId);

    /**
     * 通过流程实例获取审批消息集合
     *
     * @param flowInstanceId 流程实例id
     * @return
     */
    List<FlowApprove> queryByFlowInstanceId(String flowInstanceId);

    /**
     * 获取我的审批消息集合
     *
     * @param model
     * @return
     */
    IPage<FlowApprove> queryByParam(RequestListParamModel model);

    /**
     * 获取我的审批消息集合
     *
     * @param model
     * @param type  消息状态筛选
     * @return
     */
    IPage<MyFlowApprove> queryByParam(RequestListParamModel model, byte... type);

    /**
     * 更新读取时间
     *
     * @param approve 审批消息对象
     * @return
     */
    int updateReadDate(FlowApprove approve);

    /**
     * 审批通过，自动流转到下一步过程
     *
     * @param approve 审批消息对象
     * @return
     */
    CourseHistoryResultModel autoPass(FlowApprove approve);
}
