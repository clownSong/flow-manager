package com.yskj.model;

import com.yskj.entity.CourseHistory;
import com.yskj.entity.FlowApprove;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("过程记录列表返回模型")
public class CourseHistoryListModel {
    @ApiModelProperty("过程记录对象")
    private CourseHistory courseHistory;
    @ApiModelProperty("过程处理消息集合")
    private List<FlowApprove> flowApproves;

    public CourseHistoryListModel(CourseHistory courseHistory, List<FlowApprove> flowApproves) {
        this.courseHistory = courseHistory;
        this.flowApproves = flowApproves;
    }

    public CourseHistory getCourseHistory() {
        return courseHistory;
    }

    public void setCourseHistory(CourseHistory courseHistory) {
        this.courseHistory = courseHistory;
    }

    public List<FlowApprove> getFlowApproves() {
        return flowApproves;
    }

    public void setFlowApproves(List<FlowApprove> flowApproves) {
        this.flowApproves = flowApproves;
    }
}
