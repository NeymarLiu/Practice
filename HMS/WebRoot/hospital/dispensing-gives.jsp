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
    <title>发药--中软高科-2015</title>
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
				window.location.href="DrugPeopleServlet.do?method=getAllDrugPeople";
		 });
    });
		function  getXhr(){
			var xhr;
			try{
				xhr = new XMLHttpRequest();
			}catch(e){
				try{
					xhr=new ActiveXObject("Microsoft.XMLHTTP"); 
				}catch(e){
					try{
						xhr= new new ActiveXObject("Msxml2.XMLHTTP");
					}catch(e){
						alert("你的浏览器不支持ajax，请跟换浏览器，建议换火狐");
					}
				}
			}
			return xhr;
		}
		var neednumber;
		function changedrug(obj){
			var dr_id = obj;
			var beh_id = document.f.hid.value;//得到所有批发的病人编号
			var xhr = getXhr();
			var sp = document.getElementById("spDG");
			xhr.open("get","DrugPeopleServlet.do?method=givesDrugNumber&beh_id="+beh_id+"&dr_id="+dr_id+"&time="+new Date().getTime(),true);
			xhr.onreadystatechange = function (){
				if(xhr.readyState == 4 && xhr.status == 200){
					neednumber = eval(xhr.responseText);
					sp.innerHTML="<span style='color:green'>该药品最多只能批量发药为"+neednumber+"</span>";
				}
			}
			xhr.send(null);
		}
		function givesDrug(){
			var a = false;
			var number = document.f.number.value;
			var sp = document.getElementById("spN");
			if(number != null && number !=""){//是否为空
				if(!isNaN(number)){//是否为数字
					if(number>0){//大于0
						if(parseInt(number) == number){
							if(number<=neednumber){
								sp.innerHTML="<span style='color:green'>√</span>";
								a = true;
							}else{
								sp.innerHTML="<span style='color:red'>该药品最多只能批量发药为"+neednumber+"</span>";
							}
						}else{
							sp.innerHTML="<span style='color:red'>不能为小数</span>";
						}
					}else{
						sp.innerHTML="<span style='color:red'>不能为负数和0</span>";
					}
				}else{//不为数字
					sp.innerHTML="<span style='color:red'>必须为数字</span>";
				}
			}else{//为空
				sp.innerHTML="<span style='color:red'>不能为空</span>";
			}
			return a;
		}
		function tijiao(){
			var tj = false;
			var a = givesDrug();
			if(a){
				var one = window.confirm("确定该操作");
				if(one){
					tj = true;
				}
			}
			return tj;
		}
    </script>
</head>
<body>
<form action="DrugPeopleServlet.do?method=givesDrug" method="post" class="definewidth m20" name="f" onsubmit="return tijiao()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td><input type="hidden" name="hid" value="${id}">${id}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="hidden" name="hname" value="${name }">${name }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td>
        <select name="drname" onchange="changedrug(this.value)">
        <option value="0">请选择要发的药品...</option>
        <c:forEach items="${names }" var="name">
        			<option value="${name.dr_id }">${name.dr_id }-${name.dr_name }</option>
        </c:forEach>
       </select>
       <span id="spDG"></span>
       </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">发药数量</td>
        <td><input type="text" name="number" value="" onblur="givesDrug()"/><span id="spN"></span></td>
        <input type="hidden" name="fyjg" value="${listSS }"/>
        <script type="text/javascript">
   				var s = document.f.fyjg.value;
   				var sp = document.getElementById("spN");
   				if(s != null){
   					if(s ==1){
   						sp.innerHTML="<span style='color:red'>库存不足</span>";
   					}else{
   					   	sp.innerHTML="<span style='color:red'>"+s+"</span>";
   					}
   				}
   			</script>
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