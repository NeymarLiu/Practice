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
    <title>添加医生--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
     <script type="text/javascript" src="Js/ajax.js"></script>
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
    	if(name == null || name == ""){
    		boo1=false;
    		span1.innerHTML="<span id='doctorname'>名字不能为空</span>";
    	}
    	return boo1;
    }
    
    function yan1(){
    	//验证身份证号码
    	var ic=false;
    	var number=document.getElementById("dcard").value;//获取身份证号码
    	var card = number.replace(/\s+/g,"");
    	document.getElementById("dcard").value=card;
    	var spanCard=document.getElementById("doctorcard");
    	spanCard.innerHTML="<span id='doctorcard'></span>";
    	//判断身份证号码的长度是否为18位
    	if(card == null || card == ""){
    		spanCard.innerHTML="<span id='doctorcard'>身份证号码不能为空</span>";
    		ic=false;
    	}else if(card.length != 18){
    		spanCard.innerHTML="<span id='doctorcard'>身份证号码位数错误</span>";
    		ic=false;
    	}else if(isNaN(card) || card.indexOf('.') > -1){
    		//不为纯数字,判断前17位是否为纯数字，最后一位是否为x
    		 var card17 =  card.substring(0,17); 
    		 var card18= card.substring(17,18);
    		 if(isNaN(card17) || card.indexOf('.') > -1){
    		 	//不为纯数字
    		 	spanCard.innerHTML="<span id='doctorcard'>身份证号码输入有误</span>";
    		 	ic=false;
    		 }else{
    		 	//前17位为纯数字
    		 	if(card18 =="X" || card18 == "x"){
    		 	//合法
    		 		ic=true;
    		 	}else{
    		 		spanCard.innerHTML="<span id='doctorcard'>身份证号码输入有误</span>";
    		 		ic=false;
    		 	}
    		 }
    	}else{
    		//合法
    		ic=true;
    	}
    	if(ic){
    		//验证身份证号码与数据库中的是否相同
    		var xhr=getXhr();
    		xhr.open("get", "DoctorServlet.do?method=yan1&idcard="+card,false);
    		xhr.onreadystatechange = function(){
    			if(xhr.readyState == 4 && xhr.status == 200){
    				//响应成功
    				var resultIdcard=xhr.responseText;
    				if(resultIdcard == "false"){
    					spanCard.innerHTML="<span id='doctorcard'>此患者已存在</span>";
    					ic=false;
    				}else{
    					ic=true;
    				}
    			}
    		};
    		xhr.send(null);
    	}
    	return ic;
    }
    
    function isValidityBrithBy18IdCard(){
	var yanIc=yan1();
	if(yanIc){
		var sexCheckBox=document.getElementsByName("psex");
		//alert(sexCheckBox);
		var idcard=document.getElementById("dcard").value;
	    var year =  idcard.substring(6,10); 
	    var month = idcard.substring(10,12);   
	    var day = idcard.substring(12,14);
	    document.getElementById("time").value=year+"-"+month+"-"+day;
	    var sex=idcard.substring(16,17);
	    //var newTime=new date();
	    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	    // 这里用getFullYear()获取年份，避免千年虫问题   
	    if(temp_date.getFullYear()!=parseFloat(year)   
	         ||temp_date.getMonth()!=parseFloat(month)-1   
	          ||temp_date.getDate()!=parseFloat(day)){   
	            return false;   
	    }else{
	    	var time=new Date();
	    	var newJian=time.getFullYear();
			var time=new Date();
	    	var newJian=time.getFullYear();
	    	var newMoth=time.getMonth()+1;
			var cardTime=temp_date.getFullYear();
	    	var newDay=time.getDate();
	    	if(newDay-day < 0){
	    		if(newMoth-month <= 0){
	    			document.getElementById("age").value=parseInt(newJian-cardTime)-1; 
	    		}else{
	    			document.getElementById("age").value=parseInt(newJian-cardTime);
	    			}
	    	}else{
	    		if(newMoth-month < 0){
	    			document.getElementById("age").value=parseInt(newJian-cardTime)-1;
	    		}else{
	    			document.getElementById("age").value=parseInt(newJian-cardTime);
	    		}
	    	}

	    	if(sex == 1){
	    		//男性
	    		//alert(0);
	    		sexCheckBox[1].checked=false;
	    		sexCheckBox[0].checked=true;
	    	}else if(sex % 2 == 0){
	    		//女性
	    		//alert(sex % 2);
	    		sexCheckBox[0].checked=false;
	    		//alert(0);
	    		sexCheckBox[1].checked=true;
	    	}else{
	    		//男性
	    		sexCheckBox[1].checked=false;
	    		sexCheckBox[0].checked=true;
	    	}
	          
	    }
	}
	return yanIc; 
}   
    
    
    function yan2(){
      	var yanP=false;
      	var number=document.getElementById("phone").value;
      	var phone = number.replace(/\s+/g,"");
      	document.getElementById("phone").value=phone;
      	var phoneSpan=document.getElementById("doctortel");
      	phoneSpan.innerHTML="<span id='doctortel'></span>";
      	if(phone == null || phone == ""){
      		phoneSpan.innerHTML="<span id='doctortel'>电话号码输入不可为空</span>";
      		yanP=false;
      	}else if(isNaN(phone)){
      		phoneSpan.innerHTML="<span id='doctortel'>电话号码输入不合法</span>";
      		yanP=false;
      	}else if(phone.length != 11){
      		phoneSpan.innerHTML="<span id='doctortel'>电话号码输入位数有误</span>";
      		yanP=false;
      	}else{
      		//进一步验证第一位是否为1，第二位是否为3,5,8
      		 var first = phone.substring(0,1);//取出第一位数
      		 var thecond = phone.substring(1,2);
      		 if(first != 1){
      		 	phoneSpan.innerHTML="<span id='doctortel'>电话号码第一位输入有误</span>";
      			yanP=false;
      		 }else{
      		 	if(thecond == 3){
      		 		yanP=true;
      		 	}else if(thecond == 5){
      		 		yanP=true;
      		 	}else if(thecond == 8){
      		 		yanP=true;
      		 	}else{
      		 		phoneSpan.innerHTML="<span id='doctortel'>电话号码第二位输入有误</span>";
      		 		yanP=false;
      		 	}
      		 }
      	}
      	return yanP;
      }
    
    function yan3(){
    	//验证座机号码是否合格
    	var boo4=true;
    	var number=document.getElementById("dp").value;
    	var zuoji = number.replace(/\s+/g,"");
    	document.getElementById("dp").value=zuoji;
    	var span4=document.getElementById("doctorphone");
    	span4.innerHTML="<span id='doctorphone'></span>";
    	var isPhone = /([0-9]{2,3}-)-?[0-9]{8,9}$/;
    	if(zuoji == null || zuoji == ""){
    		boo4=false;
    		span4.innerHTML="<span id='doctorphone'>座机号不能为空</span>";
    	}else if(!isPhone.test(zuoji)){
    		boo4=false;
    		span4.innerHTML="<span id='doctorphone'>座机号输入格式有误</span>";
    	}else{
    		boo4=true;
    	}
    	return boo4;
    }
    
   /*  function yan4(){
    	var boo5=true;
    	var email=document.getElementById("demile").value;
    	var span5=document.getElementById("doctoremail");
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
    	return boo5;
    } */
    
    function yan4(){
    	//验证邮箱
    	var boo5=false;
    	var emailId=document.getElementById("demile").value;
    	var emailSpan=document.getElementById("doctoremail");
    	emailSpan.innerHTML="<span id='doctoremail'></span>";
    	//var QQEmail="[1-9]\\d{4,9}@qq.com";
    	var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    	//var sinaMail="[a-zA-Z]\\w{10}@sina.com";
    	if(re.test(emailId)){
    		boo5=true;
    	}else{
    		emailSpan.innerHTML="<span id='doctoremail'>邮箱格式错误</span>";
    		boo5=false;
    	}
    	/* if(QQEmail.test(emailId).val()){
    		//为QQ邮箱
    	alert("email");
    		boo5=true;
    	}else if(sinaMail.test(emailId).val()){
    		//为新浪邮箱
    		boo5=true;
    	}else{
    		//格式有误
    		boo5=false;
    		emailSpan.innerHTML="<span id='doctoremail'>不为qq邮箱或者新浪邮箱</span>";
    	} */
    	return boo5;
    }

    function baocun(){
    	var a= yan();
    	var b=yan1();
    	var c=yan2();
    	var d=yan3();
    	var e=yan4();
    	/* alert(a);
    	alert(b);
    	alert(c);
    	alert(d);
    	alert(e); */
    	var f=false;
    	if(a && b && c && d  && e){
    		f=true;
    	}else{
    		f=false;
    	}
    	return f;
    }
    
    </script>
