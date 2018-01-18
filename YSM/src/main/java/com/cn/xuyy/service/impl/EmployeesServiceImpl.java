package com.cn.xuyy.service.impl;

import com.cn.xuyy.dao.EmployeesMapper;
import com.cn.xuyy.entity.Employees;
import com.cn.xuyy.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public Employees getById(long empID) {
        return employeesMapper.selectByPrimaryKey(empID);
    }

    @Override
    public List<Employees> getList(int start, int pageNum) {
        return employeesMapper.queryAll(start,pageNum);
    }

    @Override
    public int addEmployees(Employees employees) {
        return employeesMapper.insert(employees);
    }

    @Override
    public int updateEmployees(Employees employees) {
        return employeesMapper.updateByPrimaryKey(employees);
    }

    @Override
    public int deleteEmployeesById(long empId) {
        return employeesMapper.deleteByPrimaryKey(empId);
    }


}
