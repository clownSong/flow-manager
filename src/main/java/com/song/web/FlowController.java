package com.song.web;

import com.song.entity.Flow;
import com.song.service.FlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2020-01-14 8:47
 * 流程主体接口，提供以下功能：
 * 1、根据目录id获取流程集合
 * 2、添加流程
 * 3、修改流程
 * 4、删除单个流程
 * 5、删除自定义视图中所有流程
 * 6、删除目录下所有流程
 * 7、获取视图下所有流程
 * 8、根据流程名称和视图查询流程集合
 */
@Api(tags = "流程主体管理")
@RestController
@RequestMapping("flow")
public class FlowController extends BaseController {
    /**
     * 流程主体服务
     */
    private final FlowService flowService;

    @Autowired
    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    /**
     * 获取流程集合
     *
     * @param folderId 目录 id
     * @return {@link List<Flow>}
     */
    @ApiOperation("获取流程集合")
    @GetMapping("/getByFolder")
    public List<Flow> getFlowByFolder(@ApiParam("目录id") String folderId) {
        return flowService.queryByFolder(folderId);
    }

    /**
     * 添加流程
     *
     * @param flow {@link Flow}
     * @return {@link Map<String,Object>}
     * key:state,{value:1(添加成功),value:-1(添加失败)}
     * key:data,{value:{@link Flow}} 添加成功后返回
     */
    @ApiOperation("添加流程")
    @PutMapping
    public Map<String, Object> insert(@RequestBody Flow flow) {
        return flowService.insert(flow);
    }

    /**
     * 更新流程
     *
     * @param flow {@link Flow}
     * @return {@link Map<String,Object>}
     * key:state,{value:1(修改成功),value:-1|0(修改失败)}
     * key:data,{value:{@link Flow}} 修改成功后返回
     */
    @ApiOperation("修改流程主体")
    @PostMapping
    public Map<String, Object> update(@RequestBody Flow flow) {
        return flowService.update(flow);
    }

    /**
     * 删除流程
     *
     * @param id 流程id
     * @return {@link Map<String,Object>}
     * ket:state,value:1（操作成功）,value:-1|0（操作失败）
     */
    @ApiOperation("删除流程")
    @DeleteMapping
    public Map<String, Object> delete(@ApiParam("流程主体id") String id) {
        return flowService.delete(id);
    }

    /**
     * 获取流程主体
     *
     * @param id 流程id
     * @return {@link Flow}
     */
    @ApiOperation("获取流程对象")
    @GetMapping
    public Flow getById(@ApiParam("流程id") String id) {
        return flowService.queryById(id);
    }

    /**
     * 通过视图id,获取流程集合
     *
     * @param viewId 视图id
     * @return {@link List<Flow>}
     */
    @ApiOperation("获取流程集合")
    @GetMapping("/getByView")
    public List<Flow> getByView(@ApiParam("视图id") String viewId) {
        return flowService.queryByViewId(viewId);
    }

    /**
     * 删除指定目录下流程集合
     *
     * @param folderId 目录id
     * @return {@link Integer} 返回删除成功的数据大小
     */
    @ApiOperation("删除指定目录中所有流程")
    @DeleteMapping("/deleteByFolder")
    public Integer deleteByFolder(@ApiParam("流程目录id") String folderId) {
        return flowService.deleteByFolder(folderId);
    }

    /**
     * 删除指定视图下流程集合（该视图由其他模块添加时自定义数据）
     *
     * @param viewId 视图id
     * @return {@link Integer} 返回删除成功的数据大小
     */
    @ApiOperation("删除指定视图中所有流程")
    @DeleteMapping("/deleteByView")
    public Integer deleteByView(@ApiParam("视图id") String viewId) {
        return flowService.deleteByViewId(viewId);
    }

    /**
     * 检索流程
     *
     * @param name   流程名称
     * @param viewId 视图id（可选），传入null时则检索未绑定视图的流程
     * @return {@link List<Flow>}
     */
    @ApiOperation("搜索流程")
    @GetMapping("/searchFlow")
    public List<Flow> searchFlow(@ApiParam("流程名称") String name, @ApiParam("视图id") String viewId) {
        return flowService.queryByName(name, viewId);
    }

}
