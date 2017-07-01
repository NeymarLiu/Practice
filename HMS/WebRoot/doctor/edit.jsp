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
    <title>修改医生--中软高科-2015</title>
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
				window.location.href="doctor/index.jsp";
		 });
    });
    
    var xhr;
    function createxmlhttp(){
    	xhr=new XMLHttpRequest();
    }
    function yan(){
    	//验证姓名信息是否非空
    	//取出姓名，做异步判断
    	var boo1=true;
    	var name=document.getElementById("nam").value;
    	var span1=document.getElementById("doctorname");
  		span1.innerHTML="<span id='doctorname'></span>";
    	if(name == null && name == ""){
    		boo1=false;
    		span1.innerHTML="<span id='doctorname'>名字为不能为空</span>";
    	}
    	return boo1;
    }
    
    function yan1(){
    	//验证身份证信息是否合法
    	var boo2=true;
    	var card=document.getElementById("dcard").value;
    	var span2=document.getElementById("doctorcard");
  		span2.innerHTML="<span id='doctorcard'></span>";
    	if(card == null || card == ""){
    		boo2=false;
    		span2.innerHTML="<span id='doctorcard'>身份证号码不能为空</span>";
    	}
    	else if(card.length != 18){//长度是否为18
    		boo2=false;
    		span2.innerHTML="<span id='doctorcard'>号码长度有误</span>";
    	}else{
    		//异步
    		createxmlhttp();
    		
    		xhr.open("post", "DoctorServlet.do?method=yan1&pcard="+card,true);
    		xhr.onreadystatechange = function(){
    			if(xhr.readyState == 4 && xhr.status == 200){
    				var result1=xhr.responseText;
    				if(result1 == "false"){
    					boo2=false;
    					span2.innerHTML="<span id='doctorcard'>号码格式有误</span>";
    				}
    			}
    		}
    		xhr.send(null);
    	}
    	return boo2;
    }
    
    function yan2(){
    
    	var boo3=true;
    	var phone=document.getElementById("phone").value;
    	var span3=document.getElementById("doctortel");
  		span3.innerHTML="<span id='doctortel'></span>";
    	if(phone == null || phone == ""){
    		boo3=false;
    		span3.innerHTML="<span id='doctortel'>手机号码不能为空</span>";
    	}
    	else if(phone.length != 11){//长度是否为11
    		boo3=false;
    		span3.innerHTML="<span id='doctortel'>手机号码长度有误</span>";
    	}else{
    		//异步
    		createxmlhttp();
    		xhr.open("get", "DoctorServlet.do?method=yan2&phone="+phone);
    		xhr.onreadystatechange = function(){
    			if(xhr.readyState == 4 && xhr.status == 200){
    				var result1=xhr.responseText;
    				if(result1 == "false"){
    					boo3=false;
    					span3.innerHTML="<span id='doctortel'>号码格式有误</span>";
    				}
    			}
    		}
    		xhr.send(null);
    	}
    	return boo3;
    }
    
    function yan3(){
    	//验证座机号码是否合格
    	var boo4=true;
    	var zuoji=document.getElementById("dp").value;
    	var span4=document.getElementById("doctorphone");
    	span4.innerHTML="<span id='doctorphone'></span>";
    	if(zuoji == null || zuoji == ""){
    		boo4=false;
    		span4.innerHTML="<span id='doctorphone'>座机号不能为空</span>";
    	}else if(zuoji.length < 11 || zuoji.length > 13){
    		//座机号的位数不对
    		boo4=false;
    		span4.innerHTML="<span id='doctorphone'>座机号位数错误</span>";
    	}else{
    		//做异步处理，进一步判断座机号的格式是否正确
       		createxmlhttp();
    		xhr.open("get", "DoctorServlet.do?method=yan3&zphone="+zuoji);
			xhr.onreadystatechange = function(){
    			if(xhr.readyState == 4 && xhr.status == 200){
    				var result1=xhr.responseText;
    				if(result1 == "false"){
    					boo4=false;
    					span4.innerHTML="<span id='doctorphone'>号码格式有误</span>";
    				}
    			}
    		}
    		xhr.send(null);
    	}
    	return boo4;
    }
    
    function yan4(){
    	var boo5=true;
    	var email=document.getElementById("demile").value;
    	var span5=document.getElementById("doctoremail");
    	span5.innerHTML="<span id='doctoremail'></span>";
    	//做异步处理
    	if(email == null || email == ""){
    		boo5=false;
    	}else{
    		createxmlhttp();
    		xhr.open("get", "DoctorServlet.do?method=yan4&email="+email);
    		xhr.onreadystatechange = function(){
    		if(xhr.readyState == 4 && xhr.status == 200){
    				var result1=xhr.responseText;
    				if(result1 == "false"){
    					boo5=false;
    					span5.innerHTML="<span id='doctoremail'>号码格式有误</span>";
    				}
    			}
    		}
    	xhr.send(null);
    	}
    }
    
    function baocun(){
    	var a= yan();
    	var b=yan1();
    	var c=yan2();
    	var d=yan3();
    	var e=yan4();
    	var f=false;
    	if(a && b && c && d && e && f){
    		f=true;
    	}
    	return f;
    }
    
    </script>
</head>
<body>
<form action="DoctorServlet.do" method="post" class="definewidth m20">

	<input type="hidden" name="method" value="dup"/>
	<input type="hidden" name="did" value="${doctor.d_id }" />
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" id="nam" readonly="readonly" name="pname" value="${doctor.d_name }" readonly="readonly" onblur="yan()"/><span id="doctorname"></span></td>
    </tr>
    
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="pcard" readonly="readonly" id="dcard" value="${doctor.d_idcar }" onblur="yan1()"/><span id="doctorcard"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input type="text" name="pphone" id="phone" value="${doctor.d_telphone }" onblur="yan2()"/><span id="doctortel"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td><input type="text" id="dp" name="ptel" value="${doctor.d_phone }" onblur="yan3()"/><span id="doctorphone"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
        	<c:if test="${doctor.d_sex == '男' }">
        		<input type="radio" name="psex" readonly="readonly" value="男" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="psex" readonly="readonly" value="女"/>女
        	</c:if>
        	<c:if test="${doctor.d_sex == '女' }">
        		<input type="radio" name="psex" readonly="readonly" value="男"/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="psex" value="女" readonly="readonly" checked/>女
        	</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" readonly="readonly" name="ptime" value="${doctor.d_intime }"/><span id="doctoryear"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" readonly="readonly" name="page" value="${doctor.d_age }"/><span id="doctorage"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" id="demile" name="pemail" value="${doctor.d_email }" onblur="yan4()"/><span id="doctoremail"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>
        	<select name="pkeshi">
        		<c:forEach var="allDoctorBean" items="${groupkeshi }">
        			<c:if test="${allDoctorBean.sta == 1}">
        				<option value="${allDoctorBean.d_keshi }" selected="selected">${allDoctorBean.d_keshi }</option>
        			</c:if>
        			<c:if test="${allDoctorBean.sta != 1}">
        				<option value="${allDoctorBean.d_keshi }">${allDoctorBean.d_keshi }</option>
        			</c:if>
        		</c:forEach>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td><select name="pxueli">
        		<option>本科</option>
        		<option>专科</option>
        		<option>硕士</option>
        		<option>博士</option>
        		<option>博士后</option>
        </select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="pdesc">${doctor.d_desc }</textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<input type="submit" value="保存" class="btn btn-primary"/> &nbsp;&nbsp;<a href="DoctorServlet.do?method=query" class="btn btn-success">返回列表</a>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>