package com.song.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前端数据集合请求参数封装
 */
@ApiModel("前端请求数据查询参数model")
public class RequestListParamModel {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty(value = "数据大小", required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "数据页码", required = true)
    private Integer pageNumber;
    @ApiModelProperty("排序名称")
    private String sort;
    @ApiModelProperty("排序类型")
    private String order;
    @ApiModelProperty("搜索字符串")
    private String search;
    @ApiModelProperty("数据开始日期")
    private String startDate;
    @ApiModelProperty("数据截止日期")
    private String endDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
