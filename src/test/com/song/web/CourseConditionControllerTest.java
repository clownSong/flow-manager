package com.song.web;

import com.song.FlowManagerApplicationTest;
import com.song.entity.CourseCondition;
import org.junit.Before;
import org.junit.Test;

/**
 * @author XiaoSong
 * @date 2020-01-17 9:48
 */
public class CourseConditionControllerTest extends FlowManagerApplicationTest {

    CourseCondition courseCondition;

    @Before
    public void setUp() throws Exception {
        courseCondition = new CourseCondition();
        courseCondition.setCourseId("398b17cc-2587-4fb7-9a05-14577b21bd96");
        courseCondition.setType(1);
        courseCondition.setFieldName("t");
        courseCondition.setValue("1001");
    }

    @Test
    public void insert() throws Exception {
        param.put("courseId", courseCondition.getCourseId());
        param.put("fieldName", courseCondition.getFieldName());
        param.put("type", courseCondition.getType() + "");
        param.put("value", courseCondition.getValue());
        put("/flowManager/courseCondition", param);
    }

    @Test
    public void update() throws Exception {
        param.put("courseId", courseCondition.getCourseId());
        param.put("fieldName", courseCondition.getFieldName() + "tt");
        param.put("type", courseCondition.getType() + "");
        param.put("value", courseCondition.getValue());
        param.put("id", "f2e3ea33-7b9a-4209-a69f-acea0bbdb7cd");
        post("/flowManager/courseCondition", param);
    }

    @Test
    public void getById() throws Exception {
        param.put("id", "f2e3ea33-7b9a-4209-a69f-acea0bbdb7cd");
        get("/flowManager/courseCondition", param);
    }

    @Test
    public void getByCourseId() throws Exception {
        param.put("courseId", courseCondition.getCourseId());
        get("/flowManager/courseCondition/byCourse", param);
    }

    @Test
    public void delete() throws Exception {
        param.put("id", "f2e3ea33-7b9a-4209-a69f-acea0bbdb7cd");
        delete("/flowManager/courseCondition", param);
    }

    @Test
    public void deleteByCourse() throws Exception {
        param.put("courseId", courseCondition.getCourseId());
        delete("/flowManager/courseCondition/byCourse", param);
    }
}