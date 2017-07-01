<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<%@  taglib  uri="http://testTag/lxc"   prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>门诊查询--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
<script type="text/javascript" src="Js/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
	 $(function () {
		$('#newNav').click(function(){
				window.location.href="ReigisterServlet.do?method=linkage";
		 });
    });
	
		function createHttpXML(){
			xhp=new XMLHttpRequest();
		}
	
    	function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
				if(ch.checked){
					for(var i=0;i<alls.length;i++){
						alls[i].checked=true;	
					}	
				}else{
					for(var i=0;i<alls.length;i++){
						alls[i].checked=false;	
					}	
				}
		}
		/* function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){//取出被选中数据
					ids.push(alls[i].value);
					
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					alert("成功!");
				}
			}else{
				alert("请选中要操作的项");
			}
		} */
		
		
		/* function check(){
		//验证查找信息是否为空
		var bln=false;
		var n=document.getElementById("mDid").value;
		var nn=n.split(".");
		if(isNaN(n) && nn.length != 1){
			alert("您输入的数据类型有误，病历号应该为纯数字");
			bln=false;
			return bln;
		}else{
			bln=true;
			return bln;
		}
	} */
		
		function delAll(){
			//批量处理退号
			//取出多选框，遍历多选框，得到数组，判断是否被选中，选中就储存到另一个数组中，然后将这个新数组交给servlet进行处理
			
				var idArray=document.getElementsByName("check");
				var newArray=new Array();
				var count=0;
				for(var i=0;i<idArray.length;i++){
					if(idArray[i].checked ){
					//被选中
						var d=document.getElementsByName(idArray[i].value)[0];
						if(d.value=="0"){
						newArray[count]=idArray[i].value;
						count++;
						}else{
							alert("选中的选项不合法!");
							return;
						}
					}
				}
				//异步
				if(newArray.length != 0){//有被选中的选项
					if(confirm("确认退出？")){
					
							createHttpXML();
							xhp.open("get", "ReigisterServlet.do?method=all&allId="+newArray);
							xhp.onreadystatechange = function(){
								if(xhp.readyState == 4 && xhp.status == 200){
									var all=xhp.responseText;
									if(all == "true"){
										alert("退号成功");
									}
								}window.location.href="ReigisterServlet.do?method=query";
							};
							xhp.send(null);
					}
				}else{
					alert("请选择需要退号的患者");
				}
			}
			
		
		function putExport(){
			//到处excal
			
				var idArray=document.getElementsByName("check");
				var newArray=new Array();
				var count=0;
				for(var i=0;i<idArray.length;i++){
					if(idArray[i].checked ){
					//被选中
						
						newArray[count]=idArray[i].value;
						count++;
					}
				}
				if(newArray.length != 0){
					if(confirm("确认导出？")){
				//异步
				
				window.location.href="ReigisterServlet.do?method=excal&allId="+newArray;
				/* 	createHttpXML();
					xhp.open("post", "ReigisterServlet.do?method=excal&allId="+newArray,true);
					xhp.onreadystatechange = function(){
						if(xhp.readyState == 4 && xhp.status == 200){
						
							
						}
					};
					xhp.send(null); */
				}
			}else{
					alert("请选择需要导出的选项");
				}
			
		}
		
		function resubmit(){
		var d=document.getElementsByName("mNumber")[0];
		var d2=document.getElementsByName("mname")[0];
		var d3=document.getElementsByName("mKeShi")[0];
		var d4=document.getElementsByName("mTimeMin")[0];
		var d5=document.getElementsByName("mTimeMax")[0];
		var d6=document.getElementsByName("f")[0];
		d.value="";		
		d2.value="";		
		d3.value="";		
		d4.value="";		
		d5.value="";		
		d6.submit();
		
		}
		
		function oneTui(hosrId){
			if(confirm("确认退出？")){
				window.location.href="ReigisterServlet.do?method=delete&mid="+hosrId;
				return true;
			}else{
				return false;
			}
		}
		
		/* function test(minTime){
			//做查询时间的验证
			var maxTime=document.getElementById("timeMax").value;
			var result1=true;
			var d=new Date();
			var year=d.getFullYear();
			var moth=d.getMonth()+1;
			var day=d.getDate();
			var nowTime=year+"-"+moth+"-"+day;
			if(minTime != null && minTime != ""){
				//最小时间不能大于当前时间，并且不能大于最大时间
				if(nowTime < minTime || nowTime <maxTime){//当前时间不能小于搜索时间
					result1=false;
					alert("查询时间不能大于当前时间");
				}else{
					if((minTime != null && minTime != "") && (maxTime != null && maxTime != "")){
					//都不等于空的时候就判断最小时间是否大雨了最大时间
						if(minTime >= maxTime){
							result1=false;
							alert("最小查询时间不能大于最大查询时间");
						}
					}else{
						result1=true;
					}
				}	
			}else{
				result1=true;
			}
			return result1;
		}
		
		function test2(value2){
			//做查询时间的验证
			var result1=true;
			var minTime=document.getElementById("timeMin").value;
			var d=new Date();
			var year=d.getFullYear();
			var moth=d.getMonth()+1;
			var day=d.getDate();
			var nowTime=year+"-"+moth+"-"+day;
			if((minTime != null && minTime != "") || (maxTime != null && maxTime != "")){
				//最小时间不能大于当前时间，并且不能大于最大时间
				if(nowTime < minTime || nowTime <maxTime){//当前时间不能小于搜索时间
					result1=false;
					alert("查询时间不能大于当前时间");
				}else{
					if((minTime != null && minTime != "") && (maxTime != null && maxTime != "")){
					//都不等于空的时候就判断最小时间是否大雨了最大时间
					if(minTime >= maxTime){
						result1=false;
						alert("2最小查询时间不能大于最大查询时间");
						}
					}else{
						result1=true;
					}
				}	
			}else{
				result1=true;
			}
			return result1;
		} */
		
		function check(){
			var result1=true;
			var minTime=document.getElementById("timeMin").value;
			var maxTime=document.getElementById("timeMax").value;
			var d=new Date();
			var year=d.getFullYear();
			var moth=d.getMonth()+1;
			var day=d.getDate();
			var nowTime=year+"-"+moth+"-"+day;
			var number=document.getElementById("mDid").value;
			var n = number.replace(/\s+/g,"");
			document.getElementById("mDid").value=n;
			var nn=n.split(".");
			if(isNaN(n) || nn.length != 1){
				alert("您输入的数据类型有误，病历号应该为纯数字");
				result1=false;
			}else{
				if((minTime != null && minTime != "") || (maxTime != null && maxTime != "")){
				//最小时间不能大于当前时间，并且不能大于最大时间
				if(nowTime < minTime || nowTime < maxTime){//当前时间不能小于搜索时间
					result1=false;
					alert("查询时间不能大于当前时间");
				}else{
					if((minTime != null && minTime != "") && (maxTime != null && maxTime != "")){
					//都不等于空的时候就判断最小时间是否大雨了最大时间
					if(minTime >= maxTime){
						result1=false;
						alert("最小查询时间不能大于最大查询时间");
						}
					}else{
						result1=true;
					}
				}	
			}else{
				result1=true;
			}
		}

			return result1;
		}
		
		function ed(hosrId){
			window.location.href="ReigisterServlet.do?method=edit&mid="+hosrId;
		}
		
		function lokUp(hosrId){
			window.location.href="ReigisterServlet.do?method=look&mid="+hosrId;
		}
		
		
    </script>
