package com.kailismart.com.entity;

import java.io.Serializable;

public class Staff implements Serializable {
    private static final long serialVersionUID = -13825030241182937L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
