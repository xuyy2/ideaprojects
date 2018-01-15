<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <link rel="stylesheet" href="css/index_admin.css" type="text/css"></link>
  </head>
 
  <body>
    <ul>
      <!-- 遍历权限集合，显示一级权限菜单 -->
      <c:forEach items="${loginedUser.role.rights }" var="right">
      <c:if test="${empty right.parent }">
        <li>${right.rightName }
          <ul>
          <!-- 嵌套内层循环，遍历 权限集合，显示该一级权限菜单下面的二级权限菜单-->
          <c:forEach items="${loginedUser.role.rights }" var="child">
          <c:if test="${child.parent eq right and child.rightType eq 1}" >             
            <li>
              <a href="${child.rightUrl }">
            	${child.rightName }
              </a>
            </li>
          </c:if>  
          </c:forEach>  
          </ul>
        </li>        
      </c:if>
      </c:forEach>
    </ul>
    <%-- <ul>
      <!-- 遍历权限集合，显示一级权限菜单 -->
      <s:iterator value="#session.loginedUser.role.rights" var="right">
      <s:if test="#right.parent==null">  <!-- 如果父权限为空，则表示为一级权限菜单 -->
        <li><s:property value="#right.rightName" />
          <ul>
          <!-- 嵌套内层循环，遍历 权限集合，显示该一级权限菜单下面的二级权限菜单-->
          <s:iterator value="#session.loginedUser.role.rights" var="child">
          <s:if test="parent==#right && rightType==1" >             
            <li>
              <a href='<s:property value="rightUrl" />'>
              <s:property value="{rightName}" />
              </a>
            </li>
          </s:if>  
          </s:iterator>  
          </ul>
        </li>        
      </s:if>
      </s:iterator>
    </ul> --%>
  </body>
</html>        