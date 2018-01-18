<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- JSTL标签引入 -->
<%
	String path = request.getContextPath();/*获得当前项目的根路径 */
%>
<!-- 出租车报销管理员审核界面 -->
<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
 <head>
  <link rel="stylesheet" href="<%=path%>/stylesheets/weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/jquery-weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/projectcss.css"/>
  <title>的士票报销审批</title>
 </head>
 <body >
  <form id="form">
  	<!-- <div class="weui_cells" style="border:none;"> -->
  	 <div style="text-align:center;">---------------------下车---------------------</div>
  	     <br/>
  		<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>市/县</b></label>
   			 </div>
    	 <div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="startCity" value="${expenseApplayTaxi.startCity }" readonly>
   		 </div>
     	</div>	
  	     <div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>地址</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="startAddress" value="${expenseApplayTaxi.startAddress }" readonly>
   		 </div>
   		 
     	</div>		
     	
  		 <div class="weui_cell ">
  		    <div class="weui_cell_bd weui_cell_primary"><label class="weui_label"><b>时间</b></label></div>
    		<div class="weui_cell_ft weui_cell_primary">
     		   <input class="weui_input" type="text" placeholder=""  name="startTime" value="${expenseApplayTaxi.startTime }" readonly>
   		    </div>
  		 </div>
  	
  		 <div class="weui_cell"> 
    		<div class="weui_cell_bd weui_cell_primary"><label class="weui_label"><b>用车事由</b></label></div>
    	     <div class="weui_cell_ft weui_cell_primary">
     	        <input class="weui_input" type="text"   name="reason" value="${expenseApplayTaxi.reason }" readonly>
   		     </div>
  	     </div> 
  	     <div style="text-align:center;">---------------------下车---------------------</div>
  	     <br/>
  	     <div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>地址</b></label>
   			 </div>
    	 <div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="endAddress"  value="${expenseApplayTaxi.endAddress }" readonly>
     		<input class="weui_input" type="text" placeholder="" name="id"  value="${expenseApplayTaxi.id }" readonly style="display:none;">
   		 </div>
   		 
     	</div>
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>时间</b></label>
   			 </div>
    	 <div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="date" placeholder=""  name="endTime"  value="${expenseApplayTaxi.endTime }" readonly>
   		 </div>
   		 
     	</div>
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>金&nbsp;额</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="moneyCost" value="${expenseApplayTaxi.moneyCost }" readonly>
   		 </div>
   		 
     	</div>
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>说&nbsp;明</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="detailExplain" value="${expenseApplayTaxi.detailExplain }"  readonly>
   		 </div>
   		 
     	</div>	
  	     <hr/>
  	     <div class="weui_row">
     	<img src="http://121.40.29.241/YindaOAImageUpload/${expenseApplayTaxi.imageUrl }" style="width:30%;height:10%"/>
     	</div>	
  	    <!--  <div class="weui-row">
  	     <div class="weui_uploader_input_wrp">
            <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multiple="">
  	     </div>
  	     </div> -->
        <div class="weui-row">
			<div class="weui-col-10"></div>
			<div class="weui-col-60"><a onclick="approve_taxi_update();" class="weui_btn  weui_btn_primary" onclick="subForm();">通过</a></div>
			<div class="weui-col-10"></div>
	    </div> 
	    
  	 <!-- </div> -->
  	</form>	
  	<script src="<%=path%>/javascripts/jquery-2.1.4.js"></script>
    <script src="<%=path%>/javascripts/jquery-weui.js"></script>
    <script>
    	function approve_taxi_update(){
    		$.post("approve_taxi_update.do",$("#form").serialize(),function(data){
    			if(data==1){
    				$.alert("审批通过");
    			}else if(data == 0){
    				$.alert("系统繁忙,请稍后重试");
    			}
    		});
    	}
    </script>
 </body> 
</html>
