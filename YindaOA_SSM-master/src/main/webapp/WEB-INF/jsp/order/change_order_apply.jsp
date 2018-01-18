<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"   import="com.ecache.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();/*获得当前项目的根路径 */
    
%>

<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<head>
<link rel="stylesheet" href="<%=path%>/stylesheets/weui.css?v=<%=SystemConfig.version%>" />
<link rel="stylesheet" href="<%=path%>/stylesheets/jquery-weui.css?v=<%=SystemConfig.version%>" />
<link rel="stylesheet" href="<%=path%>/stylesheets/projectcss.css?v=<%=SystemConfig.version%>" />
<script src="<%=path%>/javascripts/jquery-2.1.4.js?v=<%=SystemConfig.version%>"></script>
<script src="<%=path%>/javascripts/jquery-weui.js?v=<%=SystemConfig.version%>"></script>
<script type="text/javascript"
	src="http://g.alicdn.com/ilw/ding/0.7.3/scripts/dingtalk.js?v=<%=SystemConfig.version%>"></script>
<title>项目变动申请</title>
</head>
<style>
.assess {
	background: #04BE02;
	color: white;
	width: 40px;
	height: 40px;
	border-radius: 40px;
	text-align: center;
	line-height: 40px;
}
</style>
<body>
	<div class="weui-pull-to-refresh-layer">
		<div class="pull-to-refresh-arrow"></div>
		<!-- 上下拉动的时候显示的箭头 -->
		<div class="pull-to-refresh-preloader"></div>
		<!-- 正在刷新的菊花 -->
		<div class="down">下拉刷新</div>
		<!-- 下拉过程显示的文案 -->
		<div class="up">释放刷新</div>
		<!-- 下拉超过50px显示的文案 -->
		<div class="refresh">正在刷新...</div>
		<!-- 正在刷新时显示的文案 -->
	</div>

	<form id="divform">
		<div class="weui_cells weui_cells_form ">

			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">变动部门</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="department" id="department">
								<option>请选择</option>
							</select>
						</div>
					</div>
				</div>
			</div>


			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">变动项目</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="project" id="project">
								<option>请选择</option>
							</select>
						</div>
					</div>
				</div>
			</div>



			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">变动订单</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="orderName" id="orderName">
								<option>请选择</option>
							</select>
						</div>
					</div>
				</div>
			</div>


			<!-- <div class="weui_cell weui_vcode">
            <div class="weui_cell_hd"><label class="weui_label">商务属性</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <div class="weui_cell weui_cell_select">
                    <div class="weui_cell_bd weui_cell_primary">
                        <select class="weui_select" name="businessProperty" id="businessProperty">

                        </select>
                    </div>
                </div>
            </div>
         </div> -->



			<div class="weui_cell weui_vcode" style="display: none;">
				<div class="weui_cell_hd">
					<label class="weui_label">商务等级</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="yindaIdentify"
								id="yindaIdentify">

							</select>
						</div>
					</div>
				</div>
			</div>

			<%-- <div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">合同类型</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="contractType" id="contractType" readonly="readonly">
                             <option value="${sessionScope.contractType}">${sessionScope.contractType}</option>
                       
							</select >
							<input class="weui_input" type="text" 
						id="contractType" name="contractType" value="${sessionScope.contractType}" readonly="readonly">
						</div>
					</div>
				</div>
			</div> --%>

<%--			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">LTE认证</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="lte" id="lte">
								<option value="NPO LTE Specialist1">NPO LTE Specialist1</option>
								<option value="NPO LTE Senior">NPO LTE Senior</option>
								<option value="NPO LTE Intermediate">NPO LTE Intermediate</option>
								<option value="Radio LTE Senior">Radio LTE Senior</option>
								<option value="Radio LTE Intermediate">Radio LTE Intermediate</option>
								<option value="LTE双初级">LTE双初级</option>
								<option value="LTE单初级">LTE单初级</option>

							</select>
						</div>
					</div>
				</div>
			</div>--%>

         <div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">合同类型</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text"  value="${sessionScope.contractType}"
						id="contractType" name="contractType" readonly="readonly">
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">变动省份</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="text" placeholder="请输入省份"
						id="changeProvince" name="changeProvince" readonly="readonly">
				</div>
			</div>





