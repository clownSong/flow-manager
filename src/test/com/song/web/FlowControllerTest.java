package com.song.web;

import com.song.FlowManagerApplicationTest;
import com.song.entity.Flow;
import com.song.entity.FlowFolder;
import org.junit.Before;
import org.junit.Test;

/**
 * @author XiaoSong
 * @date 2020-01-14 9:47
 */
public class FlowControllerTest extends FlowManagerApplicationTest {
    Flow flow;

    @Before
    public void setUp() throws Exception {
        flow = new Flow();
        flow.setName("测试流程-web");
        flow.setPerson("1001,song");
        flow.setRemark("备注信息");
        FlowFolder ff = new FlowFolder();
        ff.setId("4b1f0d0e-aca6-4ef2-bfa3-b77daf6fb1ea");
        flow.setFolder(ff);
    }

    @Test
    public void getFlowByFolder() {
    }

    @Test
    public void insert() throws Exception {
        param.put("name", flow.getName() + "2");
        param.put("person", flow.getPerson() + "2");
        param.put("remark", flow.getRemark() + "2");
        param.put("folder.id", flow.getFolder().getId());
        post("/flowManager/flow", param);
    }

    @Test
    public void update() throws Exception {
        param.put("name", flow.getName() + "-update");
        param.put("person", flow.getPerson());
        param.put("remark", flow.getRemark() + "-update");
        param.put("folder.id", flow.getFolder().getId());
        param.put("id", "b72998f9-b678-4e3a-b924-3c7f0d95c8ab");
        param.put("viewId", "321");
        put("/flowManager/flow", param);
    }

    @Test
    public void delete() throws Exception {
        param.put("id", "04961e07-7a37-4541-91aa-8d6e6a9b0dd7");
        delete("/flowManager/flow", param);
    }

    @Test
    public void getById() throws Exception {
        param.put("id", "64754778-59e1-41be-8bb1-1bc5af57abdf");
        get("/flowManager/flow", param);
    }

    @Test
    public void getByView() throws Exception {
        param.put("viewId", "321");
        get("/flowManager/flow/getByView", param);
    }

    @Test
    public void deleteByFolder() throws Exception {
        param.put("folderId", "4b1f0d0e-aca6-4ef2-bfa3-b77daf6fb1ea");
        delete("/flowManager/flow/deleteByFolder", param);
    }

    @Test
    public void deleteByView() throws Exception {
        param.put("viewId", "321");
        delete("/flowManager/flow/deleteByView", param);
    }

    @Test
    public void searchFlow() throws Exception {
        param.put("name", "web23");
        get("/flowManager/flow/searchFlow", param);
    }
}