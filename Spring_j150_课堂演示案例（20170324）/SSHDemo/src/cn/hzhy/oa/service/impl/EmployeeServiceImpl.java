package cn.hzhy.oa.service.impl;

import java.util.List;

import cn.hzhy.oa.dao.EmployeeDao;
import cn.hzhy.oa.entity.Employee;
import cn.hzhy.oa.service.EmployeeService;
import cn.hzhy.oa.util.EmployeeProperties;
import cn.hzhy.oa.util.PageUtil;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	
	/**
	 * 设置employeeDao
	 * @param employeeDao
	 */
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public List<Employee> getByName(String empName) {
		return employeeDao.getByName(empName);
	}
	
	public Employee login(Employee employee) {
		List<Employee> empList= employeeDao.getByName(employee.getEmpName());
		Employee result=null;
		if(empList!=null&&empList.size()!=0){
			result=empList.get(0);
			if(result.getPassword().equals(employee.getPassword())){
				return result;
			}
		}
		return null;
	}

	public PageUtil<Employee> getEmployeeListByPage(EmployeeProperties condition,
			int pageSize, int pageNo) {
		PageUtil<Employee> pageUtil=new PageUtil<Employee>();
		List<Employee> empList=employeeDao.getEmployeeListByPage(condition, pageSize, pageNo);
		pageUtil.setCurrPageNo(pageNo);
		pageUtil.setTotalPages(this.getTotalPages(condition,pageSize));
		pageUtil.setList(empList);
		return pageUtil;
	}

	public int getTotalPages(EmployeeProperties condition,int pageSize) {
		//获取总的记录数
		int totalRecords=employeeDao.getEmployeeList(condition).size();
		System.out.println("总的记录数:"+totalRecords);
		return totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
	}
}
