package com.yskj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yskj.entity.FlowFolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-18 13:09
 */
@Mapper
public interface FlowFolderMapper extends BaseMapper<FlowFolder> {
    /**
     * 添加目录
     *
     * @param folder
     * @return
     */
    int insert(FlowFolder folder);

    /**
     * 删除目录
     *
     * @param id 目录主键
     * @return
     */
    int delete(@Param("id") String id);

    /**
     * 更新目录
     *
     * @param folder
     * @return
     */
    int update(FlowFolder folder);

    /**
     * 获取目录
     *
     * @param id 目录主键
     * @return
     */
    FlowFolder queryById(@Param("id") String id);

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
    List<FlowFolder> queryFolder(@Param("parentId") String parentId);

    /**
     * 根据目录名称查询目录
     *
     * @param folderName
     * @return 不存在返回null
     */
    FlowFolder queryByName(@Param("folderName") String folderName);

    /**
     * 获取目录排序最大序号
     *
     * @param parentId 可指定父元素id
     * @return
     */
    Integer queryMaxSort(@Param("parentId") String parentId);

    /**
     * 模糊查询目录
     * @param name 目录名称
     * @return
     */
    List<FlowFolder> search(@Param("name") String name);
}
