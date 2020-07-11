package com.song.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:03
 * 流程主体类
 */
@ApiModel("流程主体对象")
public class Flow extends BaseEntity {
    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称", required = true)
    private String name;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注信息（可选）")
    private String remark;
    /**
     * 视图id
     */
    @ApiModelProperty("绑定视图（可选）")
    private String viewId;
    /**
     * 流程目录
     */
    @ApiModelProperty(value = "流程目录", required = true)
    private FlowFolder folder;
    /**
     * 流程添加时间
     */
    @ApiModelProperty(value = "添加时间，后台自动生成")
    private String datetime;
    /**
     * 流程添加人员id+人员名称
     */
    @ApiModelProperty(value = "流程创建人，格式为（'创建人id,姓名'）,添加时后台自动生成")
    private String person;
    /**
     * 查询排序
     */
    @ApiModelProperty("流程排序号")
    private Integer sort;
    /**
     * 是否停用，0=停用，1=启用
     */
    @ApiModelProperty("是否停用，false=启用，true=停用")
    private boolean isStop;

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

    public FlowFolder getFolder() {
        return folder;
    }

    public void setFolder(FlowFolder folder) {
        this.folder = folder;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
