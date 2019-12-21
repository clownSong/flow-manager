package com.song.service.impl;

import com.song.FlowManagerApplicationTest;
import com.song.entity.CourseCondition;
import com.song.service.CourseConditionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XiaoSong
 * @date 2019-12-21 11:22
 */
public class CourseConditionServiceImplTest extends FlowManagerApplicationTest {
    private CourseCondition courseCondition;
    @Autowired
    private CourseConditionService courseConditionService;

    @Before
    public void setUp() {
        courseCondition = new CourseCondition();
        courseCondition.setCourseId("2617c821-ab81-4924-a796-042ff189121c");
        courseCondition.setFieldName("name");
//        courseCondition.setRelation(1);
        courseCondition.setType(1);
        courseCondition.setValue("123");
    }

    @Test
    public void insert() {
        print(courseConditionService.insert(courseCondition));
    }

    @Test
    public void update() {
        courseCondition.setId("2dfada30-5b1c-4c6e-aa1b-7e9bea7b5b2e");
        courseCondition.setType(2);
        courseCondition.setFieldName("333");
        courseCondition.setValue("098");
        print(courseConditionService.update(courseCondition));
    }

    @Test
    public void queryById() {
        print(courseConditionService.queryById("2dfada30-5b1c-4c6e-aa1b-7e9bea7b5b2e"));
    }

    @Test
    public void queryByCourse() {
        print(courseConditionService.queryByCourse("2617c821-ab81-4924-a796-042ff189121c"));
    }

    @Test
    public void delete() {
        print(courseConditionService.delete("2dfada30-5b1c-4c6e-aa1b-7e9bea7b5b2e"));
    }

    @Test
    public void deleteByCourse() {
        print(courseConditionService.deleteByCourse("2617c821-ab81-4924-a796-042ff189121c"));
    }
}