package cn.hzhy.oa.service;

import java.util.List;

import cn.hzhy.oa.entity.Employee;
import cn.hzhy.oa.util.EmployeeProperties;
import cn.hzhy.oa.util.PageUtil;

public interface EmployeeService {
	public List<Employee> getByName(String empName);
	/**
	 * 登录
	 * @param employee
	 * @return
	 */
	public Employee login(Employee employee);
	
	public PageUtil<Employee> getEmployeeListByPage(EmployeeProperties condition,int pageSize,int pageNo);
	public int getTotalPages(EmployeeProperties condition,int pageSize);
}
