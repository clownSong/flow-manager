package com.yskj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yskj.entity.BaseEntity;
import com.yskj.entity.FlowFolder;
import com.yskj.mapper.FlowFolderMapper;
import com.yskj.service.FlowFolderService;
import com.yskj.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.yskj.utils.DateUtils.getNowDatetime;
import static com.yskj.utils.EntityVerifyUtils.verifyString;

/**
 * @author XiaoSong
 * @date 2019-12-18 13:24
 * 流程目录服务实现
 */
@Service
public class FlowFolderServiceImpl extends BaseServiceImplAbstract implements FlowFolderService {
    @Autowired
    private FlowFolderMapper flowFolderMapper;

    @Override
    public Map<String, Object> insert(FlowFolder folder) {
        return insert(folder, flowFolderMapper);
    }

    @Override
    public Map<String, Object> update(FlowFolder folder) {
        return update(folder, flowFolderMapper);
    }

    @Override
    public Map<String, Object> delete(String id) {
        return delete(id, flowFolderMapper);
    }

    @Override
    public FlowFolder queryById(String id) {
        return flowFolderMapper.queryById(id);
    }

    @Override
    public List<FlowFolder> queryRoot() {
        return flowFolderMapper.queryRoot();
    }

    @Override
    public List<FlowFolder> queryFolder(String parentId) {
        return flowFolderMapper.queryFolder(parentId);
    }

    @Override
    public FlowFolder queryByName(String folderName) {
        return flowFolderMapper.queryByName(folderName);
    }

    @Override
    public Integer getMaxSort(String parentId) {
        return flowFolderMapper.queryMaxSort(parentId);
    }

    @Override
    public List<FlowFolder> search(String name) {
        QueryWrapper qw = new QueryWrapper();
        qw.lambda().like("name",name);
        return flowFolderMapper.selectList(qw);
    }

    @Override
    public Map<String, Object> verify(final BaseEntity baseEntity) {
        FlowFolder flowFolder = (FlowFolder) baseEntity;
        Map<String, Object> verifyResult = new HashMap<>(4);
        verifyResult.put("name", "目录名称不能为空");
        verifyResult = verifyString(verifyResult, flowFolder);
        if (verifyResult.isEmpty()) {
//            验证成功
            if (flowFolder.getName().length() > Constant.DATABASE_VARCHAR_LENGTH_100) {
//                目录名称过长
                verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                verifyResult.put(Constant.RESULT_STATE_MSG_KEY, "目录名称:" + Constant.DATABASE_LENGTH_ERROR_MSG);
                return verifyResult;
            }
            if (StringUtils.isBlank(flowFolder.getId())) {
//                设置uuid
                flowFolder.setId(UUID.randomUUID().toString());
            }
//            设置添加时间
            flowFolder.setDatetime(getNowDatetime());
//            设置目录root元素
            setRoot(flowFolder);
//            root元素长度是否超过
            if (flowFolder.getRoot().length() > Constant.DATABASE_VARCHAR_LENGTH_8000) {
                verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                verifyResult.put(Constant.RESULT_STATE_MSG_KEY, "子目录超过系统限制!");
                return verifyResult;
            }
//           设置目录排序序号
            setRootSort(flowFolder);
//            父元素是否存在
            if (StringUtils.isNotBlank(flowFolder.getParentId())) {
                FlowFolder parent = queryById(flowFolder.getParentId());
                if (parent == null) {
//                    父元素目录不存在
                    verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                    verifyResult.put(Constant.RESULT_STATE_MSG_KEY, "指定的上级目录不存在");
                    return verifyResult;
                } else if (parent.getId().equals(flowFolder.getId())) {
                    verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
                    verifyResult.put(Constant.RESULT_STATE_MSG_KEY, "上级目录设置错误（不允许设置自身为上级目录）");
                    return verifyResult;
                }
            } else {
                flowFolder.setParentId(null);
            }
            verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_SUCCESS);
        } else {
//            验证失败
            verifyResult.put(Constant.RESULT_STATE_KEY, Constant.RESULT_STATE_FAIL);
        }
        return verifyResult;
    }

    /**
     * 设置目录排序序号
     *
     * @param flowFolder 目录对象
     */
    private void setRootSort(final FlowFolder flowFolder) {
        if (Objects.isNull(flowFolder.getSort())) {
            Integer maxSort = getMaxSort(flowFolder.getParentId());
            maxSort = maxSort == null ? 0 : maxSort;
            flowFolder.setSort(maxSort + 1);
        }
    }


    /**
     * 设置目录对象root值，此方法需要对象id不能为null
     *
     * @param flowFolder
     */
    private void setRoot(final FlowFolder flowFolder) {
        FlowFolder parent = queryById(flowFolder.getParentId());
        if (parent != null) {
            flowFolder.setRoot(parent.getRoot() + "," + flowFolder.getId());
        } else {
            flowFolder.setRoot(flowFolder.getId());
        }
    }
}
