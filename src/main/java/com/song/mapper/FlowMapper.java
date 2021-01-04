package com.song.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.entity.Flow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-19 9:29
 * 流程主体mapper
 */
@Mapper
public interface FlowMapper extends BaseMapper<Flow> {
    /**
     * 添加流程
     *
     * @param flow
     * @return {state:"操作状态",msg:"提示信息",data:"修改后的数据"}
     */
    int insert(Flow flow);

    /**
     * 删除流程
     *
     * @param id 主键
     * @return 失败返回-1
     */
    int delete(@Param("id") String id);

    /**
     * 修改流程
     *
     * @param flow
     * @return {state:"操作状态",msg:"提示信息",data:"修改后的数据"}
     */
    int update(Flow flow);

    /**
     * 获取流程对象
     *
     * @param id 流程主键
     * @return
     */
    Flow queryById(@Param("id") String id);

    /**
     * 获取流程集合
     *
     * @param folderId 流程目录id
     * @return
     */
    List<Flow> queryByFolder(@Param("folderId") String folderId);

    /**
     * 获取流程集合
     *
     * @param viewId 视图id
     * @return
     */
    List<Flow> queryByViewId(@Param("viewId") String viewId);

    /**
     * 根据流程名称，检索流程集合
     *
     * @param name   流程名称
     * @param viewId 视图id（可选），传入null时则检索未绑定视图的流程
     * @return
     */
    List<Flow> queryByName(@Param("name") String name, @Param("viewId") String viewId);

    /**
     * 获取流程排序最大序号
     *
     * @param viewId 视图id
     * @return
     */
    Integer queryMaxSort(@Param("viewId") String viewId);

    /**
     * 根据目录id,删除目录下所有流程
     *
     * @param folderId 目录id
     * @return
     */
    int deleteByFolder(@Param("folderId") String folderId);

    /**
     * 根据视图id,删除流程集合
     *
     * @param viewId 视图id
     * @return
     */
    int deleteByViewId(@Param("viewId") String viewId);
}
