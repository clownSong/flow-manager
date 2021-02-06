package com.song.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.song.entity.Course;
import com.song.entity.CourseHistory;
import com.song.entity.CoursePerson;
import com.song.entity.FlowInstance;
import com.song.mapper.CourseHistoryMapper;
import com.song.model.CourseHistoryListModel;
import com.song.model.CourseHistoryResultModel;
import com.song.model.SystemPersonModel;
import com.song.service.*;
import com.song.utils.EntityVerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CourseHistoryServiceImpl implements CourseHistoryService {
    private Logger log = LoggerFactory.getLogger(CourseHistoryServiceImpl.class);
    @Resource
    private CourseHistoryMapper courseHistoryMapper;
    @Resource
    private CoursePersonService coursePersonService;
    @Resource
    private CoursePersonHistoryService coursePersonHistoryService;
    @Resource
    private FlowApproveService flowApproveService;
    @Resource
    private FlowInstanceService flowInstanceService;

    @Override
    public CourseHistory insert(CourseHistory courseHistory) {
        Map<String, Object> verify = new HashMap<>();
        verify.put("flowHistoryId", "未指定流程主体id");
        verify.put("name", "未指定过程名称");
        verify = EntityVerifyUtils.verifyString(verify, courseHistory);
        if (verify.isEmpty()) {
            courseHistory.setId(UUID.randomUUID().toString());
            courseHistoryMapper.insert(courseHistory);
            return courseHistory;
        }
        return null;
    }

    @Override
    @Transactional
    public CourseHistory insertByCourse(Course course, String historyId) {
        CourseHistory courseHistory = new CourseHistory();
        BeanUtils.copyProperties(course, courseHistory);
        courseHistory.setCourseId(course.getId());
        courseHistory.setId(null);
        courseHistory.setFlowHistoryId(historyId);
        courseHistory = insert(courseHistory);
        if (Objects.nonNull(courseHistory)) {
//                添加过程中审批人员
            List<CoursePerson> coursePersons = coursePersonService.queryByCourse(course.getId());
            for (int i = 0; i < coursePersons.size(); i++) {
                CoursePerson coursePerson = coursePersons.get(i);
                coursePerson.setCourseId(courseHistory.getId());
                if (Objects.isNull(coursePersonHistoryService.insertByPerson(coursePerson))) {
                    log.error("添加过程人员记录失败");
                }/*else{
                        if(coursePerson.getDispose() == 1){

                        }
                    }*/
            }
//                添加过程中判断条件

        }
        return courseHistory;
    }

    @Override
    public CourseHistory queryById(String id) {
        return (CourseHistory) courseHistoryMapper.selectById(id);
    }

    @Override
    public List<CourseHistory> queryByHistoryFlowId(String historyFlowId) {
        QueryWrapper<CourseHistory> wrapper = new QueryWrapper();
        wrapper.eq("flow_history_id", historyFlowId);
        return courseHistoryMapper.selectList(wrapper);
    }

    @Override
    public CourseHistoryResultModel goNext(String courseHistoryId, String flowInstanceId, SystemPersonModel sendPerson) {
        CourseHistory courseHistory = queryById(courseHistoryId);
        CourseHistoryResultModel result = new CourseHistoryResultModel();
        if (Objects.isNull(courseHistory)) {
            result.setOk(false);
            result.setMsg("指定的过程记录不存在");
        } else {
            if (courseHistory.getIsCountersign() == 1) {
                //需要会签，判断该节点下所有的审批节点是否全部同意
                if (flowApproveService.isApprove(courseHistoryId)) {
                    //全部通过
                    return next(courseHistory, flowInstanceId, sendPerson);
                } else {
                    //未全部通过
                    result.setMsg("会签流程，尚未全部通过审批");
                    result.setOk(false);
                    return result;
                }
            } else {
                return next(courseHistory, flowInstanceId, sendPerson);
            }
        }
        return result;
    }

    /**
     * 下一步过程
     *
     * @param courseHistory 当前审批过程
     * @return
     */
    private CourseHistoryResultModel next(CourseHistory courseHistory, String flowInstanceId, SystemPersonModel sendPerson) {
        CourseHistoryResultModel result = new CourseHistoryResultModel();
        FlowInstance instance = flowInstanceService.queryById(flowInstanceId);
        List<CourseHistory> courseHistoryList = queryByParentId(courseHistory.getCourseId(),instance.getFlowHistoryId());
        if (courseHistoryList.isEmpty()) {
            //步骤全部审批完成
            flowInstanceService.success(flowInstanceId, sendPerson);
        } else if (courseHistoryList.size() == 1) {
            CourseHistory nextCourse = courseHistoryList.get(0);
            //调用审批消息新增服务
            flowApproveService.insertByCourseHistoryId(nextCourse.getId(), flowInstanceId, sendPerson);
        } else {
            //有条件判断的过程
        }
        return result;
    }

    @Override
    public List<CourseHistory> queryByParentId(String courseHistoryId,String flowHistoryId) {
        QueryWrapper<CourseHistory> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(CourseHistory::getFlowHistoryId,flowHistoryId);
        queryWrapper.lambda().and(param->{
            param.eq(CourseHistory::getParentCourseId,courseHistoryId);
        });
        return courseHistoryMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteByFlowHistoryId(String flowId) {
        //            删除过程中人员记录
        List<CourseHistory> courseHistoryList = queryByHistoryFlowId(flowId);
        courseHistoryList.forEach(c -> {
//            删除过程中审批步骤人员记录
            coursePersonHistoryService.deleteByCourseHistoryId(c.getId());
//            删除审批消息表数据
            flowApproveService.deleteByCourseId(c.getId());
//            删除判定条件
        });
//        删除过程记录
        return courseHistoryMapper.delete(new UpdateWrapper<CourseHistory>().lambda().eq(CourseHistory::getFlowHistoryId, flowId));
    }

    @Override
    public List<CourseHistoryListModel> queryByFlowHistoryId(String flowHistoryId) {
        List<CourseHistory> histories = courseHistoryMapper.selectList(Wrappers.<CourseHistory>lambdaQuery().eq(CourseHistory::getFlowHistoryId, flowHistoryId));
        List<CourseHistoryListModel> listModels = new ArrayList<>(histories.size());
        histories.forEach(item -> {
            listModels.add(new CourseHistoryListModel(item, flowApproveService.queryByCourseId(item.getId())));
        });
        return listModels;
    }
}
