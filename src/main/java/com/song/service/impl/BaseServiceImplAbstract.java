package com.song.service.impl;

import com.song.entity.BaseEntity;
import com.song.mapper.BaseMapper;

import java.util.Map;

import static com.song.utils.Constant.*;

/**
 * @author XiaoSong
 * @date 2019-12-20 16:39
 * 服务接口基类，实现基本通用方法
 */
public abstract class BaseServiceImplAbstract {
    /**
     * 添加数据到数据库
     *
     * @param baseEntity 系统基类实体
     * @param baseMapper 系统mapper基类接口
     * @return
     */
    public Map<String, Object> insert(BaseEntity baseEntity, BaseMapper baseMapper) {
        Map<String, Object> result = verify(baseEntity);
        if ((int) result.get(RESULT_STATE_KEY) == RESULT_STATE_SUCCESS) {
//           验证通过,保存数据
            result.put(RESULT_STATE_KEY, baseMapper.insert(baseEntity));
            result.put(RESULT_DATA_KEY, baseEntity);
        }
        return result;
    }

    /**
     * 更新数据库中数据
     *
     * @param baseEntity 系统基类实体
     * @param baseMapper 系统mapper基类接口
     * @return
     */
    public Map<String, Object> update(BaseEntity baseEntity, BaseMapper baseMapper) {
        Map<String, Object> result = verify(baseEntity);
        if ((int) result.get(RESULT_STATE_KEY) == RESULT_STATE_SUCCESS) {
//            验证通过，修改数据
            result.put(RESULT_STATE_KEY, baseMapper.update(baseEntity));
            result.put(RESULT_DATA_KEY, baseEntity);
        }
        return result;
    }

    /**
     * 验证实体类数据完整性抽象函数
     *
     * @param baseEntity 实体类对象
     * @return
     */
    protected abstract Map<String, Object> verify(final BaseEntity baseEntity);
}
