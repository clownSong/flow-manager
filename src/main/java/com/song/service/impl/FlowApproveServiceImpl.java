package com.song.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.song.entity.CoursePerson;
import com.song.entity.CoursePersonHistory;
import com.song.entity.FlowApprove;
import com.song.entity.FlowInstance;
import com.song.mapper.FlowApproveMapper;
import com.song.model.CourseHistoryResultModel;
import com.song.model.MyFlowApprove;
import com.song.model.RequestListParamModel;
import com.song.model.SystemPersonModel;
import com.song.service.*;
import com.song.utils.DateUtils;
import com.song.utils.EntityVerifyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FlowApproveServiceImpl implements FlowApproveService {
    @Resource
    private FlowApproveMapper flowApproveMapper;
    @Resource
    private CoursePersonService coursePersonService;
    @Resource
    private CourseHistoryService courseHistoryService;
    @Resource
    private CoursePersonHistoryService coursePersonHistoryService;
    @Resource
    private FlowInstanceService flowInstanceService;

    @Override
    public FlowApprove insert(FlowApprove approve) {
        Map<String, Object> verifyMap = new HashMap<>();
        verifyMap.put("flowInstanceId", "未指定流程实例id");
        verifyMap.put("courseHistoryId", "未指定过程记录id");
        verifyMap.put("sendUserId", "未指定发送人id");
        verifyMap.put("sendUserName", "未指定发送人名称");
        verifyMap.put("acceptUserId", "未指定接收人id");
        verifyMap.put("acceptUserName", "未指定接收人名称");
        verifyMap = EntityVerifyUtils.verifyString(verifyMap, approve);
        if (verifyMap.isEmpty()) {
            if (StringUtils.isBlank(approve.getSendDatetime())) {
                approve.setSendDatetime(DateUtils.getNowDatetime());
            }
            approve.setState((byte) 0);
            flowApproveMapper.insert(approve);
        }
        return approve;
    }

    @Override
    public int delete(String id) {
        return flowApproveMapper.deleteById(id);
    }

    @Override
    public int deleteByFlowInstance(String flowInstanceId) {
        return flowApproveMapper.delete(Wrappers.<FlowApprove>lambdaUpdate().eq(FlowApprove::getFlowInstanceId, flowInstanceId));
    }

    @Override
    public int updateState(FlowApprove flowApprove) {
        return flowApproveMapper.updateById(flowApprove);
    }

    @Override
    public FlowApprove queryById(String id) {
        return (FlowApprove) flowApproveMapper.selectById(id);
    }

    @Override
    public List<FlowApprove> queryByCourseId(String courseHistoryId) {
        return flowApproveMapper.selectList(Wrappers.lambdaQuery(FlowApprove.class).eq(FlowApprove::getCourseHistoryId, courseHistoryId));
    }

    @Override
    public int deleteByCourseId(String courseHistoryId) {
        return flowApproveMapper.delete(Wrappers.lambdaUpdate(FlowApprove.class).eq(FlowApprove::getCourseHistoryId, courseHistoryId));
    }

    @Override
    public List<FlowApprove> insertByCourseId(String courseId, FlowInstance flowInstance, String courseHistoryId) {
        ArrayList<FlowApprove> approveList = new ArrayList<>();
        List<CoursePerson> people = coursePersonService.queryByCourse(courseId);
        people.forEach(coursePerson -> {
            String pointId = coursePerson.getPointId();
            pointId = Objects.isNull(pointId) ? flowInstance.getUserId() : pointId;
            List person = coursePersonService.getPersonListByType(pointId,coursePerson.getType()+"");
            insertFlowApprove(flowInstance, courseHistoryId, approveList, person);
        });
        return approveList;
    }

    private void insertFlowApprove(FlowInstance flowInstance, String courseHistoryId, ArrayList<FlowApprove> approveList, List person) {
        person.forEach(p -> {
            SystemPersonModel spm = (SystemPersonModel) p;
            FlowApprove flowApprove = new FlowApprove();
            flowApprove.setSendUserId(flowInstance.getUserId());
            flowApprove.setSendUserName(flowInstance.getUserName());
            flowApprove.setAcceptUserId(spm.getId());
            flowApprove.setAcceptUserName(spm.getName());
            flowApprove.setFlowInstanceId(flowInstance.getId());
            flowApprove.setCourseHistoryId(courseHistoryId);
            insert(flowApprove);
            approveList.add(flowApprove);
        });
    }

    @Override
    public boolean isAutoApprove(FlowInstance flowInstance, String courseHistoryId) {
        List<FlowApprove> approveList = queryByCourseId(courseHistoryId);
        FlowApprove approve;
        for (int x = 0; x < approveList.size(); x++) {
            approve = approveList.get(x);
            if (approve.getState() <= 1 && approve.getAcceptUserId().equals(flowInstance.getUserId())) {
//                审批人与发起人为同一人，直接审批通过处理
                pass(approve);
//                处理下一步过程
                SystemPersonModel personModel = new SystemPersonModel();
                personModel.setId(flowInstance.getUserId());
                personModel.setName(flowInstance.getUserName());
                courseHistoryService.goNext(courseHistoryId, flowInstance.getId(), personModel);
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> pass(FlowApprove flowApprove) {
        Map<String, Object> verify = new HashMap<>(16);
        verify.put("flowInstanceId", "未指定流程发起实例id");
        verify.put("courseHistoryId", "未指定审批过程记录id");
        verify.put("id", "指定的审批人员消息不存在");
        verify = EntityVerifyUtils.verifyString(verify, flowApprove);
        if (verify.isEmpty()) {
            flowApprove.setState((byte) 2);
            flowApprove.setApproveDatetime(DateUtils.getNowDatetime());
            updateState(flowApprove);
        }
        return verify;
    }

    @Override
    public boolean isApprove(String courseHistoryId) {
        List<FlowApprove> approveList = queryByCourseId(courseHistoryId);
        for (int i = 0; i < approveList.size(); i++) {
            if (approveList.get(i).getState() < 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void insertByCourseHistoryId(String courseHistoryId, String flowInstanceId, SystemPersonModel sendPerson) {
        List<CoursePersonHistory> coursePersonHistories = coursePersonHistoryService.queryByCourseHistoryId(courseHistoryId);
        FlowInstance instance = flowInstanceService.queryById(flowInstanceId);
        for (int i = 0; i < coursePersonHistories.size(); i++) {
            String pointId = coursePersonHistories.get(i).getPointId();
            pointId = Objects.isNull(pointId) ? instance.getUserId() : pointId;
            List<SystemPersonModel> persons = coursePersonService.getPersonListByType(pointId, coursePersonHistories.get(i).getType() + "");
            persons.forEach(p -> {
                FlowApprove flowApprove = new FlowApprove();
                flowApprove.setSendUserId(sendPerson.getId());
                flowApprove.setSendUserName(sendPerson.getName());
                flowApprove.setAcceptUserId(p.getId());
                flowApprove.setAcceptUserName(p.getName());
                flowApprove.setFlowInstanceId(flowInstanceId);
                flowApprove.setCourseHistoryId(courseHistoryId);
                insert(flowApprove);
            });
        }
    }

    @Override
    public void cancel(String flowInstanceId,SystemPersonModel sendPerson) {
        FlowInstance instance = flowInstanceService.queryById(flowInstanceId);
        List<FlowApprove> approves = queryByFlowInstanceId(flowInstanceId);
        approves.forEach(approve2 -> {
            if (approve2.getState() <= 1) {
                approve2.setState((byte) 7);
                approve2.setApproveContent("主流程被取消");
                updateState(approve2);
            }
        });
        if(Objects.isNull(sendPerson)){
            sendPerson = new SystemPersonModel();
            sendPerson.setId(instance.getUserId());
            sendPerson.setName(instance.getUserName());
        }
        flowInstanceService.flowChange(instance,sendPerson);
    }

    @Override
    public boolean cancel(FlowApprove approve) {
        SystemPersonModel sendPerson = new SystemPersonModel();
        sendPerson.setId(approve.getAcceptUserId());
        sendPerson.setName(approve.getAcceptUserName());
        cancel(approve.getFlowInstanceId(),sendPerson);
        approve.setState((byte) 5);
        updateState(approve);
        return true;
    }

    @Override
    public List<FlowApprove> queryByFlowInstanceId(String flowInstanceId) {
        return flowApproveMapper.selectList(Wrappers.<FlowApprove>lambdaQuery().eq(FlowApprove::getFlowInstanceId, flowInstanceId));
    }

    @Override
    public IPage<FlowApprove> queryByParam(RequestListParamModel model) {
        Page<FlowApprove> page = new Page<>(model.getPageNumber(), model.getPageSize());
        LambdaQueryWrapper queryWrapper = Wrappers.<FlowApprove>lambdaQuery();
        return flowApproveMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<MyFlowApprove> queryByParam(RequestListParamModel model, byte... type) {
        Page<FlowApprove> page = new Page<>(model.getPageNumber(), model.getPageSize(), true);

        QueryWrapper queryWrapper = Wrappers.<FlowApprove>query();
        if (type != null) {
            queryWrapper.in("state", type);
        }
        if (StringUtils.isNotBlank(model.getUserId())) {
            queryWrapper.eq("accept_user_id", model.getUserId());
        }
        IPage<FlowApprove> approves = flowApproveMapper.selectPage(page, queryWrapper);
        Page<MyFlowApprove> flowApproves = new Page<>();
        List<MyFlowApprove> recorder = new ArrayList<>();
        approves.getRecords().forEach(fa -> {
            recorder.add(MyFlowApprove.instance(fa, flowInstanceService.queryById(fa.getFlowInstanceId())));
        });
        BeanUtils.copyProperties(approves, flowApproves, "records");
        flowApproves.setRecords(recorder);
        return flowApproves;
    }

    @Override
    public int updateReadDate(FlowApprove approve) {
        if (Objects.isNull(approve)) {
            return 404;
        } else if (approve.getState() != 1 && approve.getState() != 4) {
            return 401;
        } else {
            approve.setReadDatetime(DateUtils.getNowDatetime());
            return updateState(approve);
        }
    }

    @Override
    public CourseHistoryResultModel autoPass(FlowApprove approve) {
        Map<String, Object> result = pass(approve);
        if (result.isEmpty()) {
            SystemPersonModel sendPerson = new SystemPersonModel();
            FlowApprove dba = flowApproveMapper.selectById(approve.getId());
            sendPerson.setId(dba.getAcceptUserId());
            sendPerson.setName(dba.getAcceptUserName());
            return courseHistoryService.goNext(approve.getCourseHistoryId(), approve.getFlowInstanceId(), sendPerson);
        } else {
            CourseHistoryResultModel chm = new CourseHistoryResultModel();
            chm.setOk(false);
            chm.setMsg("");
            result.forEach((key, val) -> {
                chm.setMsg(chm.getMsg() + "\n" + val.toString());
            });
            return chm;
        }
    }


}
