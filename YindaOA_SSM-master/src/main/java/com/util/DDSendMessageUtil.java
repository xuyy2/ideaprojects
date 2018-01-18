package com.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.ddSdk.base.OApiException;
import com.model.DepartmentExample;
import com.model.ExpenseApplayBus;
import com.model.ExpenseApplayHotel;
import com.model.ExpenseApplayTrain;
import com.model.ExpenseApplySubway;
import com.service.IDepartmentService;
import com.service.IStaffInfoService;
import com.service.impl.StaffInfoServiceImpl;

import bsh.ParseException;
@Component("ddSendMessageUtil")
public class DDSendMessageUtil {
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
     * 获取当前时间
     * @return
     */
    private static  String getCurrentTimeDate() {

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        return time;
    }
    /**
	 * 获取access_token
	 * @return
	 */
    private static String getAccess_token(){
		String access_token=null;
    	try {
    		//根据corpid跟corpsecrect获取accesstoken
			String ddResponse=HttpsUtil.httpGet(DDSendMessageUtil.baseUrl01+"corpid="+DDSendMessageUtil.corp_id+"&corpsecret="+DDSendMessageUtil.corp_secrect);
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
     * 推送消息--大巴\火车\公交\地铁报销-即时推送
     * @param message
     */
    public static void sendMessage(DDMessageUtil message,String type){
    	//link消息类型
    	String contentJson1="{"
			    +"touser:"+"'"+message.getToUser()+"'"+","
			    +"toparty:"+"'"+message.getToParty()+"'"+","
			    +"agentid:"+"'"+message.getAgentId()+"'"+","
	    		+"msgtype:'link',"
			    +"link:{"
			    + "messageUrl:"+"'"+message.getMessageUrl()+"'"+","
			    + "picUrl:"+"'"+message.getPicUrl()+"'"+","
			    + "title:"+"'"+message.getTitle()+"'"+","
			    + "text:"+"'"+message.getText()+"'"
			    + "}"
	    		+ "}"; 
    	//OA消息类型
    	String contentJsonSubway = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'FF0000FF',"
				+     "text: "+"'"+message.getText()+"'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'姓名:',"
				+            "value:"+ "'"+message.getAskStaffName()+"'"
				+"},"			
				+         " {"
				+            "key: "+"'部门:',"
				+            "value:"+ "'"+message.getAskStaffDepartment()+"'"
				+      "},"  
				+         " {"
				+            "key: "+"'申请发送时间:',"
				+            "value:"+ "'"+message.getAskTime()+"'"
				+      "}" 
				+" ],"   	          
				+"}" 
				+"}" 
				+"}";
    	String contentJsonBus = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'FF0000FF',"
				+     "text: "+"'"+message.getText()+"'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'姓名:',"
				+            "value:"+ "'"+message.getAskStaffName()+"'"
				+"},"			
				+         " {"
				+            "key: "+"'部门:',"
				+            "value:"+ "'"+message.getAskStaffDepartment()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'起始地址:',"
				+            "value:"+ "'"+message.getStartAddress()+"'"
				+      "},"
				+         " {"
				+            "key: "+"'终点地址:',"
				+            "value:"+ "'"+message.getEndAddress()+"'"
				+      "},"
				+         " {"
				+            "key: "+"'乘车时间:',"
				+            "value:"+ "'"+message.getAskTime()+"'"
				+      "}" 
				+" ],"   	          
				+"}" 
				+"}" 
				+"}";
    	String contentJsonHotel = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'FF0000FF',"
				+     "text: "+"'"+message.getText()+"'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'姓名:',"
				+            "value:"+ "'"+message.getAskStaffName()+"'"
				+"},"			
				+         " {"
				+            "key: "+"'部门:',"
				+            "value:"+ "'"+message.getAskStaffDepartment()+"'"
				+      "}," 
				+         " {"
				+            "key: "+"'旅店名称:',"
				+            "value:"+ "'"+message.getHotelName()+"'"
				+      "},"
				+         " {"
				+            "key: "+"'住店时间:',"
				+            "value:"+ "'"+message.getAskTime()+"'"
				+      "},"
				+" ],"   	          
				+"}" 
				+"}" 
				+"}";
    	String contentJsonWeekCount = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
//				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'FF0000FF',"
				+     "text: "+"'"+message.getText()+"'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["
				+         " {"
				+            "key: "+"'大巴费报销:',"
				+            "value:"+ "'"+message.getBusCount()+"条待审批'"				+"},"			
				+         " {"
				+            "key: "+"'火车票报销:',"
				+            "value:"+ "'"+message.getTrainCount()+"条待审批'"
				+      "}," 
				+         " {"
				+            "key: "+"'旅店费报销待:',"
				+            "value:"+ "'"+message.getHotelCount()+"条待审批'"
				+      "},"
				+         " {"
				+            "key: "+"'地铁公交费报销:',"
				+            "value:"+ "'"+message.getSubwayCount()+"条待审批'"
				+      "},"
				+" ],"   	          
				+"}" 
				+"}" 
				+"}";
    	String contentJsonException = "{"
				+"touser:"+"'"+message.getToUser()+"'"+","
				+"toparty:"+"'"+message.getToParty()+"'"+","
				+"agentid:"+"'"+message.getAgentId()+"'"+","
				+"msgtype:" +"'oa',"
				+"oa: "+"{"
//				+  "message_url:"+ "'"+message.getMessageUrl()+"',"
				+ "head:" +"{"
				+    "bgcolor:"+ "'FF0000FF',"
				+     "text: "+"'"+message.getText()+"'"
				+ "},"
				+  "body:{" 
				+      "title:" +"'"+message.getTitle()+"',"
				+     "form:" +"["        
				+         " {"
				+            "key: "+"'异常内容:',"
				+            "value:"+ "'"+message.getNotice()+"'"
				+      "},"
				+" ],"   	          
				+"}" 
				+"}" 
				+"}";
        String contentJson=null;
    	if("subway".equals(type)){
    		contentJson=contentJsonSubway;
    	}else if("bus".equals(type)){
    		contentJson = contentJsonBus;
    	}else if("hotel".equals(type)){
    		contentJson=contentJsonHotel;
    	}else if("weekCount".equals(type)){
    		contentJson=contentJsonWeekCount;
    	}else if("exception".equals(type)){
    		contentJson=contentJsonException;
    	}else{
    		contentJson=contentJson1;
    	}
    	System.out.println("contentJson====="+contentJson);
    	try {
			String access_token = DDSendMessageUtil.getAccess_token();
			JSONObject json=HttpsUtil.httpPost(DDSendMessageUtil.baseUrl02+access_token,contentJson);
			System.out.println("消息发送成功----->钉钉返回Json"+json);
		} catch (OApiException e) {
			e.printStackTrace();
		}  	
    }
    /**
     * 根据当前用户的钉钉ID生成当前审批请求的审批人
     * @param staffUserId 当前用户钉钉ID
     * @return  [二级部门审批人钉钉ID,一级部门审批人钉钉ID]
     * 对于挂职在二级部门下的员工【二级部门审批人钉钉ID】
     */
    public  List<String> getApprovers(String staffUserId){
    	List<String> approverList=new ArrayList<String>();//需要的审批人信息记录
    	List<String> departmentList=new ArrayList<String>();//当前用户所在的各级部门列表
    	List<String> depDDIdList=new ArrayList<String>();;//当前用户所在部门的各级部门钉钉ID列表
    	//根据staffuserid获取用户的部门结构List 	
    	departmentList = Arrays.asList(Arrays.asList(staffInfoService.selectStaffByID(staffUserId)
    			.getDepartment().split("\\,")).get(0).split("\\-"));
    	System.out.println(departmentList.toString());
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
    			String managerDDId=DDSendMessageUtil.getManagerDDId(depDDId);
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
    	String access_token = DDSendMessageUtil.getAccess_token();
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
     * 发送火车票报销信息
     * @param expenseApplayTrain 报销信息
     * @param id 报销记录数据库主键
     * @param toUser  被发发送方
     */
    public static void sendMessageTrain(ExpenseApplayTrain expenseApplayTrain,int id,String toUser){
        DDMessageUtil ddMessage = new DDMessageUtil();
        ddMessage.setMessageUrl(ExpenseApplyResources.approve_Message_URL_TRAIN+id+"&manager="+toUser);
        ddMessage.setPicUrl(ExpenseApplyResources.approve_Message_picUrl);
        ddMessage.setText("");
        ddMessage.setTitle("火车票报销");
        ddMessage.setToParty("");
        ddMessage.setToUser(toUser);
        ddMessage.setAskStaffName(expenseApplayTrain.getStaffName());
        ddMessage.setAskStaffDepartment(expenseApplayTrain.getStaffDepart());
        ddMessage.setStartAddress(expenseApplayTrain.getStartAddress());
        ddMessage.setEndAddress(expenseApplayTrain.getEndAddress());
        ddMessage.setAskTime(expenseApplayTrain.getStartTime());
        DDSendMessageUtil.sendMessage(ddMessage,"bus");
    }
    /**
     * 发送大巴车报销信息

     * @param id
     * @param toUser
     */
    public static void sendMessageBus(ExpenseApplayBus expenseApplayBus,int id,String toUser){
        DDMessageUtil ddMessage = new DDMessageUtil();
        ddMessage.setMessageUrl(ExpenseApplyResources.approve_Message_URL_BUS+id+"&manager="+toUser);
        ddMessage.setPicUrl(ExpenseApplyResources.approve_Message_picUrl);
        ddMessage.setText("");
        ddMessage.setTitle("大巴车报销申请");
        ddMessage.setToParty("");
        ddMessage.setToUser(toUser);
        ddMessage.setAskStaffName(expenseApplayBus.getStaffName());
        ddMessage.setAskStaffDepartment(expenseApplayBus.getStaffDepart());
        ddMessage.setAskTime(expenseApplayBus.getBeginTime());
        ddMessage.setStartAddress(expenseApplayBus.getStartAddress());
        ddMessage.setEndAddress(expenseApplayBus.getDestination());
        DDSendMessageUtil.sendMessage(ddMessage,"bus");
    }
    /**
     * 发送大巴车报销信息

     * @param id
     * @param toUser
     */
    public static void sendMessageHotel(ExpenseApplayHotel expenseApplayHotel,int id,String toUser){
    	
        DDMessageUtil ddMessage = new DDMessageUtil();
        ddMessage.setMessageUrl(ExpenseApplyResources.approve_Message_URL_HOTEL+id+"&manager="+toUser);
        ddMessage.setPicUrl(ExpenseApplyResources.approve_Message_picUrl);
        ddMessage.setText("");
        ddMessage.setTitle("住宿费用报销申请");
        ddMessage.setToParty("");
        ddMessage.setToUser(toUser);
        ddMessage.setAskStaffName(expenseApplayHotel.getStaffName());
        ddMessage.setAskStaffDepartment(expenseApplayHotel.getStaffDepart());
        ddMessage.setHotelName(expenseApplayHotel.getHotelName());
        ddMessage.setAskTime(expenseApplayHotel.getStartTime());
        DDSendMessageUtil.sendMessage(ddMessage,"hotel");
    }
    /**
     * 发送地铁公交报销申请
     * @param id
     * @param toUser
     */
    public static void sendMessageSubway(ExpenseApplySubway subwayApply,int id,String toUser){
        DDMessageUtil ddMessage = new DDMessageUtil();
        ddMessage.setMessageUrl(ExpenseApplyResources.approve_Message_URL_SUBWAY+id+"&manager="+toUser);
        ddMessage.setText("");
        ddMessage.setTitle("地铁公交报销");
        ddMessage.setToParty("");
        ddMessage.setToUser(toUser);
        ddMessage.setAskStaffName(subwayApply.getAskStaffName());
        ddMessage.setAskStaffDepartment(subwayApply.getAskStaffDepart());
        ddMessage.setAskTime(getCurrentTimeDate());
        DDSendMessageUtil.sendMessage(ddMessage,"subway");
    }
}
