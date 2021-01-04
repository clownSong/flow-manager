package com.song.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-19 16:28
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 删除过程
     *
     * @param id 过程主键
     * @return
     */
    int delete(@Param("id") String id);

    /**
     * 通过流程id,删除步骤集合
     *
     * @param flowId 流程id
     * @return
     */
    int deleteByFlow(@Param("flowId") String flowId);

    /**
     * 获取过程集合
     *
     * @param flowId 流程主体id
     * @return
     */
    List<Course> queryByFlowId(@Param("flowId") String flowId);

    /**
     * 获取过程对象
     *
     * @param id 过程主键
     * @return
     */
    Course queryById(@Param("id") String id);
}
