package com.yskj.web;

import com.yskj.model.FlowInstanceModel;
import com.yskj.model.RequestListParamModel;
import com.yskj.model.Result;
import com.yskj.service.FlowInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "流程发起实例")
@RestController
@RequestMapping("flowInstance")
public class FlowInstanceController {
    @Resource
    private FlowInstanceService flowInstanceService;

    @ApiOperation("发起流程")
    @PutMapping("startFlow")
    public Result startFlow(@RequestBody FlowInstanceModel flowInstanceModel) {
        return Result.success(flowInstanceService.startFlow(flowInstanceModel));
    }

    @ApiOperation("取消流程实例，记录保存")
    @PostMapping("cancelFlow")
    public Result cancelFlow(@ApiParam("流程实例id") String id) {
        return Result.success(flowInstanceService.cancel(id));
    }

    @ApiOperation("删除流程实例，记录一同被删除")
    @DeleteMapping
    public Result deleteFlow(@ApiParam("流程实例id") String id) {
        return Result.success(flowInstanceService.delete(id));
    }

    @ApiOperation("获取流程实例对象")
    @GetMapping
    public Result getById(@ApiParam("流程实例id") String id) {
        return Result.success(flowInstanceService.queryById(id));
    }

    @ApiOperation("获取我的流程实例集合")
    @GetMapping("getListByUser")
    public Result getListByUser(@RequestParam RequestListParamModel model) {
        return Result.success(flowInstanceService.queryByUser(model));
    }

    @ApiOperation("获取所有的流程实例")
    @GetMapping("getAllList")
    public Result getAllList(@RequestParam RequestListParamModel model) {
        return Result.success(flowInstanceService.queryAll(model));
    }
}
