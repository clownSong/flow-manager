package com.yskj.service.impl;

import com.yskj.FlowManagerApplicationTest;
import com.yskj.model.SystemPersonModel;
import com.yskj.service.SystemPersonService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class SystemPersonServiceImplTest extends FlowManagerApplicationTest {
    @Resource
    private SystemPersonService systemPersonService;

    @Test
    public void getPerson() {
        List<SystemPersonModel> modelList = systemPersonService.getByPerson("20#54E3J", "2");
        System.out.println(modelList);
    }
}
