<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>管理控制台</title>
    <link rel="stylesheet" href="css/index_admin.css" type="text/css"></link>
  </head>
 
  <body>
    <div id="header">
      <s:property value="#session.loginedUser.role.roleName" />:
      <a href="#"><s:property value="#session.loginedUser.empName" /></a>
      <a href="#">注销</a>
    </div>
    <div id="main">
      <div id="sidebar">
        <jsp:include page="/jsp/commons/left.jsp" />
      </div>
      <div id="content"></div>
    </div>
  </body>
</html>
