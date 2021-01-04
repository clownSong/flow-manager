package com.song.web;

import com.song.FlowManagerApplicationTest;
import com.song.entity.Course;
import org.junit.Before;
import org.junit.Test;

/**
 * @author XiaoSong
 * @date 2020-01-17 9:11
 */
public class CourseControllerTest extends FlowManagerApplicationTest {
    Course course;

    @Before
    public void setUp() throws Exception {
        course = new Course();
        course.setName("测试过程2");
        course.setFlowId("903bbebd-f80a-4355-95f1-9347b4cccc");
    }

    @Test
    public void insert() throws Exception {
//        param.put("name",course.getName()+"2");
//        param.put("flowId",course.getFlowHistoryId());

        put("/course", course);
    }

    @Test
    public void update() throws Exception {
        param.put("name", course.getName() + "-update");
        param.put("flowId", "903bbebd-f80a-4355-95f1-9347b4cccc89");
        param.put("remark", "备注信息");
//        param.put("id","2c2313a8-b909-4f01-87b3-0074ebe1d4fa");
        post("/flowManager/course", param);
    }

    @Test
    public void delete() throws Exception {
        param.put("id", "2617c821-ab81-4924-a796-042ff189121c");
        delete("/flowManager/course", param);
    }

    @Test
    public void deleteByFlow() throws Exception {
        param.put("flowId", "903bbebd-f80a-4355-95f1-9347b4cccc");
        delete("/flowManager/course/byFlow", param);
    }

    @Test
    public void getByFlow() throws Exception {
        param.put("flowId", "d9dc0ab0-5875-4129-a829-5070a0ef0e1d");
        get("/course/queryByFlow", param);
    }

    @Test
    public void getById() throws Exception {
        param.put("id", "63dd503b-6d1f-4981-adaa-adb21b3f1353");
        get("/flowManager/course", param);
    }
}