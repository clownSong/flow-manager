package com.song.entity;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:25
 * 过程审批人员信息表
 */
public class CoursePerson extends BaseEntity {
    /**
     * 过程名称
     */
    private String name;
    /**
     * 审批人类型
     */
    private Integer type;
    /**
     * 审批人类型唯一索引
     */
    private String pointId;
    /**
     * 处理方式
     */
    private Integer dispose;
    /**
     * 添加时间
     */
    private String datetime;
    /**
     * 过程id
     */
    private String courseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public Integer getDispose() {
        return dispose;
    }

    public void setDispose(Integer dispose) {
        this.dispose = dispose;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
