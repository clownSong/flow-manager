package com.yskj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yskj.entity.FlowInstance;
import com.yskj.model.FlowInstanceModel;
import com.yskj.model.RequestListParamModel;
import com.yskj.model.StartFlowInstanceModel;
import com.yskj.model.SystemPersonModel;

/**
 * 流程发起实例服务接口
 */
public interface FlowInstanceService {
    /**
     * 开始流转流程
     *
     * @param flowInstanceModel
     * @return
     */
    StartFlowInstanceModel startFlow(FlowInstanceModel flowInstanceModel);

    /**
     * 获取我的发起实例列表
     *
     * @param paramModel 数据查询参数
     * @return
     */
    IPage<FlowInstance> queryByUser(RequestListParamModel paramModel);

    /**
     * 获取所有发起流程
     *
     * @param paramModel 查询参数model
     * @return
     */
    IPage<FlowInstance> queryAll(RequestListParamModel paramModel);

    /**
     * 取消流程发起
     *
     * @param id 主键
     * @return
     */
    StartFlowInstanceModel cancel(String id);

    /**
     * 驳回流程
     * @param flowInstanceId
     * @return
     */
    StartFlowInstanceModel updateState(String flowInstanceId);

    /**
     * 审批完成
     *
     * @param id         主键
     * @param sendPerson 最后处理人信息
     */
    StartFlowInstanceModel success(String id, SystemPersonModel sendPerson);

    /**
     * 删除流程实例
     *
     * @param id 主键
     * @return
     */
    StartFlowInstanceModel delete(String id);

    /**
     * 获取流程实例对象
     *
     * @param id 主键
     * @return
     */
    FlowInstance queryById(String id);

    void flowChange(FlowInstance instance, SystemPersonModel sendPerson);
}
