package com.song.service;

import com.song.entity.Flow;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-19 9:21
 * 流程主体服务
 */
public interface FlowService {
    /**
     * 添加流程
     *
     * @param flow
     * @return {state:"操作状态",msg:"提示信息",data:"修改后的数据"}
     */
    Map<String, Object> insert(Flow flow);

    /**
     * 删除流程
     *
     * @param id 主键
     * @return 失败返回-1
     */
    Map<String, Object> delete(String id);

    /**
     * 修改流程
     *
     * @param flow
     * @return {state:"操作状态",msg:"提示信息",data:"修改后的数据"}
     */
    Map<String, Object> update(Flow flow);

    /**
     * 获取流程对象
     *
     * @param id 流程主键
     * @return
     */
    Flow queryById(String id);

    /**
     * 获取流程集合
     *
     * @param folderId 流程目录id
     * @return
     */
    List<Flow> queryByFolder(String folderId);

    /**
     * 获取流程集合
     *
     * @param viewId 视图id
     * @return
     */
    List<Flow> queryByViewId(String viewId);

    /**
     * 获取流程排序最大序号
     *
     * @param viewId 视图id
     * @return
     */
    Integer queryMaxSort(String viewId);

    /**
     * 根据目录id,删除目录下所有流程
     *
     * @param folderId 目录id
     * @return
     */
    int deleteByFolder(String folderId);

    /**
     * 根据视图id,删除流程集合
     *
     * @param viewId 视图id
     * @return
     */
    int deleteByViewId(String viewId);

    /**
     * 根据流程名称，检索流程集合
     *
     * @param name   流程名称
     * @param viewId 视图id（可选），传入null时则检索未绑定视图的流程
     * @return
     */
    List<Flow> queryByName(String name, String viewId);

}
