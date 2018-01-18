<%--
  Created by IntelliJ IDEA.
  User: ma
  Date: 2016/10/20
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
    <meta charset="UTF-8">
    <title>工资查询</title>
    <link rel="shortcut icon" type="image/ico" href="../images/yd.ico" />
    <link rel="stylesheet" href="../stylesheets/reset.css">
    <link rel="stylesheet" href="../stylesheets/buttons.css">
    <link rel="stylesheet" href="../stylesheets/header.css">
    <link rel="stylesheet" href="../stylesheets/affairs-search.css">
    <link rel="stylesheet" href="../datePlug/jquery.monthpicker.css">
    <script src="../javascripts/jquery-1.10.2.js"></script>
    <script src="../datePlug/jquery.monthpicker.js"></script>
    <%--<script type="text/javascript" src="../javascripts/bootstrap.min.js"></script>--%>
    <%--<script type="text/javascript" src="../javascripts/bootstrap-treeview.min.js"></script>--%>
    <script src="../javascripts/knockout-3.4.0rc.js"></script>
    <script type="text/javascript">
        var result = null;

        //============================================
        //当前选择的部门
        var nowDep = null;
        //最后一次触发节点Id
        var lastSelectedNodeId = null;
        //最后一次触发时间
        var lastSelectTime = null;
        //部门树
        var tree = [];
        //当前显示的页码
        var showindex = 0;
        //当前显示的分页条目
        var showpage = 20;

        function  dateTransform(date){
            var y=date.getFullYear();
            var m=date.getMonth()+1;
            var d=date.getDate();
            console.log(y+"-"+m+"-"+d)
        }


        $(document).ready(function () {

            var ViewModel = function () {
                var self = this;
                //变量区

                self.showTree = ko.observableArray();
                //当前显示的树列表
                self.rootid = ko.observable();
                //搜索的知识树编号
                self.classid = ko.observable();

                //待修改题目
                self.overItem = ko.observable(0);
                self.allItem = ko.observable(0);
                self.allCount = ko.observable(0);
                //==============================
                self.AllList = ko.observableArray();
                //绑定题目列表对象

                //当前被修改的用户信息
                self.changeItem = ko.observable();
                //当前显示的人员列表
                self.ShowList = ko.observableArray();

                //ko初始化数据加载
                $(function () {


                });
                //查询成员列表（部门，姓名，电话，工号）
                self.GetUserByQuery = function(){
//                if (nowDep != null){var depid = nowDep.name;} else {depid = null;}
                    $.ajax({
                        data:JSON.stringify(new UserModel($("#search_name").val(),$("#search_date").val())),
                        type:"post",
                        headers: { 'Content-Type': 'application/json' },
                        dataType: 'json',
                        url:"../usersalary/query.do",
                        error:function(data){
                            alert("出错了！！:"+data.msg);
                        },
                        success:function(data){
                            result = eval(data.list);
                            total = eval(data.total);
                            self.changeItem(total);
                            self.ShowList.removeAll();
                            for (var i = 0; i < result.length; i++) {
                                self.ShowList.push(result[i]);
                            }
                            //self.GetUserListByDep(nowDep.name);
                        }
                    });
                }
                //日期转换器
                self.ClickSearch = function () {
                    self.GetUserByQuery();
                    return true;
                }

                //点击事件-点击清空搜索项
                self.ClickClear = function() {
                    $("#search_name").val("");

                }
                self.ClickGenerate = function(){
                    $.ajax({
                        data:JSON.stringify(new UserModel($("#search_name").val(),$("#search_date").val())),
                        type:"post",
                        headers: { 'Content-Type': 'application/json' },
                        dataType: 'json',
                        url:"../usersalary/generate.do",
                        error:function(data){
                            alert("出错了！！:"+data.msg);
                        },
                        success:function(data){
                            alert(date);
                        }
                    });
                }

            }
            ko.applyBindings(new ViewModel);
        });
        function UserModel(name,salarydate) {
            this.salarydate=salarydate;
            this.userid = null;
            this.staffid = name;
            return this;
        }
        function getLocalTime(nS) {
            return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
        }
    </script>
</head>
<body>

<header>
    <div class="head-cont">
        <div class="head-left fl">
            <img src="../images/logo.png" height="35" width="50" alt="">
            人事管理系统
        </div>
        <div class="head-nav fl" id="h-nav">
            <ul>
                <li><a data-bind="attr: { href: '<%=basePath%>ExcelStaffInfo/homePage.do'}">人员导入</a></li>
                <li><a data-bind="attr: { href: '<%=basePath%>userinfo/testMethod.do'}">通讯录</a></li>
                <li><a  data-bind="attr: { href: '<%=basePath%>Import/navigator.do'}">审批数据导入</a></li>
                <li><a class="active" data-bind="attr: { href: '<%=basePath%>userinfo/test.do'}">工资查询</a></li>
                <li><a data-bind="attr: { href: '<%=basePath%>userinfo/querys.do'}">个人工资明细</a></li>
                <li><a href="<%=basePath%>toAskForLeave.do">请假明细</a>
                <li><a href="<%=basePath%>toOverTime.do">加班明细</a>
                <li><a href="<%=basePath%>toItemChange.do">项目变更</a>
                <li><a href="<%=basePath%>toSocialSecurity.do">社保明细查看</a>
            </ul>
        </div>
        <div class="head-right fr">
            欢迎您！管理员&nbsp;&nbsp;&nbsp;
            <a href=""><img src="../images/guanbi.png" height="22" width="22" alt=""></a>
        </div>
    </div>
