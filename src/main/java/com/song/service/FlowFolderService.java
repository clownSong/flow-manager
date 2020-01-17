package com.song.service;

import com.song.entity.FlowFolder;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-18 13:22
 * 流程目录服务接口
 */
public interface FlowFolderService {

    /**
     * 添加目录
     *
     * @param folder
     * @return
     */
    Map<String, Object> insert(FlowFolder folder);

    /**
     * 删除目录
     *
     * @param id 目录主键
     * @return
     */
    Map<String, Object> delete(String id);

    /**
     * 更新目录
     *
     * @param folder
     * @return
     */
    Map<String, Object> update(FlowFolder folder);

    /**
     * 获取目录
     *
     * @param id 目录主键
     * @return
     */
    FlowFolder queryById(String id);

    /**
     * 获取根目录集合
     *
     * @return
     */
    List<FlowFolder> queryRoot();

    /**
     * 获取指定目录下子目录集合
     *
     * @param parentId 目录id
     * @return
     */
    List<FlowFolder> queryFolder(String parentId);

    /**
     * 根据目录名称查询目录
     *
     * @param folderName
     * @return 不存在返回null
     */
    FlowFolder queryByName(String folderName);

    /**
     * 获取目录排序最大序号
     *
     * @param parentId 可指定上级目录id，不指定时传null
     * @return
     */
    Integer getMaxSort(String parentId);
}
