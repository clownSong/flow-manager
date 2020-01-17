package com.song.web;

import com.song.entity.FlowFolder;
import com.song.service.FlowFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-21 13:26
 * 流程目录数据接口，包含以下功能：
 * 1、添加目录   <a>insert()</a>
 * 2、修改目录   <a>update()</a>
 * 3、删除目录   <a>delete()</a>
 * 4、查询系统根目录集合 <a>queryRoot()</a>
 * 5、查询子目录集合    <a>queryByParent()</a>
 * 6、通过目录id查询目录对象 <a>queryById()</a>
 * Note：FlowFolder对象请参见:com.song.entity.FlowFolder类
 */
@RestController
@RequestMapping("/flowManager/flowFolder")
public class FlowFolderController extends BaseController {
    /**
     * 流程目录服务接口
     */
    private FlowFolderService flowFolderService;

    @Autowired
    public FlowFolderController(FlowFolderService flowFolderService) {
        this.flowFolderService = flowFolderService;
    }

    /**
     * 添加目录
     *
     * @param folder {@link FlowFolder} 目录实例
     * @return key：{@link com.song.utils.Constant} RESULT_STATE_KEY=RESULT_STATE_SUCCESS(添加成功)，
     * RESULT_STATE_KEY=RESULT_STATE_FAIL（添加失败，提示信息请读取：RESULT_STATE_MSG_KEY）}
     */
    @PostMapping
    public Map<String, Object> insert(FlowFolder folder) {
        return flowFolderService.insert(folder);
    }

    /**
     * 更新目录信息
     *
     * @param folder {@link FlowFolder} 目录实例
     * @return key：{@link com.song.utils.Constant}
     * key:state,{value:1(添加成功),-1（添加失败）}
     * msg:添加失败提示信息
     * data:{@link FlowFolder}添加成功后返回流程目录数据
     */
    @PutMapping
    public Map<String, Object> update(FlowFolder folder) {
        return flowFolderService.update(folder);
    }

    /**
     * 删除目录信息
     *
     * @param id 目录主键
     * @return {@link Map<String,Object>}
     * key:state,value:1(删除成功),-1(删除失败)，
     * key:msg（删除失败提示信息）
     */
    @DeleteMapping
    public Map<String, Object> delete(String id) {
        return flowFolderService.delete(id);
    }

    /**
     * 获取根目录
     *
     * @return {@link List<FlowFolder>}
     */
    @GetMapping("/getRoot")
    public List<FlowFolder> queryRoot() {
        return flowFolderService.queryRoot();
    }

    /**
     * 通过目录id,获取子目录集合
     *
     * @param parentId 目录id
     * @return {@link List<FlowFolder>}
     */
    @GetMapping("/getByParent")
    public List<FlowFolder> queryByParent(String parentId) {
        return flowFolderService.queryFolder(parentId);
    }

    /**
     * 获取目录实例
     *
     * @param id 主键
     * @return {@link FlowFolder}
     */
    @GetMapping("/getById")
    public FlowFolder queryById(String id) {
        return flowFolderService.queryById(id);
    }
}