</header>
<div class="contain">
    <div class="content">
        <div class="cont-tit">
            工资查询
        </div>
        <div class="search">
            <div class="ser-input fl">
                工号：<input type="text" id="search_name" placeholder="输入工号">
                日期：<input type="text"  id="search_date" placeholder="输入查询日期" class="input" >
            </div>
            <div class="ser-btn fr">
                <button class="button button-glow button-rounded button-primary button-small " data-bind="click:$root.ClickGenerate">生成</button>
                <button class="button button-glow button-rounded button-primary button-small " data-bind="click:$root.ClickSearch">查询</button>
                <button class="button button-glow button-rounded button-highlight button-small " data-bind="click:$root.ClickClear" >清空</button>

            </div>
        </div>
        <div style="width:100%; height:700px;padding-top: 5px;overflow:auto;border:0 solid #000000;">
            <div class="ser-resault" >
                <table border="1">
                    <thead>
                    <tr >
                        <th>工号</th>
                        <th>日期</th>
                        <th>日期类型</th>
                        <th>打卡情况</th>
                        <th>有效考勤</th>
                        <th>出勤工资</th>
                        <th>请假类型</th>
                        <th>请假补款</th>
                        <th>加班</th>
                        <th>加班工资</th>
                        <th>出差</th>
                        <th>出差补贴</th>
                        <th>time认证奖金</th>
                        <th>task报告数</th>
                        <th>task报告奖金</th>
                        <th>交通费</th>
                        <th>额外奖金</th>
                        <th>工作地点</th>
                        <th>日总额</th>
                    </tr>
                    </thead>
                    <tbody data-bind="foreach:ShowList">
                    <tr >
                        <%--<td data-bind="text:name">姓名</td>--%>
                        <td data-bind="text:staffid">用户编号</td>
                        <td data-bind="text:getLocalTime(date)"></td>
                        <td data-bind="text:datetype">日期类型</td>
                        <td data-bind="text:attendance">知识树编号</td>
                        <td data-bind="text:effectiveAttendance">知识树编号</td>
                        <td data-bind="text:attendanceSalary">所属知识</td>
                        <td data-bind="text:leavetype">修改者</td>
                        <td data-bind="text:leavesalary">审核状态</td>
                        <td data-bind="text:workovertime">ok的</td>
                        <td data-bind="text:worksalary">所属知识</td>
                        <td data-bind="text:evection">修改者</td>
                        <td data-bind="text:allowance">审核状态</td>
                        <td data-bind="text:timesalary">到大</td>
                        <td data-bind="text:task">所属知识</td>
                        <td data-bind="text:tasksalary">修改者</td>
                        <td data-bind="text:trafficsalary">审核状态</td>
                        <td data-bind="text:bonusSalary">卫视</td>
                        <td data-bind="text:workAddress">卫视</td>
                        <td data-bind="text:daySalary">卫视</td>
                    </tr>
                    </tbody>
                    <thead>
                    <tr data-bind="with:changeItem">
                        <th >合计</th>
                        <td data-bind="text:name">用户编号</td>
<%--                        <td data-bind="text:salarydate">日期类型</td>--%>
                        <td data-bind="text:datetype">日期类型</td>
                        <td data-bind="text:attendance">知识树编号</td>
                        <td data-bind="text:effectiveattendance">知识树编号</td>
                        <td data-bind="text:attendancesalary">所属知识</td>
                        <td data-bind="text:leavetype">修改者</td>
                        <td data-bind="text:leavesalary">审核状态</td>
                        <td data-bind="text:workovertime">ok的</td>
                        <td data-bind="text:worksalary">所属知识</td>
                        <td data-bind="text:evection">修改者</td>
                        <td data-bind="text:allowance">审核状态</td>
                        <td data-bind="text:timesalary">到大</td>
                        <td >报告数</td>
                        <td data-bind="text:tasksalary">修改者</td>
                        <td data-bind="text:trafficsalary">审核状态</td>
                        <td data-bind="text:userbonus">卫视</td>
                        <td data-bind="text:subtotal">卫视</td>
                        <td data-bind="text:totalsalary">卫视</td>

                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<footer>
    <p><img src="../images/tubiao.png" alt="">上海音达科技实业有限公司</p>
</footer>

<script>
    // 日期插件开始
    $('#monthpicker').monthpicker({
        years: [2017,2016,2015, 2014, 2013, 2012, 2011,2010,2009],
        topOffset: 6,
        onMonthSelect: function(m, y) {
            console.log('Month: ' + m + ', year: ' + y);
        }
    });
    $('#search_date').monthpicker({
        years: [2017,2016,2015, 2014, 2013, 2012, 2011,2010,2009],
        topOffset: 6
    });
    //日期插件结束
</script>
</body>
</html>
