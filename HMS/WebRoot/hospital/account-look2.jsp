<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://testTag/lxc" prefix="p"%>
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
    <title>结算详细--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

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
				window.location.href="hospital/add.jsp";
		 });
    });
	
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
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
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
		}
    $(function () {       
		$('#backid').click(function(){
				window.location.href="WHosrServlet.do?method=initWHosr";
		 });
    });
    
    function daoChu(id){
    var page=document.getElementsByName("page")[0].value;
    window.location.href="WHosrServlet.do?method=daoExcel&id="+id+"&page="+page;
    }
    </script>
</head>
<body>
<input type="hidden" value="${nowPage }" name="page">
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>	    
        <th>病历号</th>
        <th>病人名字</th>
        <th>收费项目</th>
        <th>收费金额</th>
        <th>检查日期</th>
    </tr>
    </thead>
    		<c:forEach var="WHosr" items="${wHosr}">
	     	<tr >
            <td style="vertical-align:middle;">${WHosr.hosr_id}</td>
            <td style="vertical-align:middle;">${WHosr.hosr_name}</td>
            <td style="vertical-align:middle;">${WHosr.cp_name}</td>
            <td style="vertical-align:middle;" >${WHosr.cp_price}</td>
            <td style="vertical-align:middle;">${WHosr.chap_time}</td>
			</tr>
	     </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  	 <p:page pageSize="${pageSize }" url="WHosrServlet.do?method=${method}&id=${id }&name=${name }"  totalPage="${pages }" count="${count }" nowPage="${nowPage }"/>
		  </div>
		 <div>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				<button type="button" class="btn btn-success" name="backid2" onclick="daoChu(${id})">导出excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
  
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">项目花费：</td>
        <td >${M}元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">押金：</td>
        <td>${N}元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">余额：</td>
        <td >
         <c:if test="${K>0 }">${K} 元</c:if>
         <c:if test="${K<=0 }"><font color="red">余额不足，请充值！</font> </c:if>
		</td>
    </tr>
</table>
  
</body>
</html>
