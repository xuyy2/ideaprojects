package cn.hzhy.oa.dao;

import java.util.List;

import cn.hzhy.oa.entity.Employee;
import cn.hzhy.oa.util.EmployeeProperties;

/**
 * 员工信息持久化操作接口
 * @author Administrator
 *
 */
public interface EmployeeDao {
	/**
	 * 根据用户名查询员工信息
	 * @param userName
	 * @return
	 */
	public List<Employee> getByName(String empName);
	
	/**
	 * 分页查询员工信息
	 * @param condition
	 * @return
	 */
	public List<Employee> getEmployeeListByPage(EmployeeProperties condition,int pageSize,int pageNo);

	/**
	 * 根据条件查询员工信息
	 * @param condition
	 * @return
	 */
	public List<Employee> getEmployeeList(EmployeeProperties condition);
		
}
