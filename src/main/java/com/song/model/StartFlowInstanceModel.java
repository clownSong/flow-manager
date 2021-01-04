package com.song.model;

import com.song.entity.Course;
import com.song.entity.Flow;
import com.song.entity.FlowInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 流程发起返回值model.
 *
 * @author xiaoSong
 * @date 2020-07-20
 */
@ApiModel("流程发起返回值model")
public class StartFlowInstanceModel<T> {
    @ApiModelProperty("流程发起实例")
    private FlowInstance flowInstance;
    @ApiModelProperty("选择的流程")
    private Flow flow;
    @ApiModelProperty("流程中第一个步骤")
    private Course firstCourse;
    @ApiModelProperty("是否自由选人")
    private boolean isFreedom;
    @ApiModelProperty("自由选人集合元素")
    private List<T> freedomElementArray;
    @ApiModelProperty("提示信息")
    private String msg;

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

    public Course getFirstCourse() {
        return firstCourse;
    }

    public void setFirstCourse(Course firstCourse) {
        this.firstCourse = firstCourse;
    }

    public boolean isFreedom() {
        return isFreedom;
    }

    public void setFreedom(boolean freedom) {
        isFreedom = freedom;
    }

    public List<T> getFreedomElementArray() {
        return freedomElementArray;
    }

    public void setFreedomElementArray(List<T> freedomElementArray) {
        this.freedomElementArray = freedomElementArray;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
