package com.song.web;

import com.song.entity.Course;
import com.song.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2020-01-17 8:56
 */
@RestController
@RequestMapping("/flowManager/course")
public class CourseController extends BaseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 添加过程
     *
     * @param course {@link com.song.entity.Course}
     * @return {@link Map<String,Object>}
     * state:-1|0=(添加失败),1=(添加成功)
     * msg:提示信息（添加失败读取）
     * data:添加成功后数据（添加成功读取）
     */
    @PutMapping
    public Map<String, Object> insert(Course course) {
        return courseService.insert(course);
    }

    /**
     * 更新过程
     * 注意：更新过程不会修改flowId信息
     * @param course {@link Course}
     * @return {@link Map<String,Object>}
     * state:-1|0=修改失败,1=修改成功
     * msg:提示信息（修改失败读取）
     * data:更新成功后数据（修改成功读取）
     */
    @PostMapping
    public Map<String, Object> update(Course course) {
        return courseService.update(course);
    }

    /**
     * 删除过程
     *
     * @param id 过程id
     * @return {@link Map<String,Object>}
     * state:删除影响的行数
     */
    @DeleteMapping
    public Map<String, Object> delete(String id) {
        return courseService.delete(id);
    }

    /**
     * 通过流程id删除过程
     *
     * @param flowId {@link com.song.entity.Flow} 流程id
     * @return {@link Map<String,Object>}
     * state:删除影响的行数
     */
    @DeleteMapping("/byFlow")
    public Map<String, Object> deleteByFlow(String flowId) {
        return courseService.deleteByFlow(flowId);
    }

    /**
     * 通过流程id获取过程集合
     *
     * @param flowId {@link com.song.entity.Flow} 流程id
     * @return {@link List<Course>} 过程集合
     */
    @GetMapping("/byFlow")
    public List<Course> getByFlow(String flowId) {
        return courseService.queryByFlowId(flowId);
    }

    /**
     * 获取过程
     *
     * @param id 过程id
     * @return {@link Course}
     */
    @GetMapping
    public Course getById(String id) {
        return courseService.queryById(id);
    }
}
