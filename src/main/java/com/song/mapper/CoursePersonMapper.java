package com.song.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.entity.CoursePerson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-21 9:43
 * 过程审批实例mapper
 */
@Mapper
public interface CoursePersonMapper extends BaseMapper<CoursePerson> {
    /**
     * 增加过程审批实例
     *
     * @param person
     * @return
     */
    int insert(CoursePerson person);

    /**
     * 修改过程审批实例
     *
     * @param person
     * @return
     */
    int update(CoursePerson person);

    /**
     * 删除过程审批实例
     *
     * @param id 审批实例id
     * @return
     */
    int delete(@Param("id") String id);

    /**
     * 删除过程审批实例
     *
     * @param courseId 过程id
     * @return
     */
    int deleteByCourse(@Param("courseId") String courseId);

    /**
     * 所有查询过程实例
     *
     * @param courseId 必须制定过程id
     * @return
     */
    List<CoursePerson> queryByCourse(@Param("courseId") String courseId);

    /**
     * 查询过程实例
     *
     * @param id 过程实例主键
     * @return
     */
    CoursePerson queryById(@Param("id") String id);
}
