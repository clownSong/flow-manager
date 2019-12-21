package com.song.mapper;

import com.song.entity.BaseEntity;

/**
 * @author XiaoSong
 * @date 2019-12-20 16:42
 * mybatis mapper基类接口,规定每个mapper需具备的基本查询
 */
public interface BaseMapper {
    /**
     * 添加数据到实体类
     *
     * @param object
     * @return 返回影响的行数
     */
    int insert(BaseEntity object);

    /**
     * 更新数据
     *
     * @param object
     * @return 返回影响的行数
     */
    int update(BaseEntity object);

    /**
     * 删除数据
     *
     * @param id 数据表主键
     * @return 返回影响的行数
     */
    int delete(String id);
}
