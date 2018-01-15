package com.cn.pojo;
public class User {
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    public User(Integer id, String userName, String password, Integer age) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }
}