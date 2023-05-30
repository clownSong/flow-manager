package com.yskj.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author XiaoSong
 * @date 2019-12-18 11:25
 * 过程处理类型信息表
 */
@TableName("sdb_course_person")
@ApiModel("步骤中审批人员")
public class CoursePerson extends BaseEntity {
    /**
     * 过程名称
     */
    @ApiModelProperty(value = "处理过程实例名称", required = true)
    private String name;
    /**
     * 审批人类型
     */
    @ApiModelProperty(value = "人员类型", required = true)
    private Integer type;
    /**
     * 审批人类型唯一索引
     */
    @ApiModelProperty(value = "处理类型主键,例子：type=用户类型，则pointId=用户id。若type=角色类型，则pointId=角色id", required = true)
    private String pointId;
    /**
     * 处理方式
     */
    @ApiModelProperty(value = "处理方式:1=审批，2=知会，3=审核", required = true)
    private Integer dispose;
    /**
     * 添加时间
     */
    @TableField(value = "date_time", updateStrategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "添加时间")
    private String datetime;
    /**
     * 过程id
     */
    @ApiModelProperty(value = "过程id,添加时必选")
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
