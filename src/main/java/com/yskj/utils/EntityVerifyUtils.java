package com.yskj.utils;

import com.yskj.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-18 13:32
 * 实体类属性校验工具
 */
public class EntityVerifyUtils {
    /**
     * 验证对象属性值是否通过
     *
     * @param verifyParam {key:需要验证的字段,value:验证失败后的提示信息}
     * @param baseEntity  待验证实体
     */
    public static Map<String, Object> verifyString(final Map<String, Object> verifyParam, BaseEntity baseEntity) {
        if (baseEntity == null) {
            verifyParam.put("baseEntity", "数据不能为空");
            return verifyParam;
        } else {
            Class classes = baseEntity.getClass();
            Iterator<String> propKey = verifyParam.keySet().iterator();
            String property = null;
            String msg = null;
            while (propKey.hasNext()) {
                property = propKey.next();
                if (verifyString(classes, property, baseEntity)) {
                    msg = verifyParam.get(property).toString();
                    break;
                }
                property = null;
            }

            verifyParam.clear();
            if (property == null) {
                return verifyParam;
            } else {
                verifyParam.put(property, msg);
                return verifyParam;
            }
        }

    }

    private static boolean verifyString(Class classes, String property, BaseEntity baseEntity) {
        Field field = ReflectionUtils.findField(classes, property);
        assert field != null;
        field.setAccessible(true);
        Object fieldValue = null;
        String value = null;
        try {
            fieldValue = field.get(baseEntity);
            if (fieldValue == null) {
                return true;
            }
            value = String.valueOf(fieldValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return StringUtils.isBlank(value);
    }
}
