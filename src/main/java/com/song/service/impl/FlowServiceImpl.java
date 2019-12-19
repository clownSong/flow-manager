package com.song.service.impl;

import com.song.entity.Flow;
import com.song.mapper.FlowMapper;
import com.song.service.FlowService;
import com.song.utils.Constant;
import com.song.utils.DateUtils;
import com.song.utils.EntityVerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author XiaoSong
 * @date 2019-12-19 10:11
 * 流程主体服务
 */
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public Map<String, Object> insert(Flow flow) {
        Map<String, Object> result = verify(flow);
        if ((int) result.get(Constant.RESULT_STATE_KEY) == Constant.RESULT_STATE_SUCCESS) {
//           验证通过,保存流程
            result.put(Constant.RESULT_STATE_KEY, flowMapper.insert(flow));
            result.put(Constant.RESULT_DATA_KEY, flow);
        }
        return result;
    }

    @Override
    public Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>(4);
        result.put(Constant.RESULT_STATE_KEY, flowMapper.delete(id));
        return result;
    }

    @Override
    public Map<String, Object> update(Flow flow) {
        Map<String, Object> result = verify(flow);
        if ((int) result.get(Constant.RESULT_STATE_KEY) == Constant.RESULT_STATE_SUCCESS) {
//            验证通过，修改流程
            result.put(Constant.RESULT_STATE_KEY, flowMapper.update(flow));
            result.put(Constant.RESULT_DATA_KEY, flow);
        }
        return result;
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

    private Map<String, Object> verify(final Flow flow) {
        Map<String, Object> result = new HashMap<>(4);
        result.put("name", "流程名称不能为空");
        result.put("person", "请指定流程管理人员");
        result = EntityVerifyUtils.verifyString(result, flow);
        if (result.isEmpty()) {
//            验证通过
            if (flow.getFolder() == null) {
                result.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                result.put(Constant.RESULT_STATE_MSG_KEY, "请指定流程目录");
                return result;
            }
            if (flow.getName().length() > Constant.DATABASE_VARCHAR_LENGTH_200) {
                result.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                result.put(Constant.RESULT_STATE_MSG_KEY, "流程目录名称过长");
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
            result.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_SUCCESS);
        } else {
//            验证失败
            result.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
        }
        return result;
    }
}
