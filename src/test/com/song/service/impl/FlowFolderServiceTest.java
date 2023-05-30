package com.yskj.service.impl;

import com.alibaba.fastjson.JSON;
import com.yskj.FlowManagerApplicationTest;
import com.yskj.entity.FlowFolder;
import com.yskj.service.FlowFolderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-18 14:47
 */
public class FlowFolderServiceTest extends FlowManagerApplicationTest {
    @Autowired
    private FlowFolderService flowFolderService;

    @Test
    public void insert() {
        FlowFolder flowFolder = new FlowFolder();
        flowFolder.setName("测试子目录2");
        flowFolder.setParentId("4b1f0d0e-aca6-4ef2-bfa3-b77daf6fb1ea");
        System.out.println(flowFolder.getName().length());
        System.out.println(JSON.toJSONString(flowFolderService.insert(null)));
    }

    @Test
    public void update() {
        FlowFolder flowFolder = new FlowFolder();
        flowFolder.setId("bef35ce4-a009-4877-b3d8-d1309d945717");
        flowFolder.setName("测试目录2");
        flowFolder.setSort(0);
        System.out.println(JSON.toJSONString(flowFolderService.update(flowFolder)));
    }

    @Test
    public void query() {
        FlowFolder flowFolder = flowFolderService.queryById("81c58747-045e-47af-a9db-9964cd9fc317");
        System.out.println(JSON.toJSONString(flowFolder));

        flowFolder = flowFolderService.queryByName("测试目录-修改2");
        System.out.println(JSON.toJSONString(flowFolder));

        List<FlowFolder> folders = flowFolderService.queryFolder("4b1f0d0e-aca6-4ef2-bfa3-b77daf6fb1ea");
        System.out.println(JSON.toJSONString(folders));

        folders = flowFolderService.queryRoot();
        System.out.println(JSON.toJSONString(folders));
    }

    @Test
    public void delete() {
        String id = "1384eb25-ffc6-4ad5-a4ec-95d0f5415f32";
        flowFolderService.delete(id);
        id = "cc832edf-fcf2-474a-b9e7-ca42f0550d6f";
        System.out.println(flowFolderService.delete(id));
        id = "e8bbf0cc-be5c-4418-9161-3dd877f4164d";
        System.out.println(flowFolderService.delete(id));
    }
}
