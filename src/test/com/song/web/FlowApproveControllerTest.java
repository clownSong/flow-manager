package com.song.web;

import com.song.FlowManagerApplicationTest;
import com.song.entity.FlowApprove;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FlowApproveControllerTest extends FlowManagerApplicationTest {
    @Test
    public void getFlowApproves() throws Exception {
        Map<String, String> param = new HashMap<>();

        get("/flowApprove?pageNumber=2&pageSize=10&search=''&type=0&userId=3E2BAFBA-0BA7-4AFA-8DF3-A8D747B010B5", param);
    }

    @Test
    public void updateState() throws Exception {
        FlowApprove approve = new FlowApprove();
        approve.setId("1297002438763196417");
        approve.setState((byte) 1);
        post("/flowApprove/read", approve);
    }
}
