package com.song.entity;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:03
 * 流程主体类
 */
public class Flow extends BaseEntity {
    /**
     * 流程名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 视图id
     */
    private String viewId;
    /**
     * 流程目录
     */
    private FlowFolder folder;
    /**
     * 流程添加时间
     */
    private String datetime;
    /**
     * 流程添加人员id+人员名称
     */
    private String person;
    /**
     * 查询排序
     */
    private Integer sort;
    /**
     * 是否停用，0=停用，1=启用
     */
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
