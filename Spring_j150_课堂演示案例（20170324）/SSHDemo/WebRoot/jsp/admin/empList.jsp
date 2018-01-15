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
    
    <title>用户查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/index_admin.css" type="text/css"></link>
    <script type="text/javascript">
      function doEmpList(currPageNo){  
        alert("ok...");    
        var txtCurrPageNo=document.getElementById("txtCurrPageNo");
        //将要查询页码值设置给隐藏表单域
        txtCurrPageNo.value=currPageNo;
        //alert("ok");           
        //调用表单提交，会将查询条件一起提交
        document.getElementById("frmEmpList").submit();
      }
    </script>
  </head>
  
  <body>
    <div id="header">系统管理员:<a href="#"><s:property value="#session.logidedUser.empName" /></a></div>
    <div id="main">
      <div id="sidebar">
         <jsp:include page="/jsp/commons/left.jsp" />
      </div>
      <div id="content">
        <h3>查询用户</h3>
        <hr />
        <form id="frmEmpList" action="employee/listAction" method="post">
           <!-- 把要查询的页码放在隐藏表单域中，默认值为1 -->
           <input type="hidden" id="txtCurrPageNo" name="pageUtil.currPageNo" value="1" />
		         员工姓名：<input type="text" name="condition.empName" value='<s:property value="condition.empName" />' /> <br/>
		          出生日期：<input type="text" name="condition.start" value='<s:date name="condition.start" format="yyyy-MM-dd"/>' />
		          ——<input type="text" name="condition.end" value='<s:date name="condition.end" format="yyyy-MM-dd"/>' />（yyyy-mm-dd）<br/>
		          角色:
	        <select name="condition.roleId">
	          <option value="0">全部</option>
	          <option value="1" <s:if test="condition.roleId==1">selected="selected"</s:if>>系统管理员</option>
	          <option value="2" <s:if test="condition.roleId==2">selected="selected"</s:if>>总经理</option>
	          <option value="3" <s:if test="condition.roleId==3">selected="selected"</s:if>>部门经理</option>
	          <option value="4" <s:if test="condition.roleId==4">selected="selected"</s:if>>财务经理</option>
	          <option value="5" <s:if test="condition.roleId==5">selected="selected"</s:if>>员工</option>
	        </select>
	       
	                       状态:
	        <select name="status">
	          <option value="-1">全部</option>
	          <option value="1" <s:if test="condition.status==1">selected="selected"</s:if>>正常</option>
	          <option value="0" <s:if test="condition.status==0">selected="selected"</s:if>>禁用</option>
	        </select><br/>	
		  <input type="submit" value="查询" />	      
        </form>
        <table border="1" cellpadding="5">
          <tr bgcolor="#CCCCCC">
            <td>ID</td>
            <td>员工姓名</td>
            <td>出生时间</td>
            <td>角色</td>
            <td>状态</td>
          </tr>
          <s:if test="pageUtil.list.size()==0">
          <tr>
            <td colspan="5">没有您要查找的数据</td>
          </tr>
          </s:if>
          <s:if  test="pageUtil.list.size()!=0">
          <s:iterator value="pageUtil.list" var="emp" status="status" >                  
          <tr <s:if test="#status.even">bgcolor="#CCCCCC"</s:if>>
            <td><s:property value="empId" /></td>
            <td><s:property value="empName" /> </td>
            <td><s:date name="birthday" format="yyyy-MM-dd" /></td>
            <td><s:property value="role.roleName" /></td>
            <td><s:property value="status==1?'正常':'禁用'" /></td>
          </tr>    
          </s:iterator>
          <tr>
            <td colspan="5">
           <%--  <s:url var="home" value="employee/listAction">
		      <s:param name="pageUtil.pageNo" value="1"></s:param>
		      <s:param name="employee.empName" value="zhang"></s:param>
		    </s:url>
		    <s:a href="%{#home}">首页</s:a>
		    <a href='<s:url value="employee/listAction?pageUtil.currPageNo=1" ></s:url>'>首页</a>
		    <s:url var="pre" value="employee/listAction">
		      <s:param name="pageUtil.currPageNo" value="pageUtil.prePageNo"></s:param>
		    </s:url>
		    <s:a href="%{#pre}">上一页</s:a>
		    
		    <s:url var="next" value="employee/listAction">
		      <s:param name="pageUtil.currPageNo" value="pageUtil.nextPageNo"></s:param>
		    </s:url>
		    <s:a href="%{#next}">下一页</s:a>
		    
		    <s:url var="end" value="employee/listAction">
		      <s:param name="pageUtil.currPageNo" value="pageUtil.totalPages"></s:param>
		    </s:url>
		    <s:a href="%{#end}">末页</s:a>
            第<s:property value="pageUtil.currPageNo"/>页/共 <s:property value="pageUtil.totalPages"/>页  --%>
             
              <a href="javascript:doEmpList(1);">首页</a>
              <a href='javascript:doEmpList(<s:property value="pageUtil.prePageNo"/>);'>上一页</a>
              <a href='javascript:doEmpList(<s:property value="pageUtil.nextPageNo"/>);'>下一页</a>
              <a href='javascript:doEmpList(<s:property value="pageUtil.totalPages"/>);'>末页</a>
                                          第<s:property value="pageUtil.currPageNo"/>页/共<s:property value="pageUtil.totalPages"/>页
            </td>
          </tr>      
          </s:if>          
        </table>
      </div>
    </div>
  </body>
</html>
