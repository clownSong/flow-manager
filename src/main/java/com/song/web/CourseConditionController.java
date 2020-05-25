package com.song.web;

import com.song.entity.CourseCondition;
import com.song.service.CourseConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2020-01-17 9:26
 */
@RestController
@RequestMapping("/flowManager/courseCondition")
public class CourseConditionController extends BaseController {
    private CourseConditionService courseConditionService;

    @Autowired
    public CourseConditionController(CourseConditionService courseConditionService) {
        this.courseConditionService = courseConditionService;
    }

    /**
     * 添加判断条件
     *
     * @param courseCondition {@link CourseCondition}
     * @return {@link Map<String,Object>}
     * state:"-1|0"=添加失败，"1"="添加成功"
     * msg:提示信息
     * data:添加成功后的数据，添加失败数据不返回
     */
    @PutMapping
    public Map<String, Object> insert(CourseCondition courseCondition) {
        return courseConditionService.insert(courseCondition);
    }

    /**
     * 更新判断条件
     *
     * @param courseCondition {@link CourseCondition}
     * @return {@link Map<String,Object>}
     * state:-1|0,更新失败,1=更新成功
     * msg:提示信息
     * data:修改成功后返回数据，失败时无数据返回
     */
    @PostMapping
    public Map<String, Object> update(CourseCondition courseCondition) {
        return courseConditionService.update(courseCondition);
    }

    /**
     * 获取判断条件对象
     *
     * @param id {@link CourseCondition} 主键
     * @return {@link CourseCondition} 实例
     */
    @GetMapping
    public CourseCondition getById(String id) {
        return courseConditionService.queryById(id);
    }

    /**
     * 获取判断条件集合
     *
     * @param courseId {@link com.song.entity.Course} 过程主键
     * @return {@link List<CourseCondition>}
     */
    @GetMapping("byCourse")
    public List<CourseCondition> getByCourseId(String courseId) {
        return courseConditionService.queryByCourse(courseId);
    }

    /**
     * 删除判断条件
     *
     * @param id {@link CourseCondition} 过程主键
     * @return {@link Map<String,Object>}
     * state:-1|0=删除失败，1=删除成功
     */
    @DeleteMapping
    public Map<String, Object> delete(String id) {
        return courseConditionService.delete(id);
    }

    /**
     * 删除判断条件集合（通过过程主键
     *
     * @param courseId {@link com.song.entity.Course} 过程主键
     * @return {@link Map<String,Object>}
     * state:返回影响的行数（0|-1=删除失败）
     */
    @DeleteMapping("/byCourse")
    public Map<String, Object> deleteByCourse(String courseId) {
        return courseConditionService.deleteByCourse(courseId);
    }

}
