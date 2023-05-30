package com.yskj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@TableName("sdb_flow_history")
@ApiModel("流程主体使用记录实体")
public class FlowHistory extends BaseEntity {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("流程名称")
    private String name;
    @ApiModelProperty("备注信息")
    private String remark;
    @ApiModelProperty("视图id")
    private String viewId;
    @ApiModelProperty("流程目录id")
    private String folderId;
    @ApiModelProperty("使用时间")
    private String date;
    @ApiModelProperty("使用人信息，格式：'id,姓名'")
    private String person;
    @ApiModelProperty("流程主体id")
    private String flowId;

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

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
}
