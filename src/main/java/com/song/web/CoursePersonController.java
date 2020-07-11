package com.song.web;

import com.song.entity.CoursePerson;
import com.song.model.Result;
import com.song.service.CoursePersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "审批人员管理")
@RestController
@RequestMapping("coursePerson")
public class CoursePersonController {
    @Autowired
    private CoursePersonService coursePersonService;

    @ApiOperation("添加审批人员")
    @PutMapping
    public Result insert(@RequestBody CoursePerson person) {
        return Result.success(coursePersonService.insert(person));
    }

    @ApiOperation("修改审批人员")
    @PostMapping
    public Result update(@RequestBody CoursePerson person) {
        return Result.success(coursePersonService.update(person));
    }

    @ApiOperation("删除审批人员")
    @DeleteMapping
    public Result delete(@ApiParam("审批人员id") String id) {
        return Result.success(coursePersonService.delete(id));
    }

    @ApiOperation("通过步骤id获取审批人员集合")
    @GetMapping("getByCourseId")
    public Result getPerson(@ApiParam("过程id") String courseId) {
        return Result.success(coursePersonService.queryByCourse(courseId));
    }

    @ApiOperation("通过id获取审批人员集合")
    @GetMapping
    public Result getPersonById(@ApiParam("主键") String id) {
        return Result.success(coursePersonService.queryById(id));
    }
}
