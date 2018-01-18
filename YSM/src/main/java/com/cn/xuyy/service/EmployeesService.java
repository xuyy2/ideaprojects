package com.cn.xuyy.service;

import com.cn.xuyy.entity.Employees;

import java.util.List;

public interface EmployeesService {
    Employees getById(long empID);
    List<Employees> getList(int start, int pageNum);
    int addEmployees(Employees employees);
    int updateEmployees(Employees employees);
    int deleteEmployeesById(long empId);
}
