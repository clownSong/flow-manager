package com.song.service.impl;

import com.song.FlowManagerApplicationTest;
import com.song.model.SystemPersonModel;
import com.song.service.SystemPersonService;
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
