<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>

<head>
    <title>文件导入结果</title>
    <%--引入excel文件夹的统一样式--%>
    <link rel="stylesheet" type="text/css" href="${ctx}/stylesheets/excel.css" />
</head>

<body class="import">
    <h1>文件导入结果：</h1>
    <h2>成功导出的数据表名为：考勤明细</h2>
    <h2>文件上传成功！</h2>
    <h2>表头效验成功！</h2>
    <h2>导入耗时：${cost}</h2>
    <h2>上传成功的条目数目为：${successAmount}</h2>
    <h2>上传失败的条目数目为：${failAmount}</h2>
    <h2 class="small_title">提交不成功的信息列表：</h2>
    <table>
        <tr>
            <th>部门</th>
            <th>工号</th>
            <th>钉钉UserId</th>
            <th>姓名</th>
            <th>打卡时间</th>
        </tr>
        <c:forEach var="item" items="${listFail}">
            <tr>
                <td>${item.department}</td>
                <td>${item.staffId}</td>
                <td>${item.userid}</td>
                <td>${item.name}</td>
                <td>${item.attendtime}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="${ctx}/ExcelAttendanceDetail/homePage.do"><button>返回上一页</button></a>

</body>

</html>