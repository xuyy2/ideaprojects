package com.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.util.HSSFColor.ORANGE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.ddSdk.base.OApiException;
import com.model.DepartmentExample;
import com.model.ExpenseApplayHotel;
import com.model.YoOrderChange;
import com.service.IDepartmentService;
import com.service.IStaffInfoService;
import com.service.impl.StaffInfoServiceImpl;

import bsh.ParseException;
@Component("OrderMessageUtil")
public class OrderMessageUtil {
	@Autowired
	private  IStaffInfoService staffInfoService;
	@Autowired
	private  IDepartmentService departmentService;

	private static String corp_id= "ding246914ee44e47d4c";
	private static String corp_secrect= "Vnm79JLaXD1oiiZi-XV4LJlVW6KOYJmvWQfr3Z5mr-fZA8HSkMMhcoAySyRB5D_8";
	//请求access_token的URL
	private static String baseUrl01="https://oapi.dingtalk.com/gettoken?";
	//推送企业消息的URL
	private static String baseUrl02="https://oapi.dingtalk.com/message/send?access_token=";
	//获取部门详情
	private static String baseUrl03="https://oapi.dingtalk.com/department/get?access_token=";
	/**
	 * 获取access_token
	 * @return
	 */
	public static String getAccess_token(){
		String access_token=null;
		try {
			//根据corpid跟corpsecrect获取accesstoken
			String ddResponse=HttpsUtil.httpGet(OrderMessageUtil.baseUrl01+"corpid="+OrderMessageUtil.corp_id+"&corpsecret="+OrderMessageUtil.corp_secrect);
			JSONObject jsonObject = JSONObject.fromObject(ddResponse);
			if(jsonObject != null){
				//获取access_token
				access_token=jsonObject.getString("access_token");
			}
		}catch (ParseException | IOException
				| URISyntaxException e) {
			e.printStackTrace();
		} 
		return access_token;
	}
	/**
	 * 组织推送报文
	 * @return
	 */
	public static String getText(String expenseApplyType,String staffName,String startAddress,String endAddress){
		return staffName+"的"+expenseApplyType+"\n ;"+"从"+startAddress+"至"+endAddress;
	}
	/**
	 * 推送消息
	 */
	public static void sendMessage(YoOrderChange orderChange){
		
		
		OrderMessage message =Order2Message(orderChange);
		System.out.println("开始向"+message.getToUser()+"发送消息");
		String contentJson = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'blue',"
				+     "text: "+"'项目变动申请'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'姓名:',"
				+            "value:"+ "'"+message.getUsername()+"'"
				+"},"			
				+         " {"
				+            "key: "+"'部门:',"
				+            "value:"+ "'"+message.getDepartment()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'项目:',"
				+            "value:"+ "'"+message.getProject()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'订单:',"
				+            "value:"+ "'"+message.getOrderName()+"'"
				+      "}" 
				+" ],"   	          
				+"content:"+ "'请尽快审批',"
				+ "author:" +"'"+message.getUsername()+"' "
				+"}" 
				+"}" 
				+"}";

		System.out.println("contentJson====="+contentJson);
		try {
			String access_token = OrderMessageUtil.getAccess_token();
			JSONObject json=HttpsUtil.httpPost(OrderMessageUtil.baseUrl02+access_token,contentJson);
			System.out.println("发送成功"+json);
		} catch (OApiException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 推送消息
	 * @param message
	 */
	public static void sendMessage(OrderMessage message){
		

		String contentJson = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'blue',"
				+     "text: "+"'项目变动申请'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'姓名:',"
				+            "value:"+ "'"+message.getUsername()+"'"
				+"},"			
				+         " {"
				+            "key: "+"'部门:',"
				+            "value:"+ "'"+message.getDepartment()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'项目:',"
				+            "value:"+ "'"+message.getProject()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'订单:',"
				+            "value:"+ "'"+message.getOrderName()+"'"
				+      "}" 
				+" ],"   	          
				+"content:"+ "'请尽快审批',"
				+ "author:" +"'"+message.getUsername()+"' "
				+"}" 
				+"}" 
				+"}";

		System.out.println("contentJson====="+contentJson);
		try {
			String access_token = OrderMessageUtil.getAccess_token();
			JSONObject json=HttpsUtil.httpPost(OrderMessageUtil.baseUrl02+access_token,contentJson);
			System.out.println("发送成功"+json);
		} catch (OApiException e) {
			e.printStackTrace();
		}

	}
	public static OrderMessage Order2Message(YoOrderChange orderChange ){
		String staff_user_id =orderChange.getNowAcess();
		OrderMessage message = new OrderMessage();//正式服务器ip 121.40.29.241 //测试服务器ip yexianglei.ngrok.cc
		message.setMessageUrl("http://121.40.29.241/YindaOA/orderChange/approve_order_page.do?id="+orderChange.getId()+"&staff_user_id="+staff_user_id);
		message.setPicUrl("/cc");
		message.setToUser(orderChange.getNowAcess());
		message.setToParty("");
		message.setTitle(orderChange.getUsername()+"的项目变更申请");
		message.setDepartment(orderChange.getDepartment());
		message.setOrderName(orderChange.getOrderName());
		message.setProject(orderChange.getProject());
		message.setUsername(orderChange.getUsername());
		return message;
	}
 
	/**
	 * 根据当前用户的钉钉ID生成当前审批请求的审批人
	 * @param staffUserId 当前用户钉钉ID
	 * @return  [二级部门审批人钉钉ID,一级部门审批人钉钉ID]
	 */
	public  List<String> getApprovers(String staffUserId){
		List<String> approverList=new ArrayList<String>();//需要的审批人信息记录
		List<String> departmentList=new ArrayList<String>();//当前用户所在的各级部门列表
		List<String> depDDIdList=new ArrayList<String>();;//当前用户所在部门的各级部门钉钉ID列表
		//根据staffuserid获取用户的部门结构List
		departmentList = Arrays.asList(staffInfoService.selectStaffByID(staffUserId).getDepartment().split("\\-"));
		//根据二级部门名称,获取二级部门ID
		DepartmentExample example = new DepartmentExample();
		DepartmentExample.Criteria criteria = example.createCriteria();
		criteria.andDepNameEqualTo(departmentList.get(0));
		depDDIdList.add(departmentService.selectByExample(example).get(0).getDepDdId());
		//根据一级部门名称,以及一级部门对应父部门的钉钉ID，获得当前一级部门的钉钉ID
		if(departmentList.size() > 1){//挂职不在二级部门下的员工
			DepartmentExample example2 = new DepartmentExample();
			DepartmentExample.Criteria criteria02 = example2.createCriteria();
			criteria02.andDepParentidEqualTo(depDDIdList.get(0));
			criteria02.andDepNameEqualTo(departmentList.get(1));
			depDDIdList.add(departmentService.selectByExample(example2).get(0).getDepDdId());

		}
		//根据获得的部门ID查询对应的审批人的滴滴ID
		for(String depDDId : depDDIdList){
			try {
				String managerDDId=OrderMessageUtil.getManagerDDId(depDDId);
				System.out.println(managerDDId);
				//如果一级部门的负责人不存在,则要求二级部门的负责人批阅两次
				if(managerDDId==null || "".equals(managerDDId)){
					approverList.add(approverList.get(0));
				}else{
					approverList.add(managerDDId);
				}
			} catch (ParseException | IOException
					| URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return approverList;
	}
	//根据当前部门的钉钉ID获取当前部门的管理员的钉钉ID
	private static String getManagerDDId(String depDDId) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		String managerDDId=null;
		//获取Access_token
		String access_token = OrderMessageUtil.getAccess_token();
		//请求的URL
		String reqURL=baseUrl03+access_token+"&id="+depDDId;
		//发起https get请求
		String resp=HttpsUtil.httpGet(reqURL);
		JSONObject jsonObject = JSONObject.fromObject(resp);
		System.out.println(jsonObject.toString());
		if(jsonObject != null){
			//该部门主管ID可能包括多个这个时候只取第一个
			managerDDId=Arrays.asList(jsonObject.getString("deptManagerUseridList").split("\\|")).get(0);
		}
		return managerDDId;
	}
	  /**
	   * 推送普通提示信息
	   * @param message
	   * @param staff_user_id
	   * @return
	   * @throws OApiException
	   */
      public static JSONObject sendMessage(String message,String staff_user_id) throws OApiException{
    	  
    	  String  contentJson ="{'touser':'"+staff_user_id+"','toparty':'1','agentid':'56136978','msgtype':'text','text':{'content':'"+message+"'}}";
    	  String  access_token = OrderMessageUtil.getAccess_token();
    	  JSONObject json=HttpsUtil.httpPost("https://oapi.dingtalk.com/message/send?access_token="+access_token,contentJson);
	      return json;
	
  }
	public static void main(String []args) throws ClientProtocolException{
	
       OrderMessage message = new OrderMessage();
   	String contentJson = "{"
			+"touser:"+"'"+message.getToUser()+"'"+","
			+"toparty:"+"'"+message.getToParty()+"'"+","
			+"agentid:"+"'"+message.getAgentId()+"'"+","
			+"msgtype:" +"'oa',"
			+"oa: "+"{"
			+  "message_url:"+ "'"+message.getMessageUrl()+"',"
			+ "head:" +"{"
			+    "bgcolor:"+ "'blue',"
			+     "text: "+"'项目变动申请'"
			+ "},"
			+  "body:{" 
			+      "title:" +"'"+message.getTitle()+"',"
			+     "form:" +"["
			+         " {"
			+            "key: "+"'姓名:',"
			+            "value:"+ "'"+message.getUsername()+"'"
			+"},"			
			+         " {"
			+            "key: "+"'部门:',"
			+            "value:"+ "'"+message.getDepartment()+"'"
			+      "}," 
			+         " {"
			+            "key: "+"'项目:',"
			+            "value:"+ "'"+message.getProject()+"'"
			+      "}," 
			+         " {"
			+            "key: "+"'订单:',"
			+            "value:"+ "'"+message.getOrderName()+"'"
			+      "}" 
			+" ],"   	          
			+"content:"+ "'请尽快审批',"
			+ "author:" +"'"+message.getUsername()+"' "
			+"}" 
			+"}" 
			+"}";
		JSONObject jsonObject = JSONObject.fromObject(contentJson);


	}
}
