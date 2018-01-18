package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.YoOrderMapper;
import com.model.YoOrder;
import com.model.YoOrderExample;
import com.service.IOrderService;
import com.util.StringUtil;


@Transactional
@Service
public class IOrderServiceImpl implements IOrderService{
	@Autowired
	private YoOrderMapper yoOrderMapper;
	@Override
	public List<YoOrder> getDepartment() {
    
	
		List<YoOrder> orderBusList=yoOrderMapper.getDepartment();
		return orderBusList;
	}

	@Override
	public List<YoOrder> getProjectByDepartment(String department) {
		// TODO Auto-generated method stub
		return yoOrderMapper.getProjectByDepartment(department);
		
	}

	/**
	 * 条件查询
	 */
	public  List<YoOrder> getOrderList(YoOrder order){
		
		YoOrderExample example = new YoOrderExample();
		YoOrderExample.Criteria criteria = example.createCriteria();
    	if(order.getOrderNumber()!=null&&!"".equals(order.getOrderNumber())) criteria.andOrderNumberEqualTo(order.getOrderNumber());
    	if(order.getProject()!=null&&!"".equals(order.getProject())) criteria.andProjectEqualTo(order.getProject());
    	if(order.getDepartment()!=null&&!"".equals(order.getDepartment())) criteria.andDepartmentEqualTo(order.getDepartment());
    	if(order.getOrderName()!=null&&!"".equals(order.getOrderName()))criteria.andOrderNameEqualTo(order.getOrderName());
    	List<YoOrder> orderList=yoOrderMapper.selectByExample(example);
    	
    	return orderList;
	}

	@Override
	public List<YoOrder> getOrderByDepartmentAndProject(String department,String project) {
		// TODO Auto-generated method stub
		YoOrderExample example = new YoOrderExample();
		YoOrderExample.Criteria criteria = example.createCriteria();
		if(StringUtil.NotBlank(project)) {criteria.andProjectEqualTo(project);};//else{return null;}
		if(StringUtil.NotBlank(department)) {criteria.andDepartmentEqualTo(department);};//else{return null;}
    	criteria.andOrderNumberLike("17"+"%");   	
    	List<YoOrder> orderList=yoOrderMapper.selectByExample(example);
		return orderList;
	}
	
	@Override
	public List<String> selectAllDepartment(){
		return yoOrderMapper.selectAllDepartment();
	}
	@Override
	public List<String> selectProjectOfDepartment(String department){
		return yoOrderMapper.selectProjectOfDepartment(department);
	}
	@Override
	public List<String> selectOrderofProject(String project){
		return yoOrderMapper.selectOrderofProject(project);
	}

	@Override
	public String getOrderNumberByName(String orderName) {
		// TODO Auto-generated method stub

		YoOrderExample example = new YoOrderExample();
		YoOrderExample.Criteria criteria = example.createCriteria();
		if(StringUtil.NotBlank(orderName)) {criteria.andOrderNameEqualTo(orderName);}else{return null;}

    	List<YoOrder> orderList=yoOrderMapper.selectByExample(example);
    	if(orderList!=null&&orderList.size()>0){
    		return  orderList.get(0).getOrderNumber();
    	}
		return null;
	}
}
