package com.yskj.service.impl;

import com.yskj.FlowManagerApplicationTest;
import com.yskj.entity.Flow;
import com.yskj.entity.FlowFolder;
import com.yskj.service.FlowService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XiaoSong
 * @date 2019-12-19 10:47
 */
public class FlowServiceImplTest extends FlowManagerApplicationTest {
    private Flow entity;
    private FlowFolder flowFolder;
    @Autowired
    private FlowService flowService;

    @Before
    public void initFlowEntity() {
        entity = new Flow();
        entity.setName("测试流程2");
        entity.setPerson("小宋,1231");
        flowFolder = new FlowFolder();
        flowFolder.setId("bef35ce4-a009-4877-b3d8-d1309d945717");
        // entity.setFolder(flowFolder);
    }

    @Test
    public void insert() {
        entity.setViewId("1");
        print(flowService.insert(entity));
    }

    @Test
    public void delete() {
        print(flowService.delete("a7a23b61-47c8-476e-8b7d-6db191e157b3"));
    }

    @Test
    public void update() {
        entity.setId("4c6a70f7-aa6c-46df-add8-ddf0d3494791");
        entity.setName("测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改" +
                "测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改" +
                "测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程-修改测试流程1");
        System.out.println(entity.getName().length());
        entity.setSort(1);
//        entity.setFolder(null);
        print(flowService.update(entity));
    }

    @Test
    public void queryById() {
        print(flowService.queryById("86d5199a-dc99-4be4-9afe-691cdf53dd65"));
    }

    @Test
    public void queryByFolder() {
        print(flowService.queryByFolder("bef35ce4-a009-4877-b3d8-d1309d945717"));
    }

    @Test
    public void queryByViewId() {
        print(flowService.queryByViewId("1"));
    }

    @Test
    public void queryMaxSort() {
        print(flowService.queryMaxSort("1"));
    }

    @Test
    public void deleteByFolder() {
        print(flowService.deleteByFolder("bef35ce4-a009-4877-b3d8-d1309d945717"));
    }

    @Test
    public void deleteByViewId() {
        print(flowService.deleteByViewId("1"));
    }

    @Test
    public void queryByName() {
        print(flowService.queryByName("测试流程", "1"));
    }
}