package com.song.model;

import com.song.entity.FlowApprove;
import com.song.entity.FlowInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("我的审批消息")
public class MyFlowApprove {
    /**
     * 我的审批消息
     */
    @ApiModelProperty("我的审批消息")
    private FlowApprove approve;
    /**
     * 流程实例
     */
    @ApiModelProperty("流程发起实例")
    private FlowInstance flowMessage;

    public MyFlowApprove(FlowApprove fa, FlowInstance instance) {
        this.approve = fa;
        this.flowMessage = instance;
    }

    public static MyFlowApprove instance(FlowApprove fa, FlowInstance instance) {
        return new MyFlowApprove(fa, instance);
    }

    public FlowApprove getApprove() {
        return approve;
    }

    public void setApprove(FlowApprove approve) {
        this.approve = approve;
    }

    public FlowInstance getFlowMessage() {
        return flowMessage;
    }

    public void setFlowMessage(FlowInstance flowMessage) {
        this.flowMessage = flowMessage;
    }
}
