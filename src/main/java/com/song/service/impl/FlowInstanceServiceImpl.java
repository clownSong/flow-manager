package com.song.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.entity.Course;
import com.song.entity.CourseHistory;
import com.song.entity.FlowHistory;
import com.song.entity.FlowInstance;
import com.song.mapper.FlowInstanceMapper;
import com.song.model.FlowInstanceModel;
import com.song.model.RequestListParamModel;
import com.song.model.StartFlowInstanceModel;
import com.song.model.SystemPersonModel;
import com.song.service.*;
import com.song.utils.DateUtils;
import com.song.utils.EntityVerifyUtils;
import com.song.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Service
public class FlowInstanceServiceImpl extends ServiceImpl<FlowInstanceMapper, FlowInstance> implements FlowInstanceService {
    @Resource
    private FlowInstanceMapper flowInstanceMapper;
    @Resource
    private FlowHistoryService flowHistoryService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseHistoryService courseHistoryService;
    @Resource
    private CoursePersonService coursePersonService;
    @Resource
    private FlowApproveService flowApproveService;
    @Resource
    private FlowService flowService;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    @Transactional
    public StartFlowInstanceModel startFlow(FlowInstanceModel flowInstanceModel) {
        FlowInstance flowInstance = flowInstanceModel.getFlowInstance();
        StartFlowInstanceModel startFlowInstanceModel = new StartFlowInstanceModel();
        startFlowInstanceModel.setFlow(flowInstanceModel.getFlow());
        startFlowInstanceModel.setFlowInstance(flowInstance);
        Map<String, Object> verifyParam = verifyFlow(flowInstanceModel);
        if (verifyParam.isEmpty()) {
            verifyParam = verifyFlowInstance(flowInstance);
            if (verifyParam.isEmpty()) {
//                流程第一个节点是否自由选人审批
                if (courseService.isFreedom(flowInstanceModel.getFlow().getId())) {
                    Course first = courseService.queryFirst(flowInstanceModel.getFlow().getId());
                    setFreedom(startFlowInstanceModel, first);
                    return startFlowInstanceModel;
                }

                setFlowInstanceProperty(flowInstance);
//                添加流程主体记录实例
                Map<String, Object> result = flowHistoryService.insertByFlow(flowInstanceModel.getFlow(), flowInstance.getUserId() + "," + flowInstance.getUserName());
                if (!Objects.isNull(result.get("data"))) {
                    FlowHistory flowHistory = (FlowHistory) result.get("data");
                    flowInstance.setFlowHistoryId(flowHistory.getId());
//                    添加流程过程记录实例
                    List<Course> courses = courseService.queryByFlowId(flowInstanceModel.getFlow().getId());
                    courses.forEach(course -> {
//                        添加过程
                        CourseHistory courseHistory = courseHistoryService.insertByCourse(course, flowHistory.getId());
                        if (!Objects.isNull(courseHistory) && StringUtils.isBlank(course.getParentCourseId())) {
//                            添加第一步审批人消息数据
                            flowApproveService.insertByCourseId(course.getId(), flowInstance, courseHistory.getId());
//                            如果发起人与审批人是同一人，则直接审批，跳到下一个过程
                            flowApproveService.isAutoApprove(flowInstance, courseHistory.getId());
                            /*if (course.getFreedom()) {
                                //自由选人审批
                                setFreedom(startFlowInstanceModel, course);
                                return;
                            } else {

                            }*/
                        }
                    });
//                    添加流程发起实例
                    insertFlowInstance(flowInstance);
                } else {
                    setResultMsg(startFlowInstanceModel, result);
                }
            } else {
                setResultMsg(startFlowInstanceModel, verifyParam);
            }
        } else {
            startFlowInstanceModel.setMsg("请指定审批流程");
        }
        return startFlowInstanceModel;
    }

    private void setFreedom(final StartFlowInstanceModel startFlowInstanceModel, Course course) {
        startFlowInstanceModel.setFreedom(true);
        startFlowInstanceModel.setMsg("自由选择审批，请先指定审批人!");
        startFlowInstanceModel.setFirstCourse(course);
        startFlowInstanceModel.setFreedomElementArray(coursePersonService.getPersonList(course.getId(), startFlowInstanceModel.getFlowInstance().getUserId()));
    }

    private void setResultMsg(StartFlowInstanceModel startFlowInstanceModel, Map<String, Object> verifyParam) {
        Iterator<String> iterator = verifyParam.keySet().iterator();
        String msg = "";
        while (iterator.hasNext()) {
            msg += verifyParam.get(iterator.next()) + ",";
        }
        startFlowInstanceModel.setMsg(msg);
    }

    private void setFlowInstanceProperty(final FlowInstance flowInstance) {
        flowInstance.setId(UUID.randomUUID().toString());
        flowInstance.setStartDate(DateUtils.getNowDate());
        flowInstance.setState((byte) 0);
        flowInstance.setFlowHistoryId(UUID.randomUUID().toString());
    }

    private Map<String, Object> verifyFlowInstance(FlowInstance flowInstance) {
        Map<String, Object> verifyParam = new HashMap<>(4);
        verifyParam.put("userId", "发起人id不能为空");
        verifyParam.put("userName", "发起人姓名不能为空");
        verifyParam.put("title", "标题不能为空");
        verifyParam.put("moduleTypeId", "表单类型不能为空");
        verifyParam.put("moduleId", "表单主键不能为空");
        return EntityVerifyUtils.verifyString(verifyParam, flowInstance);
    }