<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">变动城市</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="changeCity" id="changeCity">
								
							</select>
						</div>
					</div>
				</div>
			</div>
     
			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">外场工作</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="principal">
								<option value="否">否</option>
								<option value="是">是</option>
							</select>
						</div>
					</div>
				</div>
			</div>

			<div class="weui_cell weui_vcode">
				<div class="weui_cell_hd">
					<label class="weui_label">室外工作</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="outdoor">
								<option value="否">否</option>
								<option value="是">是</option>
							</select>
						</div>
					</div>
				</div>
			</div>
           
      
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">生效日期</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="date" id='datetime-picker'
						name="beginTime">
				</div>
			</div>


			<!--  <div class="weui_cell weui_vcode">
             <div class="weui_cell_hd"><label class="weui_label">备注</label></div>
             <div class="weui_cell_bd weui_cell_primary">
                 <div class="weui_cell weui_cell_select">
                     <div class="weui_cell_bd weui_cell_primary">
                         <select class="weui_select" name="remark" id="remark">

                         </select>
                     </div>
                 </div>
             </div>
         </div> -->
			<!--  <div class="weui_cell">
           <div class="weui_cell_hd"><label for="" class="weui_label" >审批人</label></div>
           <div class="weui_cell_bd weui_cell_primary">
            <div class="assess">刷刷<div>
           </div> -->
		</div>
		</div>


		</div>

		<div class="weui_cell">

			<div class="weui_cell_bd weui_cell_primary">
				<a href="javascript:subForm();" class="weui_btn weui_btn_primary">提交</a>
			</div>
		</div>
	</form>
	<script>
    /** 页面下拉刷新**/
    $(document.body).pullToRefresh();
    $(document.body).on("pull-to-refresh", function() {
        window.location.reload();
        $(document.body).pullToRefreshDone();
    });






    /** 初始化时间输入框 **/
    /* $("#datetime-picker").datetimePicker(); */

    /**
     * 初始化部门
     */
    $(function (){

        var departmenthtml =$("#department").html();
        $.post("<%=path%>/order/getDepartment.do",function(json){

            $.each(json, function (n, value) {

                departmenthtml = departmenthtml+"<option value='"+value+"'>"+value+"</option>";
            });
            $("#department").html(departmenthtml);
        });

        /***初始化商务等级**/
        $.post("<%=path%>/order/getIdentifyInStallInfo.do",function(json){

            var yindaIdentifyhtml;
            $.each(json, function (n, value) {
                yindaIdentifyhtml = yindaIdentifyhtml +"<option value='"+value.yindaIdentify+"' style='display:none;'>"+ value.yindaIdentify +"</option>";

            });

            $("#yindaIdentify").html(yindaIdentifyhtml);
        });


        /**初始化合同类型**/
       <%--  $.post("<%=path%>/order/getContract_typeInStallInfo.do",function(json){

            var contractTypehtml;
            $.each(json, function (n, value) {
                contractTypehtml = contractTypehtml +"<option value='"+value.contractType+"'>"+value.contractType+"</option>";

            });

            $("#contractType").html(contractTypehtml);
        }); --%>
    });
    /**部门变动改变项目 **/
    $("#department").change(function (){
        var projecthtml;
        var department =$(this).val();


        $.post("<%=path%>/order/getProjectByDepartment.do",{'department':department},function(json){
            projecthtml+="<option >请选择</option>";
            $.each(json, function (n, value) {
                projecthtml = projecthtml +"<option value='"+value+"'>"+value+"</option>";

            });

            $("#project").html(projecthtml);
        });

        /**项目变动改变订单**/
        $("#project").change(function (){
            var orderhtml ="<option >请选择</option>";
            var department =$("#department").val();
            var project =$(this).val();

            if(department=="请选择"){$("#orderName").html(" <option >请选择</option>");return;}
            if(project=="请选择"){$("#orderName").html("  <option >请选择</option>");return;}
            $.post("<%=path%>/order/getOrderByDepartmentAndProject.do",{'department':department,'project':project},function(json){
                $.each(json, function (n, value) {
                    orderhtml = orderhtml +"<option value='"+value+"'>"+value+"</option>";

                });

                $("#orderName").html(orderhtml);
            });


        });

        /**部门变动改变订单 **/
        $("#department").change(function (){
            var orderhtml ="<option >请选择</option>";
            var department =$("#department").val();
            var project =$("#project").val();

            if(department=="请选择"){$("#orderName").html(" <option >请选择</option>");return;}
            if(project=="请选择"){$("#orderName").html("<option >请选择</option>");return;}
            $.post("<%=path%>/order/getOrderByDepartmentAndProject.do",{'department':department,'project':project},function(json){
                $.each(json, function (n, value) {
                    orderhtml = orderhtml +"<option value='"+value.orderName+"'>"+value.orderName+"</option>";

                });

                $("#orderName").html(orderhtml);
            });

        });

        $("#orderName").change(function (){
            var orderName = $("#orderName").val();
            var  changeCityHTML ="<option value = ''>请选择</option>";
            $.post("<%=path%>/orderProperty/getOrderProvince.do",{'orderName':orderName},function (data){

                $("#changeProvince").val(data);

            })

            $.post("<%=path%>/orderProperty/getOrderCity.do",{'orderName':orderName},function (data){
                $.each(data, function (n, value) {
                    changeCityHTML = changeCityHTML +"<option value='"+value+"'>"+value+"</option>";
                });
                $("#changeCity").html(changeCityHTML);

            })

            var businessPropertyHTML;
            $.post("<%=path%>/orderProperty/getBusinessProperty.do",{'orderName':orderName},function (data){
                $.each(data, function (n, value) {
                    businessPropertyHTML = businessPropertyHTML +"<option value='"+value+"'>"+value+"</option>";
                });
                $("#businessProperty").html(businessPropertyHTML);

            });

            var remarkHTML;
            $.post("<%=path%>/order/getRemarkByOrder.do",{'orderName':orderName},function (data){
                $.each(data, function (n, value) {

                    remarkHTML = remarkHTML +"<option value='"+value+"'>"+value+"</option>";
                });
                $("#remark").html(remarkHTML);

            });


        });


    });



    function subForm()
    {
        var beginTime = $("#datetime-picker").val();
        var changeCity =$("select[name='changeCity']").val();
       
        var  orderName =$("#orderName").val();
        if(beginTime==null || ""==beginTime){
            
            $.alert("生效日期不能为空！");
            return;
        }
        /*获取当前日期*/
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        if(month<10){
        	month = "0"+month;
        }
        if(day<10){
        	day = "0"+day;
        }
        var nowtime = year+"-"+month+"-"+day;
	    if(nowtime>beginTime){
	    	$.alert("生效日期必须大于或等于当前日期");
		     return;
	      }
      
        if(changeCity==null || "" == changeCity){
            $.alert("变动城市不能为空！");
            return;
        }

        if(orderName==null || ""== orderName || "请选择" == orderName ){
            $.alert("订单不能为空！");
            return;
        }
        $.post("<%=path%>/ItemChange/add_ItemChange.do",$("#divform").serialize(),function(data){

            if("success"==data){
                $.alert("申请成功！");
                window.history.go(-1);

            }else{
                $.alert(data);
            }
        });
    }

</script>
</body>
</html>
