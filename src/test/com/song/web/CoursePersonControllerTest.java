package com.yskj.web;

import com.yskj.FlowManagerApplicationTest;
import com.yskj.entity.CoursePerson;
import org.junit.Test;

import java.util.HashMap;

public class CoursePersonControllerTest extends FlowManagerApplicationTest {
    @Test
    public void delete() throws Exception {
        delete("/coursePerson?id=01010c6b-d54c-4c7b-8a94-3c5c319b9961", new HashMap<>());
    }

    @Test
    public void update() throws Exception {
        CoursePerson cp = new CoursePerson();
        cp.setId("b18781b1-a312-489b-b714-306f1fcd5ae7");
        cp.setCourseId("360b9c60-5bcc-468f-897d-19859d6ad029");
        cp.setName("采购经理审批2");
        cp.setDispose(1);
        cp.setType(1);
        post("/coursePerson?id=01010c6b-d54c-4c7b-8a94-3c5c319b9961", cp);
    }
}