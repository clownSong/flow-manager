package com.yskj.web;

import com.yskj.model.CourseHistoryListModel;
import com.yskj.model.Result;
import com.yskj.service.CourseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "流程过程实例")
@RestController
@RequestMapping("courseHistory")
public class CourseHistoryController {
    @Resource
    private CourseHistoryService courseHistoryService;

    @ApiOperation(value = "获取流程实例中过程列表", response = CourseHistoryListModel.class)
    @GetMapping("getBuFlowInstanceId")
    public Result getCourseHistory(@ApiParam("流程发起实例记录id") String flowHistoryId) {
        return Result.success(courseHistoryService.queryByFlowHistoryId(flowHistoryId));
    }
}
