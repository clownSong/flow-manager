package com.song.entity;

/**
 * @author XiaoSong
 * @date 2019-12-18 10:59
 * 流程目录类（sdb_flow_folder）
 */
public class FlowFolder extends BaseEntity {
    /**
     * 目录名称
     */
    private String name;
    /**
     * 上级目录id
     */
    private String parentId;
    /**
     * 上级所有目录+当前目录id
     */
    private String root;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序序号
     */
    private Integer sort;
    /**
     * 目录创建时间
     */
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
