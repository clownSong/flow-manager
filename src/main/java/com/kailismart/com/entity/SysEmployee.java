package com.kailismart.com.entity;

import java.io.Serializable;

public class SysEmployee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long employeeId;
    private String id;
    private String name;

    public String getId() {
        return String.valueOf(this.employeeId);
    }

    public void setId(String id) {
        this.employeeId = Long.valueOf(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
