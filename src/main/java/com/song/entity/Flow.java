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
    private FlowFolder fodler;
    /**
     * 流程添加时间
     */
    private String datetime;
    /**
     * 流程添加人员id+人员名称
     */
    private String person;

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

    public FlowFolder getFodler() {
        return fodler;
    }

    public void setFodler(FlowFolder fodler) {
        this.fodler = fodler;
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
}
