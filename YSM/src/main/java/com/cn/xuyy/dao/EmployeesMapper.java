package com.cn.xuyy.dao;

import com.cn.xuyy.entity.Employees;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeesMapper {
    int deleteByPrimaryKey(Long empId);

    int insert(Employees record);

    Employees selectByPrimaryKey(Long empId);

    int updateByPrimaryKey(Employees record);

    List<Employees> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}