package com.yskj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@TableName("sdb_course_history")
@ApiModel("流程过程实例记录")
public class CourseHistory extends BaseEntity {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("过程名称")
    private String name;
    @ApiModelProperty("备注信息")
    private String remark;
    @ApiModelProperty("超时时间")
    private int overtime;
    @ApiModelProperty("超时处理方式")
    private int overtimeDispose;
    @ApiModelProperty("上级过程id")
    private String parentCourseId;
    @ApiModelProperty("流程记录id")
    private String flowHistoryId;
    @ApiModelProperty("是否有条件判断")
    private byte isJudge;
    @ApiModelProperty("是否自由选人")
    private byte isFreedom;
    @ApiModelProperty("是否会签，会签代表着该过程中每一位审批人员都需要审批同意")
    private byte isCountersign;
    @ApiModelProperty("流程X位置")
    private float postionX;
    @ApiModelProperty("流程Y位置")
    private float postionY;
    @ApiModelProperty("过程id")
    private String courseId;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public int getOvertimeDispose() {
        return overtimeDispose;
    }

    public void setOvertimeDispose(int overtimeDispose) {
        this.overtimeDispose = overtimeDispose;
    }

    public String getParentCourseId() {
        return parentCourseId;
    }

    public void setParentCourseId(String parentCourseId) {
        this.parentCourseId = parentCourseId;
    }

    public String getFlowHistoryId() {
        return flowHistoryId;
    }

    public void setFlowHistoryId(String flowHistoryId) {
        this.flowHistoryId = flowHistoryId;
    }

    public byte getIsJudge() {
        return isJudge;
    }

    public void setIsJudge(byte isJudge) {
        this.isJudge = isJudge;
    }

    public byte getIsFreedom() {
        return isFreedom;
    }

    public void setIsFreedom(byte isFreedom) {
        this.isFreedom = isFreedom;
    }

    public byte getIsCountersign() {
        return isCountersign;
    }

    public void setIsCountersign(byte isCountersign) {
        this.isCountersign = isCountersign;
    }

    public float getPostionX() {
        return postionX;
    }

    public void setPostionX(float postionX) {
        this.postionX = postionX;
    }

    public float getPostionY() {
        return postionY;
    }

    public void setPostionY(float postionY) {
        this.postionY = postionY;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
