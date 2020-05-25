package com.song.web;

import com.song.entity.CoursePerson;
import com.song.service.CoursePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author XiaoSong
 * @date 2020-01-18 10:31
 */
@RestController
@RequestMapping("/flowManager/coursePerson")
public class CoursePersonController extends BaseController {
    private CoursePersonService coursePersonService;

    @Autowired
    public CoursePersonController(CoursePersonService coursePersonService) {
        this.coursePersonService = coursePersonService;
    }

    /**
     * 添加过程人员
     * @param coursePerson {@link CoursePerson}
     * @return {@link Map<String,Object>}
     * state:-1|0=添加失败,1=添加成功
     * msg:添加失败提示信息
     * data:添加成功返回的数据
     */
    @PutMapping
    public Map<String,Object> insert(CoursePerson coursePerson){
        return coursePersonService.insert(coursePerson);
    }

    /**
     * 更新过程人员
     * @param coursePerson {@link CoursePerson}
     * @return {@link Map<String,Object>}
     * state:-1|0=添加失败.1=添加成功
     * msg:添加失败提示信息
     * data:添加成功返回的数据
     */
    @PostMapping
    public Map<String,Object> update(CoursePerson coursePerson){
        return coursePersonService.update(coursePerson);
    }

    /**
     * 删除过程人员
     * @param id {@link CoursePerson} 主键
     * @return {@link Map<String,Object>}
     * state:-1|0=添加失败，1=添加成功
     */
    @DeleteMapping
    public Map<String,Object> delete(String id){
        return coursePersonService.delete(id);
    }
}
