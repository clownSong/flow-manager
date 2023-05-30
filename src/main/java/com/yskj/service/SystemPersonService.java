package com.yskj.service;

import com.yskj.model.SystemPersonModel;

import java.util.List;

/**
 * 系统人员服务接口，第三方引用flow-manager系统时需要实现该接口，供流程审批时获取人员信息.
 *
 * @author xiaoSong
 * @date 2020-07-20
 */
public interface SystemPersonService {
    /**
     * 获取人员列表
     *
     * @param id   主键
     * @param type 类型
     * @return
     */
    List<SystemPersonModel> getByPerson(String id, String type);
}
