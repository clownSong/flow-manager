package com.song.service.impl;

import com.song.FlowManagerApplicationTest;
import com.song.entity.Flow;
import com.song.entity.FlowInstance;
import com.song.model.FlowInstanceModel;
import com.song.service.FlowInstanceService;
import org.junit.Test;

import javax.annotation.Resource;

public class FlowInstanceServiceImplTest extends FlowManagerApplicationTest {
    @Resource
    private FlowInstanceService flowInstanceService;
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
    public void startFlow() {
        super.print(flowInstanceService.startFlow(flowInstanceModel));
    }

}
