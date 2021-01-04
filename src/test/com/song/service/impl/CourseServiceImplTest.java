package com.song.service.impl;

import com.song.FlowManagerApplicationTest;
import com.song.entity.Course;
import com.song.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XiaoSong
 * @date 2019-12-21 8:42
 */
public class CourseServiceImplTest extends FlowManagerApplicationTest {
    private Course course;
    @Autowired
    private CourseService courseService;

    @Before
    public void before() {
        course = new Course();
        course.setName("过程1");
        course.setRemark("remark");
        course.setJudge(true);
        course.setCountersign(false);
        course.setFreedom(true);
        course.setOvertime(-1);
        course.setOvertimeDispose(3);
        course.setFlowId("bef35ce4-a009-4877-b3d8-d1309d945717");
    }

    @Test
    public void insert() {
        print(courseService.insert(course));
    }

    @Test
    public void update() {
        course.setId("a9199914-41ea-44c1-9e17-4bac5cb93b0c");
        course.setName("过程-修改");
        course.setRemark("");
        course.setOvertime(1);
        course.setOvertimeDispose(2);
        course.setJudge(true);
        course.setCountersign(true);
        course.setFreedom(true);
        course.setParentCourseId(null);
        print(courseService.update(course));
    }

    @Test
    public void delete() {
        print(courseService.delete("a9199914-41ea-44c1-9e17-4bac5cb93b0c"));
    }

    @Test
    public void deleteByFolder() {
        print(courseService.deleteByFlow("d9dc0ab0-5875-4129-a829-5070a0ef0e1d"));
    }

    @Test
    public void queryByFlowId() {
        print(courseService.queryByFlowId("1"));
    }

    @Test
    public void queryById() {
        print(courseService.queryById("ee176b27-5911-41c9-aee1-ebe2fc8cf53e"));
    }
}