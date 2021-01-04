package com.song.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@TableName("sdb_flow_approve")
@ApiModel("审批消息实体")
public class FlowApprove extends BaseEntity {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("流程实例id")
    private String flowInstanceId;
    @ApiModelProperty("过程记录id")
    private String courseHistoryId;
    @ApiModelProperty("发送人id")
    private String sendUserId;
    @ApiModelProperty("发送人姓名")
    private String sendUserName;
    @ApiModelProperty("接收人id")
    private String acceptUserId;
    @ApiModelProperty("接收人姓名")
    private String acceptUserName;
    @ApiModelProperty("读取时间")
    private String readDatetime;
    @ApiModelProperty("审批时间")
    private String approveDatetime;
    @ApiModelProperty("审批内容")
    private String approveContent;
    @ApiModelProperty("审批状态：0=未读,1=已读,2=同意,3=知会未读,4=知会已读,5=驳回,6=忽略,7=主流程被取消中断,8=此过程被其他人审批")
    private byte state;
    @ApiModelProperty("发生时间")
    private String sendDatetime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public String getCourseHistoryId() {
        return courseHistoryId;
    }

    public void setCourseHistoryId(String courseHistoryId) {
        this.courseHistoryId = courseHistoryId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public String getAcceptUserName() {
        return acceptUserName;
    }

    public void setAcceptUserName(String acceptUserName) {
        this.acceptUserName = acceptUserName;
    }

    public String getReadDatetime() {
        return readDatetime;
    }

    public void setReadDatetime(String readDatetime) {
        this.readDatetime = readDatetime;
    }

    public String getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(String approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

}
