<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- JSTL标签引入 -->
<%
	String path = request.getContextPath();/*获得当前项目的根路径 */
%>
<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
 <head>
  <link rel="stylesheet" href="<%=path%>/stylesheets/weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/jquery-weui.css"/>
  <link rel="stylesheet" href="<%=path%>/stylesheets/projectcss.css"/>
  <title>住宿报销详细信息查看</title>
 </head>
 <body >
 <form id="form">
  	<!-- <div class="weui_cells" style="border:none;"> -->
  		<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>住宿原因</b></label>
   			 </div>
    	 <div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="reason" value="${expenseApplayHotel.reason }"  readonly>
   		 </div>
     	</div>
     		
  	     <div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>旅馆名称</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="hotelName" value="${expenseApplayHotel.hotelName }" readonly>
   		 </div>
   		 
     	</div>		
  		 <div class="weui_cell ">
  		    <div class="weui_cell_bd weui_cell_primary"><label class="weui_label"><b>住宿日期</b></label></div>
    		<div class="weui_cell_ft weui_cell_primary">
     		   <input class="weui_input" type="text" placeholder=""  name="startTime" value="${expenseApplayHotel.startTime }" readonly>
   		    </div>
  		 </div>
  		
  	    <div class="weui_cell ">
  		    <div class="weui_cell_bd weui_cell_primary"><label class="weui_label"><b>离店日期</b></label></div>
    		<div class="weui_cell_ft weui_cell_primary">
     		   <input class="weui_input" type="text" placeholder=""  name="endTime" value="${expenseApplayHotel.endTime }" readonly>
   		    </div>
  		 </div>
  			
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>金&nbsp;额</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="moneyCost"  value="${expenseApplayHotel.moneyCost }" readonly>
   		 </div>
   		 
     	</div>
     	
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>天&nbsp;数</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder=""  name="daysCost" value="${expenseApplayHotel.daysCost }"  readonly>
   		 </div>
   		 
     	</div>
     		
     	<div class="weui_cell">
    		<div class="weui_cell_bd weui_cell_primary">
      			<label class="weui_label"><b>说&nbsp;明</b></label>
   			 </div>
    	<div class="weui_cell_ft weui_cell_primary">
     		<input class="weui_input" type="text" placeholder="" name="detailExplain" value="${expenseApplayHotel.detailExplain}"  readonly>
   		 </div>
   		 
     	</div>
     	
  	     <hr/>
  	    <div class="weui-row">
             <img src="http://121.40.29.241/YindaOAImageUpload/${expenseApplayHotel.imageUrl}" style="width:100px;height:100px" onclick="imageView(this);"/>
  	     </div>
        <div><a href="javascript:history.go(-1);" class="weui_btn weui_btn_plain_default" >返回</a></div>
  	 <!-- </div> -->
  	</form>	
  	<script src="<%=path%>/javascripts/jquery-2.1.4.js"></script>
    <script src="<%=path%>/javascripts/jquery-weui.js"></script>
    <script type="text/javascript">
  		//图片放大缩小
    function imageView(e){
    	if($(e).css("width")=="100px"){
    		$(e).css("width","300px");
    		$(e).css("height","300px");
    	}else if($(e).css("width")=="300px"){
    		$(e).css("width","100px");
    		$(e).css("height","100px");
    	}
    }
    </script>
 </body> 
</html>
