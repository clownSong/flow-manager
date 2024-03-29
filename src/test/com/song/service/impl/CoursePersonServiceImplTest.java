package com.yskj.service.impl;

import com.yskj.FlowManagerApplicationTest;
import com.yskj.entity.CoursePerson;
import com.yskj.service.CoursePersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XiaoSong
 * @date 2019-12-21 10:11
 */
public class CoursePersonServiceImplTest extends FlowManagerApplicationTest {
    @Autowired
    private CoursePersonService coursePersonService;
    private CoursePerson coursePerson;

    @Before
    public void setUp() throws Exception {
        coursePerson = new CoursePerson();
        coursePerson.setName("采购经理审批");
        coursePerson.setType(1);
        coursePerson.setCourseId("360b9c60-5bcc-468f-897d-19859d6ad029");
        coursePerson.setDispose(2);
        coursePerson.setPointId("");
    }

    @Test
    public void insert() {
        print(coursePersonService.insert(coursePerson));
    }

    @Test
    public void update() {
        coursePerson.setId("5be4d72e-49c4-43de-9de3-9ffcda825c50");
        coursePerson.setName("采购经理审批-修改");
        coursePerson.setCourseId("");
        print(coursePersonService.update(coursePerson));
    }

    @Test
    public void delete() {
        print(coursePersonService.delete("5be4d72e-49c4-43de-9de3-9ffcda825c50"));
    }

    @Test
    public void deleteByCourse() {
        print(coursePersonService.deleteByCourse("2617c821-ab81-4924-a796-042ff189121c"));
    }

    @Test
    public void queryPersons() {
        print(coursePersonService.queryByCourse("2617c821-ab81-4924-a796-042ff189121c"));
    }

    @Test
    public void queryById() {
        print(coursePersonService.queryById("5be4d72e-49c4-43de-9de3-9ffcda825c50"));
    }
}