package com.song.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.entity.CourseCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-21 10:40
 * 流程过程判断条件mapper
 */
@Mapper
@Repository
public interface CourseConditionMapper extends BaseMapper<CourseCondition> {
    /**
     * 添加判断条件
     *
     * @param condition
     * @return
     */
    int insert(CourseCondition condition);

    /**
     * 修改判断条件
     *
     * @param condition
     * @return
     */
    int update(CourseCondition condition);

    /**
     * 获取判断条件对象
     *
     * @param id 判断条件主键
     * @return
     */
    CourseCondition queryById(@Param("id") String id);

    /**
     * 获取判断条件集合
     *
     * @param courseId 过程id
     * @return
     */
    List<CourseCondition> queryByCourse(@Param("courseId") String courseId);

    /**
     * 删除过程判断条件
     *
     * @param id 判断条件主键
     * @return
     */
    int delete(@Param("id") String id);

    /**
     * 删除判断条件集合
     *
     * @param courseId 过程主体id
     * @return
     */
    int deleteByCourse(@Param("courseId") String courseId);
}
