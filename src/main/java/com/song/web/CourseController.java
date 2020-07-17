package com.song.web;

import com.song.entity.Course;
import com.song.model.Result;
import com.song.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "流程过程管理")
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("通过流程获取过程集合")
    @GetMapping("queryByFlow")
    public Result queryByFlow(@ApiParam("流程id") String flowId) {
        return Result.success(courseService.queryByFlowId(flowId));
    }

    @ApiOperation("通过主键获取过程对象")
    @GetMapping()
    public Result queryById(@ApiParam("过程id") String id) {
        return Result.success(courseService.queryById(id));
    }

    @ApiOperation("添加过程")
    @PutMapping
    public Result insert(@RequestBody Course course) {
        return Result.success(course);
    }

    @ApiOperation("修改过程")
    @PostMapping
    public Result update(@RequestBody Course course) {
        return Result.success(courseService.update(course));
    }

    @ApiOperation("删除过程")
    @DeleteMapping
    public Result delete(@ApiParam("过程id") String id) {
        return Result.success(courseService.delete(id));
    }

    @ApiOperation("通过流程id删除过程集合")
    @DeleteMapping("byFlow")
    public Result deleteByFlow(@ApiParam("流程id") String flowId) {
        return Result.success(courseService.deleteByFlow(flowId));
    }

}