    private Map<String, Object> verifyFlow(FlowInstanceModel flowInstanceModel) {
        Map<String, Object> verifyParam = new HashMap<>();
        verifyParam.put("id", "请指定审批流程");
        verifyParam = EntityVerifyUtils.verifyString(verifyParam, flowInstanceModel.getFlow());
        if (verifyParam.isEmpty()) {
            flowInstanceModel.setFlow(flowService.queryById(flowInstanceModel.getFlow().getId()));
            if (Objects.isNull(flowInstanceModel.getFlow())) {
                verifyParam.put("id", "指定的审批流程无效");
            }
        }
        return verifyParam;
    }

    @Override
    public IPage<FlowInstance> queryByUser(RequestListParamModel model) {
        if (StringUtils.isBlank(model.getUserId())) {
            return null;
        }
        return queryAll(model);
    }

    @Override
    public IPage<FlowInstance> queryAll(RequestListParamModel paramModel) {
        QueryWrapper<FlowInstance> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FlowInstance::getUserId, paramModel.getUserId())
                .like(FlowInstance::getTitle, paramModel.getSearch());
        Page<FlowInstance> page = new Page(paramModel.getPageNumber(), paramModel.getPageSize());
        if (StringUtils.equalsIgnoreCase(paramModel.getOrder(), "ASC")) {
            page.setOrders(OrderItem.ascs(paramModel.getSort()));
        } else {
            page.setOrders(OrderItem.descs(paramModel.getSort()));
        }
        return flowInstanceMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public StartFlowInstanceModel cancel(String flowInstanceId) {
        FlowInstance instance = flowInstanceMapper.selectById(flowInstanceId);
        StartFlowInstanceModel resultModel = new StartFlowInstanceModel();
        if (Objects.isNull(instance)) {
            resultModel.setMsg("流程不存在");
        } else if (instance.getState() == 2) {
            resultModel.setMsg("流程已审批完成");
        } else {
            instance.setState((byte) 4);
            resultModel.setMsg("操作成功");
//            主流程取消
            flowInstanceMapper.updateById(instance);
//            取消审批中的步骤状态
            flowApproveService.cancel(flowInstanceId, null);
        }
        return resultModel;
    }

    @Override
    public StartFlowInstanceModel success(String flowInstanceId, SystemPersonModel sendPerson) {
        FlowInstance instance = queryById(flowInstanceId);
        StartFlowInstanceModel result = new StartFlowInstanceModel();
        result.setFlowInstance(instance);
        if (Objects.isNull(instance)) {
            result.setMsg("流程发起实例不存在");
        } else {
            instance.setState((byte) 2);
            flowInstanceMapper.updateById(instance);
            //回调流程变化api
            flowChange(instance, sendPerson);
            result.setMsg("流程审批完成");
        }
        return result;
    }

    @Override
    public FlowInstance queryById(String flowInstanceId) {
        return flowInstanceMapper.selectById(flowInstanceId);
    }

    @Override
    @Transactional
    public StartFlowInstanceModel delete(String flowInstanceId) {
        FlowInstance instance = queryById(flowInstanceId);
        StartFlowInstanceModel result = new StartFlowInstanceModel();
        result.setFlowInstance(instance);
        if (Objects.isNull(instance)) {
            result.setMsg("指定的流程实例不存在");
        } else if (instance.getState() == 2) {
            result.setMsg("流程审批完成");
        } else {
//            删除过程
            courseHistoryService.deleteByFlowHistoryId(instance.getFlowHistoryId());
//            删除流程记录
            flowHistoryService.delete(instance.getFlowHistoryId());
//            删除流程实例
            flowInstanceMapper.deleteById(flowInstanceId);
        }
        return result;
    }

    @Override
    public void flowChange(FlowInstance instance, SystemPersonModel sendPerson) {
        RestTemplate restTemplate = new RestTemplate();
        String param = null;
        try {
            param = "?flowId=" + URLDecoder.decode(instance.getId(), "UTF-8") + "&moduleId=" + instance.getModuleId() + "&moduleType=" + instance.getModuleTypeId() + "&state=" + instance.getState() + "&sendUserId=" + sendPerson.getId();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
//            b7d2d6f2-7451-4c40-a5c7-556525fe0235
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Authorization", tokenUtils.getToken());
            requestHeaders.add("Content-Type","application/json;charset=UTF-8");
            Map<String, String> params = new HashMap<>();
            params.put("flowId", URLDecoder.decode(instance.getId(), "UTF-8"));
            params.put("moduleId", instance.getModuleId());
            params.put("moduleType", instance.getModuleTypeId());
            params.put("state", instance.getState() + "");
            params.put("sendUserId", sendPerson.getId());
            HttpEntity<Map> requestEntity = new HttpEntity<Map>(params, requestHeaders);
            restTemplate.exchange(instance.getChangeApi() + param, HttpMethod.PUT, requestEntity, String.class);
//            restTemplate.getForObject(instance.getChangeApi() + param, Object.class);
        } catch (Exception ignore) {

        }
    }

    private void insertFlowInstance(FlowInstance flowInstance) {
        flowInstanceMapper.insert(flowInstance);
    }
}