</head>
<body>

<form action="ReigisterServlet.do?method=like" method="post" class="definewidth m20" name="f">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" name="mNumber" value="${mNumber }" id="mDid"/></td>
		
        <td width="10%" class="tableleft">主治医生：</td>
        <td><input type="text" name="mname" value="${mname }" id="mDoctor"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td><input type="text" name="mKeShi" value="${mKeShi }" id="mRoom"/></td>
    </tr>
    <tr>
		
        <td width="10%" class="tableleft">挂号时间：</td>
		
		  <td colspan="5">
			<input type="text" name="mTimeMin" value="${mTimeMin }" style="cursor: pointer;" id="timeMin" onclick="WdatePicker()" readonly="readonly"/>&nbsp;至&nbsp;<input type="text" name="mTimeMax" value="${mTimeMax }" id="timeMax"  onclick="WdatePicker()" readonly="readonly" style="cursor: pointer;"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-primary" type="button" onclick="return check()">查询</button> 
            <button onclick="resubmit()" class="btn btn-primary" type="button">清空</button> 
			
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>门诊编号</th>
        <th>患者姓名</th>
        <th>挂号时间</th>
        <th>主治医生</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
	     <c:forEach var="hBean" items="${allHosr }">
	     	<input type="hidden" id="state">
	     	<tr >
         	<td style="vertical-align:middle;">
         		<input type="checkbox" name="check" id="chec" value="${hBean.hosr_id }">
         	</td>
            <td style="vertical-align:middle;">${hBean.hosr_id }</td>
            <td style="vertical-align:middle;">${hBean.hosr_name }</td>
            <td style="vertical-align:middle;">${hBean.hosr_time }</td>
            <td style="vertical-align:middle;">${hBean.d_name }</td>
            <td style="vertical-align:middle;">${hBean.keshi }</td>
            <!--0挂号1已住院2已结算3已退号4已出院5已退院  --> 
            <td style="vertical-align:middle;"><input type="hidden" value="${hBean.hosr_state }" name="${hBean.hosr_id }"/>${hBean.hosr_state==0? "挂号":(hBean.hosr_state==1?"已住院" :(hBean.hosr_state==2?"已结算":(hBean.hosr_state==3?"已退号":(hBean.hosr_state==4?"已出院":"已退院"))))}</td>
            <td style="vertical-align:middle;"><a href="javascript:void(0);" onclick="lokUp(${hBean.hosr_id })">详情>>></a>&nbsp;&nbsp;&nbsp;
            <c:if test="${hBean.hosr_state != 3 && hBean.hosr_state != 4 }"><a href="javascript:void(0);" onclick="ed(${hBean.hosr_id })">更改</a></c:if>
            &nbsp;&nbsp;&nbsp;<c:if test="${hBean.hosr_state==0 }"><a href="javascript:void(0);" onclick="oneTui(${hBean.hosr_id })">退号</a>
            </c:if>
            </td>
        </tr>
	     </c:forEach>
	     
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
           <p:page pageSize="${pageSize}" url="ReigisterServlet.do?method=${method}&mNumber=${mNumber}&mname=${mname}&mKeShi=${mKeShi}&mTimeMin=${mTimeMin }&mTimeMax=${mTimeMax }" totalPage="${totalPage }" count="${dCount }" nowPage="${nowPage }"/>
          
		  </div>
		 <div><button type="button" class="btn btn-success" id="newNav" onclick="insert()">门诊挂号</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onClick="delAll()">退号</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onclick="putExport()">导出Excel</button>
		

		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
