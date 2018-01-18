<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<html>
<head>
    <title>易商用户管理系统</title>

</head>

<body>
    <div>
        <h1 style="color:red">登录</h1>
        <form id="indexform" name="indexForm" action="logincheck.jsp" method="post">
            <table border="0">
                <tr>
                    <td>账号：</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type="password" name="password">
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="登录" style="color:#BC8F8F">
        </form>
        <form action="zhuce.jsp">
            <input type="submit" value="注册" style="color:#BC8F8F">
        </form>
    </div>
</body>
</html>