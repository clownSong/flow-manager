package com.yskj.web;

import com.yskj.FlowManagerApplicationTest;
import com.yskj.entity.Flow;
import com.yskj.entity.FlowInstance;
import com.yskj.model.FlowInstanceModel;
import org.junit.Test;

import java.util.HashMap;

public class FlowInstanceControllerTest extends FlowManagerApplicationTest {
    private FlowInstanceModel flowInstanceModel;

    @Override
    public void before() {
        super.before();
        flowInstanceModel = new FlowInstanceModel();
        Flow flow = new Flow();
        flow.setId("d9dc0ab0-5875-4129-a829-5070a0ef0e1d");
        flowInstanceModel.setFlow(flow);
        FlowInstance instance = new FlowInstance();
        instance.setTitle("测试审批标题");
        instance.setRemark("备注信息");
        instance.setModuleId("123");
        instance.setModuleTypeId("321");
        instance.setUserId("123321");
        instance.setUserName("test");
        flowInstanceModel.setFlowInstance(instance);
    }

    @Test
    public void startFlow() throws Exception {
        put("/api/flowInstance/startFlow", flowInstanceModel);
    }

    @Test
    public void cancelFlow() throws Exception {
        post("/api/flowInstance/cancelFlow?id=e73b0cf8-e746-4a75-bb69-c78a784734c4", new HashMap<>());
    }

    @Test
    public void deleteFlow() throws Exception {
        delete("/api/flowInstance?id=e73b0cf8-e746-4a75-bb69-c78a784734c4", new HashMap<>());
    }

    @Test
    public void getById() {
    }

    @Test
    public void getListByUser() {
    }

    @Test
    public void getAllList() {
    }
}