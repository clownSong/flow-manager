package com.song.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:14
 * 流程过程类（sdb_course）
 */
@ApiModel("过程实体")
public class Course extends BaseEntity {
    /**
     * 过程名称
     */
    @ApiModelProperty(value = "过程名称", required = true)
    private String name;
    /**
     * 备注信息
     */
    @ApiModelProperty("备注信息")
    private String remark;
    /**
     * 超时时间（毫秒）
     */
    @ApiModelProperty("超时时间（可选）")
    private Integer overtime;
    /**
     * 超时处理方式id
     */
    @ApiModelProperty(value = "超时后的处理方式:0=不通过，1=通过，2=跳过(忽略，直接到下一个审批步骤", required = true)
    private Integer overtimeDispose;
    /**
     * 上级过程id
     */
    @ApiModelProperty("上级过程id")
    private String parentCourseId;
    /**
     * 流程id
     */
    @ApiModelProperty("流程id")
    private String flowId;
    /**
     * 是否有判断条件
     */
    @ApiModelProperty("是否有判断条件")
    private Boolean isJudge;
    /**
     * 是否自由选人审批
     */
    @ApiModelProperty(value = "是否由发起人自由选人审批", required = true)
    private Boolean isFreedom;
    /**
     * 是否会签
     */
    @ApiModelProperty(value = "是否会签", required = true)
    private Boolean isCountersign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public Integer getOvertimeDispose() {
        return overtimeDispose;
    }

    public void setOvertimeDispose(Integer overtimeDispose) {
        this.overtimeDispose = overtimeDispose;
    }

    public String getParentCourseId() {
        return parentCourseId;
    }

    public void setParentCourseId(String parentCourseId) {
        this.parentCourseId = parentCourseId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Boolean getJudge() {
        return isJudge;
    }

    public void setJudge(Boolean judge) {
        isJudge = judge;
    }

    public Boolean getFreedom() {
        return isFreedom;
    }

    public void setFreedom(Boolean freedom) {
        isFreedom = freedom;
    }

    public Boolean getCountersign() {
        return isCountersign;
    }

    public void setCountersign(Boolean countersign) {
        isCountersign = countersign;
    }
}
