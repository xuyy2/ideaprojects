package cn.hzhy.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.hzhy.oa.dao.EmployeeDao;
import cn.hzhy.oa.entity.Employee;
import cn.hzhy.oa.util.EmployeeProperties;

/**
 * 员工信息持久化操作实现类
 * @author Administrator
 *
 */
public class EmployeeDaoImpl_bak implements EmployeeDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Employee> getByName(String empName) {
		Session session=sessionFactory.openSession();
		Query query= session.createQuery("from Employee where empName like :empName");
		query.setString("empName", empName);		
		return query.list();
	}

	public List<Employee> getEmployeeListByPage(EmployeeProperties condition,int pageSize,int page) {
		return null;
	}

	public List<Employee> getEmployeeList(EmployeeProperties condition) {
		return null;
	}
	
}
