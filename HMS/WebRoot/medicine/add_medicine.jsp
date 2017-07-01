<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
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
    <title>添加药品--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="Js/Drug/addMedicine.js"></script>
    <script type="text/javascript" src="Js/ajax.js"></script>
 

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
		$('#backid').click(function(){
				window.location.href="DrugServlet.do?method=getAllDrug";
		 });
    });
    
    </script>
    <script type="text/javascript">
	    function number2(){
	    		var a = false;
	    		var number = document.getElementsByName("number")[0].value;
	    		var sp = document.getElementById("spN");
	    		var str=number.replace(/\s+/g,"");
	    		if(number != null && number != ""){
	    			if(str==number){
					if(isNaN(number)){//true
						sp.innerHTML="<span style='color:red'>数量只能是数字</span>";
					}else{
						if(parseInt(number)>=0){
						if(parseInt(number) == number){
						if(parseInt(number) <= 10000){
							sp.innerHTML="<span style='color:green'>ok</span>";
							a = true;
							}else{
							sp.innerHTML="<span style='color:red'>数量超过最大限度</span>";
							}
						}else{
							sp.innerHTML="<span style='color:red'>数量不能是小数</span>";
						}
						
						}else{
							sp.innerHTML="<span style='color:red'>数量不能是负数</span>";
							}
						}
					}else{
						sp.innerHTML="<span style='color:red'>数量格式不规范，请重新输入</span>";
						}
				}else{
					sp.innerHTML="<span style='color:red'>数量不能为空</span>";
				}
			return a;
		    }
	function tijiao(){
		var tj=false;
		var a=number2();
		if(a==true){
			tj=true;
		}else{
			tj=false;
		}
	return tj;
	}
    </script>
</head>
<body>
<form action="DrugServlet.do?method=changeDrug&id=${db.dr_id}" method="post" class="definewidth m20" onsubmit="return tijiao()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td>${db.dr_id}<input type="hidden" name="id" value="${db.dr_id}"></td>
    </tr> 
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td >${db.dr_name}</td>
    </tr>    
	<tr>
        <td width="10%" class="tableleft">余量</td>
        <td>${db.dr_number}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">数量</td>
        <td>
			<input type="text" value="" name="number" onblur="number2()"/><span id="spN"></span>
		</td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>