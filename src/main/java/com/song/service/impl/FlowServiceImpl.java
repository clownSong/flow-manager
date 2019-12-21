package com.song.service.impl;

import com.song.entity.BaseEntity;
import com.song.entity.Flow;
import com.song.mapper.FlowMapper;
import com.song.service.FlowService;
import com.song.utils.DateUtils;
import com.song.utils.EntityVerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.song.utils.Constant.*;

/**
 * @author XiaoSong
 * @date 2019-12-19 10:11
 * 流程主体服务
 */
@Service
public class FlowServiceImpl extends BaseServiceImplAbstract implements FlowService {
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public Map<String, Object> insert(Flow flow) {
        return insert(flow, flowMapper);
    }

    @Override
    public Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(RESULT_STATE_KEY, flowMapper.delete(id));
        return result;
    }

    @Override
    public Map<String, Object> update(Flow flow) {
        return update(flow, flowMapper);
    }

    @Override
    public Flow queryById(String id) {
        return flowMapper.queryById(id);
    }

    @Override
    public List<Flow> queryByFolder(String folderId) {
        return flowMapper.queryByFolder(folderId);
    }

    @Override
    public List<Flow> queryByViewId(String viewId) {
        return flowMapper.queryByViewId(viewId);
    }

    @Override
    public Integer queryMaxSort(String viewId) {
        return flowMapper.queryMaxSort(viewId);
    }

    @Override
    public int deleteByFolder(String folderId) {
        return flowMapper.deleteByFolder(folderId);
    }

    @Override
    public int deleteByViewId(String viewId) {
        return flowMapper.deleteByViewId(viewId);
    }

    @Override
    public List<Flow> queryByName(String name, String viewId) {
        return flowMapper.queryByName(name, viewId);
    }

    @Override
    protected Map<String, Object> verify(final BaseEntity baseEntity) {
        Flow flow = (Flow) baseEntity;
        Map<String, Object> result = new HashMap<>(4);
        result.put("name", "流程名称不能为空");
        result.put("person", "请指定流程管理人员");
        result = EntityVerifyUtils.verifyString(result, flow);
        if (result.isEmpty()) {
//            验证通过
            if (flow.getFolder() == null) {
                result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
                result.put(RESULT_STATE_MSG_KEY, "请指定流程目录");
                return result;
            }
            if (flow.getName().length() > DATABASE_VARCHAR_LENGTH_200) {
                result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
                result.put(RESULT_STATE_MSG_KEY, "流程目录名称过长");
                return result;
            }
//            设置流程添加时间
            flow.setDatetime(DateUtils.getNowDatetime());
//            设置排序
            if (flow.getSort() == null) {
                Integer max = queryMaxSort(flow.getViewId());
                max = max == null ? 0 : max;
                flow.setSort(max + 1);
            }
//            设置流程id
            if (flow.getId() == null) {
                flow.setId(UUID.randomUUID().toString());
            }
//            设置默认启用
            flow.setStop(false);
            result.put(RESULT_STATE_KEY, RESULT_STATE_SUCCESS);
        } else {
//            验证失败
            result.put(RESULT_STATE_KEY, RESULT_STATE_FAIL);
        }
        return result;
    }
}
