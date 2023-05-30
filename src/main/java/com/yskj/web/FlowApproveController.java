package com.yskj.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yskj.entity.FlowApprove;
import com.yskj.model.CourseHistoryResultModel;
import com.yskj.model.MyFlowApprove;
import com.yskj.model.RequestListParamModel;
import com.yskj.model.Result;
import com.yskj.service.FlowApproveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "我的审批")
@RestController
@RequestMapping("flowApprove")
public class FlowApproveController {
    @Resource
    private FlowApproveService flowApproveService;

    @ApiOperation(value = "获取我的审批消息列表", response = MyFlowApprove.class)
    @GetMapping
    public Result<IPage<MyFlowApprove>> getMyApprove(@ApiParam("数据大小") Integer pageSize,
                                                     @ApiParam("数据页码") Integer pageNumber,
                                                     @ApiParam("检索字符串") String search,
                                                     @ApiParam("用户id") String userId,
                                                     @ApiParam("消息状态，参见“审批消息实体”中状态") byte[] type) {
        RequestListParamModel model = new RequestListParamModel();
        model.setUserId(userId);
        model.setPageNumber(pageNumber);
        model.setPageSize(pageSize);
        model.setSearch(search);
        return Result.<IPage<MyFlowApprove>>success(flowApproveService.queryByParam(model, type));
    }

    @ApiOperation(value = "更新我的审批消息为已读")
    @PostMapping("read")
    public Result updateRead(@RequestBody @ApiParam("必传项：id:'审批消息主键',state:1=审批消息已读，4=知会消息已读") FlowApprove approve) {
        int state = flowApproveService.updateReadDate(approve);
                if (state > 0 && state < 400) {
            return Result.success(flowApproveService.queryById(approve.getId()));
        }
        return Result.success(state, "更新失败,请检查参数是否有误(状态只能为1或者4)", approve);
    }

    @ApiOperation(value = "审批通过", response = CourseHistoryResultModel.class)
    @PostMapping("pass")
    public Result approve(@RequestBody FlowApprove approve) {
        return Result.success(flowApproveService.autoPass(approve));
    }

    @ApiOperation("审批不通过（驳回）")
    @PostMapping("cancel")
    public Result cancel(@RequestBody FlowApprove approve) {
        return Result.success(flowApproveService.cancel(approve));
    }
}
