package com.yskj.service.impl;

import com.yskj.entity.Flow;
import com.yskj.entity.FlowHistory;
import com.yskj.mapper.FlowHistoryMapper;
import com.yskj.service.FlowHistoryService;
import com.yskj.service.FlowService;
import com.yskj.utils.DateUtils;
import com.yskj.utils.EntityVerifyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FlowHistoryServiceImpl implements FlowHistoryService {
    @Resource
    private FlowHistoryMapper flowHistoryMapper;
    @Resource
    private FlowService flowService;

    @Override
    public Map<String, Object> insert(FlowHistory flowHistory) {
        Map<String, Object> verify = new HashMap<>();
        verify.put("flowId", "请指定流程主体id");
        verify.put("person", "请指定流程使用人信息");
        verify = EntityVerifyUtils.verifyString(verify, flowHistory);
        if (verify.isEmpty()) {
            Flow flow = flowService.queryById(flowHistory.getFlowId());
            if (Objects.isNull(flow)) {
                verify.put("flowId", "流程主体不存在");
                return verify;
            }
            flowHistory.setName(flow.getName());
            flowHistory.setRemark(flow.getRemark());
            flowHistory.setFolderId(flow.getFolderId());
            flowHistory.setViewId(flow.getViewId());
            if (StringUtils.isBlank(flowHistory.getId())) {
                flowHistory.setId(UUID.randomUUID().toString());
            }
            if (StringUtils.isBlank(flowHistory.getDate())) {
                flowHistory.setDate(DateUtils.getNowDate());
            }
            flowHistoryMapper.insert(flowHistory);
            verify.put("data", flowHistory);
        }
        return verify;
    }

    @Override
    public Map<String, Object> insertByFlow(Flow flow, String personInfo) {
        FlowHistory flowHistory = new FlowHistory();
        flowHistory.setFlowId(flow.getId());
        flowHistory.setPerson(personInfo);
        return insert(flowHistory);
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public FlowHistory queryById(String id) {
        return null;
    }

    @Override
    public List<FlowHistory> queryByParam(FlowHistory flowHistory) {
        return null;
    }
}
