package com.song.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程发起实例主体 实体类
 *
 * @author makejava
 * @since 2020-07-17 13:53:37
 */
@TableName("sdb_flow_instance")
@ApiModel("流程实例")
public class FlowInstance extends BaseEntity {
    private static final long serialVersionUID = 826799366621724221L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 流程记录id
     */
    @ApiModelProperty("流程记录id")
    private String flowHistoryId;
    /**
     * 发起时间
     */
    @ApiModelProperty("发起时间")
    private String startDate;
    /**
     * 发起人id
     */
    @ApiModelProperty("发起人id")
    private String userId;
    /**
     * 发起人姓名
     */
    @ApiModelProperty("发起人姓名")
    private String userName;
    /**
     * 发起标题
     */
    @ApiModelProperty(value = "发起标题", required = true)
    private String title;
    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;
    /**
     * 模块类型id
     */
    @ApiModelProperty(value = "模块类型id", required = true)
    private String moduleTypeId;
    /**
     * 模块单据主键
     */
    @ApiModelProperty(value = "模块单据主键", required = true)
    private String moduleId;
    /**
     * 当前流程状态
     */
    @ApiModelProperty("当前流程状态:1=审批中,2=已完成,3=已读,4=取消")
    private byte state;
    /**
     * 流程状态变化时回调地址
     */
    @ApiModelProperty("流程状态变化时回调地址")
    private String changeApi;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getFlowHistoryId() {
        return flowHistoryId;
    }

    public void setFlowHistoryId(String flowHistoryId) {
        this.flowHistoryId = flowHistoryId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModuleTypeId() {
        return moduleTypeId;
    }

    public void setModuleTypeId(String moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getChangeApi() {
        return changeApi;
    }

    public void setChangeApi(String changeApi) {
        this.changeApi = changeApi;
    }
}