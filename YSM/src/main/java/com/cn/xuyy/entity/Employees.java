package com.cn.xuyy.entity;

public class Employees {
    private Long empId;

    private String name;

    private String password;

    private Integer powerId;

    @Override
    public String toString() {
        return "Employees{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", password=" + password +
                ", powerId='" + powerId+ '\'' +
                '}';
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }
}