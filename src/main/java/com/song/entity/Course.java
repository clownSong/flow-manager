package com.song.entity;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:14
 * 流程过程类（sdb_course）
 */
public class Course extends BaseEntity {
    /**
     * 过程名称
     */
    private String name;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 超时时间（毫秒）
     */
    private Integer overtime;
    /**
     * 超时处理方式id
     */
    private String overtimeDispose;
    /**
     * 上级过程id
     */
    private String parentCourseId;
    /**
     * 流程id
     */
    private String flowId;
    /**
     * 是否有判断条件
     */
    private Boolean isJudge;
    /**
     * 是否自由选人审批
     */
    private Boolean isFreedom;
    /**
     * 是否会签
     */
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

    public String getOvertimeDispose() {
        return overtimeDispose;
    }

    public void setOvertimeDispose(String overtimeDispose) {
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
