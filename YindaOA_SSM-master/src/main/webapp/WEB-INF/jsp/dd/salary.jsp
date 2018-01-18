<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/8
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
    <title>个人财务</title>

    <!-- 日期插件引入文件 -->
    <meta name="format-detection" content="telephone=no"/>

    <script src="<%=basePath%>/javascripts/jquery-1.10.2.js"></script>
    <link href="<%=basePath%>/datePlug/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath%>/datePlug/mobiscroll.custom-2.5.0.min.js" type="text/javascript"></script>
    <script src="../javascripts//knockout-3.4.0rc.js"></script>
    <!--日期插件引入文件结束  -->
    <link rel="stylesheet" href="<%=basePath%>/stylesheets/buttons.css">
    <script type="text/javascript" src="<%=basePath%>javascripts/zepto.min.js"></script>
    <script type="text/javascript" src="http://g.alicdn.com/ilw/ding/0.7.3/scripts/dingtalk.js"></script>
    <style>
        /*引入字体图标*/
        @font-face {
            font-family: 'iconfont';
            src: url('<%=basePath%>/fonts/iconfont/iconfont.eot'); /* IE9*/
            src: url('<%=basePath%>/fonts/iconfont/iconfont.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */
            url('<%=basePath%>/fonts/iconfont/iconfont.woff') format('woff'), /* chrome、firefox */
            url('<%=basePath%>/fonts/iconfont/iconfont.ttf') format('truetype'), /* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
            url('<%=basePath%>/fonts/iconfont/iconfont.svg#iconfont') format('svg'); /* iOS 4.1- */
        }
        .iconfont{
            font-family:"iconfont" !important;
            font-size:25px;font-style:normal;
            -webkit-font-smoothing: antialiased;
            -webkit-text-stroke-width: 0.2px;
            -moz-osx-font-smoothing: grayscale;
        }
        /*样式重置*/
        *{margin:0;padding:0;}
        ul li{list-style:none;}
        a{text-decoration: none;}
        .fl{float:left;}
        .fr{float:right;}
        /*样式*/
        html{background:#F2F2F2;color:#585A5D;}
        header{background:#2FA1F3;overflow: hidden;position: fixed;top:0;width:100%;padding:4% 0;color:#fff;box-shadow: 0px 5px 40px #000;}
        /*        header .person-pho{width:12%;float:left;margin-left:1%;}
                header .person-pho img{width:100%;border-radius: 50%;display: block;vertical-align: middle;}
                header .person-msg{float:left;padding-top:3%;font-size: 20px;}
                header .head-ser{float:right;padding-top:3%;}*/
        header .head-ico{border-right:1px solid #E5E5E5;padding:0 2%;margin:0.3% 3% 0.3% 0;}
        header .head-msg,header .head-ser{line-height:1.5;}
        header .head-ser{margin-right:2%;}

        .content{margin-top:12%;background:#F2F2F2;}
        .content .content-top{background:#2FA1F3;}
        .content .content-top .affair-msg{border-bottom: 1px solid #fff;padding:8% 0 2% 0;}
        .content .content-top .affair-msg p{text-align: center;font-size: 16px;color:#fff;}
        .content .content-top .affair-msg p:nth-child(2){font-size:45px;margin:2% 0;}
        .content .content-top .other{overflow:hidden;}
        .content .content-top .other a{display:block;width:33%;padding:4% 0;float:left;border-right:1px solid #fff;border-bottom:1px solid #fff;text-align: center;color:#fff;}
        .content .content-top .other a:nth-child(3){border-right:transparent;}
        .content .content-top .other a:nth-child(6){border-right:transparent;}

        .content .content-ser .tit-1{padding:3% 0;background:#fff;border-top:1px solid #E4E4E4;border-bottom:1px solid #E4E4E4;margin-top:2%;}
        .content .content-ser  .tit-1 p{font-size: 14px;margin-left:2%;}
        .content .content-ser .ser-details{margin-top:1%;overflow: hidden;}
        .content .content-ser .ser-details .person-pho{width:12%;float:left;margin-left:1%;}
        .content .content-ser .ser-details .person-pho img{width:100%;border-radius: 50%;}
        .content .content-ser .ser-details .person-msg{float:left;padding-top:4%;font-size: 14px;}
        .content .content-ser .ser-details .ser-date{font-size:14px;width:60%;}
        .content .content-ser .ser-details .ser-date input{display: inline-block;width: 28%;height:40px;border-radius: 10px; border: 1px solid #D5E5FF;text-align: center;color: #2FA1F3;}
        .content .content-ser .ser-details button{background:#2FA1F3;color:#fff;font-size:16px;border:none;border-radius: 5px;padding:0;width:30%;}

        /*        .content .content-ser .ser-details{margin-top:1%;overflow: hidden;}
                .content .content-ser .ser-details .person-pho{width:12%;float:left;margin-left:1%;}
                .content .content-ser .ser-details .person-pho img{width:100%;border-radius: 50%;}
                .content .content-ser .ser-details .person-msg{float:left;padding-top:4%;font-size: 14px;}
                .content .content-ser .ser-details .ser-date{float:right;margin-top:4%;font-size:14px;margin-right:2%;width:50%;}
                .content .content-ser .ser-details .ser-date input{display:inline-block;width:45%;}*/
    </style>
</head>
<body>
<div class="box">
   <header>
       <%-- <div class="head-ico fl"><i class="iconfont">&#xe82f;</i></div>--%>
        <div class="head-msg fl">上海音达科技实业有限公司</div>
        <%--<div class="head-ser fr">帮助</div>--%>
    </header>
    <div class="content">
        <div class="content-top" data-bind="with:changeItem">
            <div class="affair-msg">
                <p style="font-size:24px" id="date" data-bind="text:salarydate"></p><p style="font-size:24px">月收入</p>
                <p style="font-size:34px" id = "total"data-bind="text:realsalary">0.00</p>
                <br>
                <p><a href=javascript:getAll("user/phone-details.do")>点击查看详情</a></p>
            </div>
            <div class="other">
                <a href=javascript:getQuery("Calendar_EffectAttendance.do")>有效出勤<p id="kq"></p></a>
                <a href=javascript:getQuery("Calendar_RealAttendance.do")>奖金天数<p id="jb"></p></a>
                <a >日报<p id="qj"></p></a>
                <a href=javascript:getQuery("Calendar_Evenctime.do")>出差<p id="cc"></p></a>
                <a href=javascript:getQuery("Calendar_Leavetype.do")>请假<p id="zs"></p></a>
                <a href=javascript:getQuery("Calendar_Worktime.do")>加班<p id="yx"></p></a>
            </div>
        </div>

        <div class="content-ser">
            <!--                     <div class="tit-1">
                                    <p>历史工资查询</p>
                                </div> -->
            <div class="ser-details">
                <!--                         <div class="person-pho"><img src="img/I.JPG" alt=""></div>
                                        <div class="person-msg">上海音达，Rose</div> -->
                <div class="ser-date fl">请选择日期：<input id="scroller" name="scroller"></div>
                <button class="button button-raised button-rounded button-royal" data-bind="click:$root.ClickQuery">查询</button>
            </div>
        </div>

        <input id="userid" style="display: none;">
        <input id="code" style="display: none;">
    </div>
</div>

<script>
    function getPreMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        var year2 = year;
        var month2 = parseInt(month) - 1;
        if (day <= 20) {
            month2 = month;
        }
        if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2;  //+ '-' + day2;

        return t2;
    }
//  日期插件代码开始
$(function(){
    $("#scroller").mobiscroll().date();


    var currDate =  (new Date());
    var currYear = currDate.getFullYear();
    var curr = currDate.getFullYear() +'-' + (currDate.getMonth()+1) + '-' + (currDate.getDay());
    //初始化日期控件
    var opt = {
        preset: 'date', //日期，可选：date\datetime\time\tree_list\image_text\select
        theme: 'android-ics light', //皮肤样式，可选：default\android\android-ics light\android-ics\ios\jqm\sense-ui\wp light\wp
        display: 'modal', //显示方式 ，可选：modal\inline\bubble\top\bottom
        mode: 'scroller', //日期选择模式，可选：scroller\clickpick\mixed
        lang:'zh',
        dateFormat: 'yyyy-mm', // 日期格式
        setText: '确定', //确认按钮名称
        cancelText: '取消',//取消按钮名籍我
        dateOrder: 'yyyymm', //面板中日期排列格式
        monthText: '月', yearText: '年', //面板中年月日文字
        showNow: false,
        nowText: "今",
        startYear:currYear, //开始年份
        endYear:currYear  //结束年份
        //endYear:2099 //结束年份
    };
    $("#scroller").mobiscroll(opt).scroller('setDate', new Date(getPreMonth(curr)), true);
});
//  日期插件代码结束

</script>

<script type="text/javascript">
    //在此拿到权限验证配置所需要的信息
    var _config = <%= request.getAttribute("config") %>;
    //当前用户，当前时间
    var nowUser = null;
    var nowTime = null;
    var code = null;//用户授权码


    //打开链接
    function openLink(url) {
        dd.biz.util.openLink({
            name:url,//页面名称
            params:{"date":nowTime},//传参
            onSuccess: function (result) {
            },
            onFail: function (err) {
                alert(JSON.stringify(err));
            }
        });
    }
    //显示信息
    function showMsg(Msg) {
        dd.device.notification.alert({
            message: Msg,
            title: "message",//可传空
            buttonName: "收到",
            onSuccess: function () {
                //onSuccess将在点击button之后回调
                /*回调*/
            },
            onFail: function (err) {
            }
        });
    }

    $(document).ready(function () {
        //配置钉钉jsapi
        dd.config({
            agentId : "38433641",
            corpId : _config.corpId,
            timeStamp : _config.timeStamp,
            nonceStr : _config.nonceStr,
            signature : _config.signature,
            jsApiList : [ 'runtime.info', 'biz.contact.choose',
                'device.notification.confirm', 'device.notification.alert',
                'device.notification.prompt', 'biz.ding.post',
                'biz.util.openLink' ]
        });
        dd.ready(function() {
            dd.biz.navigation.setTitle({
                title: '工资单',
                onSuccess: function(data) {
                },
                onFail: function(err) {
                    log.e(JSON.stringify(err));
                }
            });
            dd.ui.pullToRefresh.enable({
                onSuccess: function() {
                },
                onFail: function() {
                }
            });

            dd.runtime.permission.requestAuthCode({
                corpId : _config.corpId,
                onSuccess : function(info) {
                    //alert('authcode: ' + info.code);
                    $("#code").val(info.code);
                    $.ajax({
                        data:$("#code").val(),
                        type:"post",
                        headers: { 'Content-Type': 'application/json' },
                        dataType: 'json',
                        async:'false',
                        url:"../usersalary/queryUserid.do",
                        error:function(data){
                            alert("出错了！！:"+data.msg);
                        },
                        success:function(data){
                            result = data.userid;
                            $("#userid").val(result);
                            nowUser = result;
                            //alert(result);
                        }
                    });

                },
                onFail : function(err) {
                    alert('fail: ' + JSON.stringify(err));
                }
            });
        });
        dd.error(function(err) {
            //alert('dd error: ' + JSON.stringify(err));
        });
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
                self.GetSalaryByQuery();

            });


            //===============================
            //查询工资单（userid，date）
            self.GetSalaryByQuery = function(){

                nowTime = $("#scroller").val();
                nowUser = $("#userid").val();
                //alert("当前用户是:"+nowUser + "\n当前日期是:" + nowTime);
                $.ajax({
                    data:JSON.stringify(new UserModel(nowUser,nowTime)),
                    type:"post",
                    headers: { 'Content-Type': 'application/json' },
                    dataType: 'json',
                    url:"../usersalary/querySalary.do",
                    error:function(data){
                        alert("出错了！！:"+data.msg);
                    },
                    success:function(data){
                        result = eval(data.total);
                        self.changeItem(result);
                    }
                });

            }

            //点击事件-点击添加用户按钮
            self.ClickQuery = function(){
                self.GetSalaryByQuery();
            };



            //点击事件-点击搜索
            self.ClickSearch = function () {
                self.GetUserByQuery();
            }
            //点击事件-点击清空搜索项
            self.ClickClear = function() {

                $("#search_name").val("");
                $("#search_workid").val("");
                $("#search_phone").val("");

            }
        }
        ko.applyBindings(new ViewModel);
    });


    function UserModel(userid,date) {
        this.userid = userid;
        this.salarydate = date;
        return this;
    }

    function getQuery(queryName){
        var queryString = "<%=basePath%>Calendar/"+queryName + "?userid="+nowUser + "&date="+nowTime;
        window.location = queryString;
    }
    function getAll(queryName){
        //alert("?userid="+nowUser + "&date="+nowTime);
        var queryString = "<%=basePath%>user/phone-details.do"+"?userid="+nowUser + "&date="+nowTime;
        window.location = queryString;
    }
</script>
</body>
</html>

