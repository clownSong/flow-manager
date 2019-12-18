package com.song.service;

import com.alibaba.fastjson.JSON;
import com.song.FlowManagerApplicationTest;
import com.song.entity.FlowFolder;
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
        System.out.println(JSON.toJSONString(flowFolderService.insert(flowFolder)));
    }

    @Test
    public void update() {
        FlowFolder flowFolder = new FlowFolder();
        flowFolder.setId("5742d495-a099-4b17-a193-02eff158ba59");
        flowFolder.setName("测试子目录-修改为子子目录");
        flowFolder.setParentId("4b1f0d0e-aca6-4ef2-bfa3-b77daf6fb1ea");
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
