package com.song.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author XiaoSong
 * @date 2019-12-18 10:59
 * 流程目录类（sdb_flow_folder）
 */
@ApiModel("流程目录实体")
public class FlowFolder extends BaseEntity {
    /**
     * 目录名称
     */
    @ApiModelProperty(value = "目录名称,添加时必填", required = true)
    private String name;
    /**
     * 上级目录id
     */
    @ApiModelProperty("上级目录id")
    private String parentId;
    /**
     * 上级所有目录+当前目录id
     */
    @ApiModelProperty("上级所有目录+当前目录id，添加时无需传入")
    private String root;
    /**
     * 备注
     */
    @ApiModelProperty("目录备注信息")
    private String remark;
    /**
     * 排序序号
     */
    @ApiModelProperty("查询时排序序号")
    private Integer sort;
    /**
     * 目录创建时间
     */
    @ApiModelProperty("添加时间")
    private String datetime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
