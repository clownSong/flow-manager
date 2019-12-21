package com.song.entity;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:29
 * 审批步骤条件判断类
 */
public class CourseCondition extends BaseEntity {
    /**
     * 过程id
     */
    private String courseId;
    /**
     * 条件类型（比对方式）
     */
    private Integer type;
    /**
     * 指定条件对比值,value值与fieldName值进行对比
     */
    private String value;
    /**
     * 条件关系
     */
    private Integer relation;
    /**
     * 数据库字段名称，对比时数据来源，fieldName的值与value值进行对比
     */
    private String fieldName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
