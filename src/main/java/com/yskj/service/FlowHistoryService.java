package com.yskj.service;

import com.yskj.entity.Flow;
import com.yskj.entity.FlowHistory;

import java.util.List;
import java.util.Map;

/**
 * 流程使用记录接口.
 *
 * @author xiaoSong
 * @date 2020-07-18
 */
public interface FlowHistoryService {
    /**
     * 添加流程记录
     *
     * @param flowHistory
     * @return
     */
    Map<String, Object> insert(FlowHistory flowHistory);

    /**
     * 删除流程记录
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 获取流程记录
     *
     * @param id 主键
     * @return
     */
    FlowHistory queryById(String id);

    /**
     * 根据指定属性参数查询流程记录集合
     *
     * @param flowHistory
     * @return
     */
    List<FlowHistory> queryByParam(FlowHistory flowHistory);

    /**
     * 通过流程主体添加流程记录信息
     *
     * @param flow
     * @param personInfo 使用人信息
     * @return
     */
    Map<String, Object> insertByFlow(Flow flow, String personInfo);
}
