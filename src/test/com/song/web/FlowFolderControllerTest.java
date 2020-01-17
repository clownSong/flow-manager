package com.song.web;

import com.song.FlowManagerApplicationTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-21 13:39
 */
public class FlowFolderControllerTest extends FlowManagerApplicationTest {

    @Test
    public void insert() throws Exception {
        Map<String, String> param = new HashMap<>(4);
        param.put("name", "系统根目录");
        post("/flowManager/flowFolder", param);
    }

    @Test
    public void update() throws Exception {
        Map<String, String> param = new HashMap<>(4);
        param.put("name", "系统根目录-修改");
        param.put("id", "e5f6f3cf-752c-427c-a8e7-e503d8c71bc9");
        param.put("sort", "2");
        put("/flowManager/flowFolder", param);
    }

    @Test
    public void delete() throws Exception {
//        param.put("id","");
        delete("/flowManager/flowFolder", param);
    }

    @Test
    public void queryRoot() throws Exception {
        get("/flowManager/flowFolder/getRoot", param);
    }

    @Test
    public void queryByParent() throws Exception {
        param.put("parentId", "bef35ce4-a009-4877-b3d8-d1309d945717");
        get("/flowManager/flowFolder/getByParent", param);
    }

    @Test
    public void queryById() throws Exception {
        param.put("id", "bef35ce4-a009-4877-b3d8-d1309d945717");
        get("/flowManager/flowFolder/getById", param);
    }
}