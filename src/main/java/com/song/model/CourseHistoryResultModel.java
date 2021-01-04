package com.song.model;

import com.song.entity.CourseHistory;
import com.song.entity.FlowInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批过程记录模型
 */
@ApiModel("审批过程记录返回值模型")
public class CourseHistoryResultModel {
    /**
     * 是否最后的过程审批
     */
    @ApiModelProperty("是否最后的过程审批")
    private boolean isLast;
    /**
     * 过程中的审批节点是否完成
     */
    @ApiModelProperty("过程中的审批节点是否完成")
    private boolean isOk;
    /**
     * 下一步审批节点是否为自由选人
     */
    @ApiModelProperty("下一步审批节点是否为自由选人")
    private boolean nextIsFreedom;
    /**
     * 下一步审批节点对象
     */
    @ApiModelProperty("下一步审批节点对象")
    private CourseHistory courseHistory;
    /**
     * 当前流程主体对象
     */
    @ApiModelProperty("当前流程主体对象")
    private FlowInstance flowInstance;
    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String msg;

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public boolean isNextIsFreedom() {
        return nextIsFreedom;
    }

    public void setNextIsFreedom(boolean nextIsFreedom) {
        this.nextIsFreedom = nextIsFreedom;
    }

    public CourseHistory getCourseHistory() {
        return courseHistory;
    }

    public void setCourseHistory(CourseHistory courseHistory) {
        this.courseHistory = courseHistory;
    }

    public FlowInstance getFlowInstance() {
        return flowInstance;
    }

    public void setFlowInstance(FlowInstance flowInstance) {
        this.flowInstance = flowInstance;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
