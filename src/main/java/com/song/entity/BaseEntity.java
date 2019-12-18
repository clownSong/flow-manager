package com.song.entity;

import java.io.Serializable;

/**
 * @author XiaoSong
 * @date 2019-12-18 10:59
 */
public class BaseEntity implements Serializable {
    /**
     * 实体类默认主键，长度40
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
