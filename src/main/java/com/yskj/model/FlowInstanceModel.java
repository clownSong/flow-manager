package com.yskj.model;

import com.yskj.entity.Flow;
import com.yskj.entity.FlowInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("流程实例model")
public class FlowInstanceModel<T> {
    @ApiModelProperty(value = "流程实例", required = true)
    private FlowInstance flowInstance;
    @ApiModelProperty(value = "流程对象", required = true)
    private Flow flow;
    @ApiModelProperty(value = "自由选择的人员集合")
    private List<T> freedomElementArray;

    public FlowInstance getFlowInstance() {
        return flowInstance;
    }

    public void setFlowInstance(FlowInstance flowInstance) {
        this.flowInstance = flowInstance;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public List<T> getFreedomElementArray() {
        return freedomElementArray;
    }

    public void setFreedomElementArray(List<T> freedomElementArray) {
        this.freedomElementArray = freedomElementArray;
    }
}
