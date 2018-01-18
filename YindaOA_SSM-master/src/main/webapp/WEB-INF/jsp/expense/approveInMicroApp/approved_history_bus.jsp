<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- JSTL标签引入 -->
<%
	String path = request.getContextPath();/*获得当前项目的根路径 */
%>
<!-- 页面复用大巴、火车、旅店的已审批报销信息都在该页面做列表展示 -->
<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
 <head>
  <link rel="stylesheet" href="<%=path%>/stylesheets/weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/jquery-weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/projectcss.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/expense.css"/>
  <title>已审批报销列表展示</title>
 </head>
 <body >
 <body >
  	<div class="weui-row table-header">
  		<div class="weui-col-20" style="overflow: hidden;"><b>报销人</b></div>
  		<div class="weui-col-20" style="overflow: hidden;"><b>报销金额</b></div>
  		<div class="weui-col-20" style="overflow: hidden;"><b>报销状态</b></div>
  		<a><b style="color:#696969;">操作</b></a>
  		<a></a>
  		<!-- <a></a> -->
    </div>
    <hr/>
    <c:forEach items="${approvedList }" var="approved">
 	<div class="weui-row font_content">
  		<div class="weui-col-20" style="overflow: hidden;">${approved.staffName}</div>
  		<div class="weui-col-20" style="overflow: hidden;">${approved.moneyCost}</div>
  		<div class="weui-col-20" style="overflow: hidden;">${approved.applayStatus}</div>
  		<button type="button" style="background-color:#32CD32;" onclick="approvedView('${approved.id}','${type}');">查看</button>
  		<a></a>
  		<!-- <a href="javascript:history.go(-1);">返回</a> -->
    </div>
    <hr/>
    </c:forEach>
    <br/>
    <div><a href="javascript:window.history.go(-1);" class="weui_btn weui_btn_plain_default" >返回</a></div>
    <script>
    
    function approvedView(id,type){
		if("train" == type){
			window.location.href="go_expense_view_train.do?id="+id;
		}
		if("bus" == type){
			window.location.href="go_expense_view_bus.do?id="+id;
		}
		if("hotel" == type){
			window.location.href="go_expense_view_hotel.do?id="+id;
		}
		if("subway" == type){
			window.location.href="go_expense_view_subway.do?id="+id;
		}
	}
    </script>
 </body> 
    <script src="<%=path%>/javascripts/jquery-2.1.4.js"></script>
    <script src="<%=path%>/javascripts/jquery-weui.js"></script>
     <script type="text/javascript" src="http://g.alicdn.com/ilw/ding/0.7.3/scripts/dingtalk.js"></script>
 </body> 
</html>