</head>
<body>
<form action="DoctorServlet.do?method=addInfor" method="post" class="definewidth m20" onsubmit="return baocun()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" id="nam" name="pname" value="" onkeyup="yan()"/><span id="doctorname"></span></td>
    </tr>
    
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input id="dcard" type="text" name="pcard" value=""  onkeyup="isValidityBrithBy18IdCard()"/><span id="doctorcard"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input id="phone" type="text" name="pphone" value="" onkeyup="yan2()"/><span id="doctortel"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td><input id="dp" type="text" name="ptel" value="" onkeyup="yan3()"/><span id="doctorphone"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="psex" readonly="readonly" value="男"/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="psex" value="女" readonly="readonly"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" name="pyear" id="time" readonly="readonly" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="page" id="age" readonly="readonly"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input id="demile" type="text" name="pemail" value="" onkeyup="yan4()"/><span id="doctoremail"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>
        	<select name="keshi">
        		<c:forEach var="keshi" items="${groupkeshi }">
        			<option value="${keshi.d_keshi }">${keshi.d_keshi }</option>
        		</c:forEach>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td><select name="xueli"><option>专科</option><option>本科</option><option>博士</option><option>博士后</option></select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="desc"></textarea></td>
	</tr>
	<tr>
        <td width="10%" class="tableleft">入职时间</td>
        <td><input type="text" name="time"/></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button class="btn btn-primary" type="submit" >保存</button> &nbsp;&nbsp;<a href="DoctorServlet.do?method=query" class="btn btn-success">返回列表</a>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>