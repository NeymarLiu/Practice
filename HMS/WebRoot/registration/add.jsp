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
    <title>更改挂号--中软高科-2015</title>
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
				window.location.href="ReigisterServlet.do?method=query";
		 });
    });
    
   /*  function createHttpXML(){
			xhr=new XMLHttpRequest();
		}
     */
    function insert(){
			//用异步处理，添加挂号信息，并且弹出信息操作是否成功
			var a=yan();
			//var b=liangdong();
			var c= officeNull();
			var d=isValidityBrithBy18IdCard();
			var e=yanMoney();
			var f=yanPhone();
			var g=yanMedical();
			/* alert("yan:"+a);
			alert("liandong"+b);
			alert("officeIsNull"+c);
			alert("idCard"+d);
			alert("money"+e);
			alert("phone"+f);
			alert("yanMedical"+g); */
			if(a && c && d && e && f && g){
				var name=document.getElementById("name").value;
				var idcard=document.getElementById("idcard").value;
				var medical=document.getElementById("medical").value;
				var money=document.getElementById("money").value;
				var phone=document.getElementById("phone").value;
				var self=document.getElementById("self").value;
				var sex=document.getElementById("sex").value;
				var age=document.getElementById("age").value;
				var work=document.getElementById("work").value;
				var look=document.getElementById("look").value;
				var office=document.getElementById("office").value;
				var d=document.getElementsByName("pdoctor")[0];
				//var doctor2=document.getElementById("doctor");
				//alert(doctor2);
				doctor=d.value;
			//	var dName="";
				/* for(var i=0;i < doctor.length;i++){
					if(doctor[i].checked){
						dName=doctor[i].value;
					}
				}
				alert(dName); */
				var desc=document.getElementById("desc").value;
				var information=name+","+idcard+","+medical+","+money+","+phone+","+self+","+sex+","+age+","+work+","+look+","+office+","+doctor+","+desc+" ,";
			//异步
			var xhr=getXhr();
		 	xhr.open("get", "ReigisterServlet.do?method=insert&info="+information);
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					var result=xhr.responseText;
					if(result == "true"){
						alert("添加用户成功");
					}else{alert("添加失败");}
				}window.location.href="ReigisterServlet.do?method=query";
			};
			xhr.send(null);
			}else{
				alert("数据有误");
			}
		}
    
    function yan(){
    	//验证姓名是否为空
    	var nameNull=false;
    	var name=document.getElementById("name").value;
    	var nspan=document.getElementById("nspan");
    	nspan.innerHTML="<span id='nspan'></span>";
    	if(name == null || name == ""){
    		nameNull=false;
    		nspan.innerHTML="<span id='nspan'>名字不能为空</span>";
    	}else{
    		nameNull=true;
    	}
    	return nameNull;
    }
    
    function liangdong(){
    	//二级联动
    	var lian=false;
    	var doctor=document.getElementsByName("pdoctor")[0];
    	var office=document.getElementById("office");
    	//doctor.innerHTML="<select name='pdoctor' id='doctor'></select>";
    	doctor.innerHTML="";
    	var oSelect=office.value;
    	var xhr=getXhr();
    	xhr.open("get", "ReigisterServlet.do?method=liandong&office="+oSelect,false);
    	xhr.onreadystatechange = function(){
    		if(xhr.readyState == 4 && xhr.status == 200){
    			var resultLian=xhr.responseText;
    			//alert(resultLian == "false");
    			if(resultLian == "false"){
    				var doctorName=new Option("请选择医生",-1);
    				doctor.options.add(doctorName);
    			}else{
    				lian=true;
    				var docName=eval(resultLian);
    				for(var i=0;i<docName.length;i++){
    					var doctorName=new Option(docName[i].d_name,docName[i].d_id);
							doctor.options.add(doctorName);
    				}
    			}
    		}
    	};xhr.send(null);
    	return lian;
    }
    
    function officeNull(){
    	//验证是否选择科室
    	var off=false;
    	var office=document.getElementById("office").value;
    	if(office != null && office != "" && office != "请选择科室"){
    		off=true;
    	}else{
    		off=false;
    	}
    	return off;
    }
    
    function yanIdcard(){
    	//验证身份证号码
    	var ic=false;
    	var number=document.getElementById("idcard").value;//获取身份证号码
    	var card = number.replace(/\s+/g,"");
    	document.getElementById("idcard").value=card;
    	var spanCard=document.getElementById("icSpan");
    	spanCard.innerHTML="<span id='icSpan'></span>";
    	//判断身份证号码的长度是否为18位
    	if(card == null || card == ""){
    		spanCard.innerHTML="<span id='icSpan'>身份证号码不能为空</span>";
    		ic=false;
    	}else if(card.length != 18){
    		spanCard.innerHTML="<span id='icSpan'>身份证号码位数错误</span>";
    		ic=false;
    	}else if(isNaN(card) || card.indexOf('.') > -1){
    		//不为纯数字,判断前17位是否为纯数字，最后一位是否为x
    		 var card17 =  card.substring(0,17); 
    		 var card18= card.substring(17,18);
    		 if(isNaN(card17) || card.indexOf('.') > -1){
    		 	//不为纯数字
    		 	spanCard.innerHTML="<span id='icSpan'>身份证号码输入有误</span>";
    		 	ic=false;
    		 }else{
    		 	//前17位为纯数字
    		 	if(card18 =="X" || card18 == "x"){
    		 	//合法
    		 		ic=true;
    		 	}else{
    		 		spanCard.innerHTML="<span id='icSpan'>身份证号码输入有误</span>";
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
    		xhr.open("get", "ReigisterServlet.do?method=selIdcard&idcard="+card,false);
    		xhr.onreadystatechange = function(){
    			if(xhr.readyState == 4 && xhr.status == 200){
    				//响应成功
    				var resultIdcard=xhr.responseText;
    				if(resultIdcard == "false"){
    					spanCard.innerHTML="<span id='icSpan'>此患者已存在</span>";
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
      
      
    /**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(){
	var yanIc=yanIdcard();
	if(yanIc){
		var sexCheckBox=document.getElementsByName("psex");
		//alert(sexCheckBox);
		var idcard=document.getElementById("idcard").value;
	    var year =  idcard.substring(6,10);   
	    var month = idcard.substring(10,12);   
	    var day = idcard.substring(12,14);
	    var sex=idcard.substring(16,17);
	    //alert(sex);
	    //var newTime=new date();
	    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	    // 这里用getFullYear()获取年份，避免千年虫问题   
	    if(temp_date.getFullYear()!=parseFloat(year)   
	         ||temp_date.getMonth()!=parseFloat(month)-1   
	          ||temp_date.getDate()!=parseFloat(day)){   
	            return false;   
	    }else{//取出身份证上的年龄
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
			/* alert(0);
			alert(newJian); */
			//alert(newJian-temp_date);
			//alert(cardTime);
	    	//document.getElementById("age").value=parseInt(newJian-cardTime); 
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
     
     function yanMoney(){
     	//验证挂号费
     	var yanM=false;
     	var number=document.getElementById("money").value;
     	var money = number.replace(/\s+/g,"");
     	document.getElementById("money").value=money;
     	var moneySpan=document.getElementById("moneySpan");
     	moneySpan.innerHTML="<span id='moneySpan'></span>";
     	if(money == null || money == ""){
     		moneySpan.innerHTML="&nbsp;<span id='moneySpan'>&nbsp;请缴纳挂号费用</span>";
     		yanM=false;
     	}else if(isNaN(money)){
     		//不为纯数字
     		var payMoney=money.split(".");
     		if(payMoney.length == 2){
     			if(isNaN(payMoney[0]) || isNaN(payMoney[1])){
     				yanM=false;
     				moneySpan.innerHTML="&nbsp;<span id='moneySpan'>&nbsp;数据不合法</span>";
     			}else{
     				yanM=true;
     			}
     		}else{
     			moneySpan.innerHTML="&nbsp;<span id='moneySpan'>&nbsp;数据不合法</span>";
     			yanM=false;
     		}
     	}else if(money < 0){
     				yanM=false;
     				moneySpan.innerHTML="&nbsp;<span id='moneySpan'>&nbsp;挂号费不能为负</span>";
     	}else{
     		//为纯数字,合格
     		yanM=true;
     	}
     	return yanM;
     }
      
      function yanPhone(){
      	var yanP=false;
      	var number=document.getElementById("phone").value;
      	var phone = number.replace(/\s+/g,"");
      	document.getElementById("phone").value=phone;
      	var phoneSpan=document.getElementById("spanPhone");
      	phoneSpan.innerHTML="<span id='spanPhone'></span>";
      	if(phone == null || phone == ""){
      		phoneSpan.innerHTML="<span id='spanPhone'>电话号码输入不可为空</span>";
      		yanP=false;
      	}else if(isNaN(phone)){
      		phoneSpan.innerHTML="<span id='spanPhone'>电话号码输入不合法</span>";
      		yanP=false;
      	}else if(phone.length != 11){
      		phoneSpan.innerHTML="<span id='spanPhone'>电话号码输入位数有误</span>";
      		yanP=false;
      	}else{
      		//进一步验证第一位是否为1，第二位是否为3,5,8
      		 var first = phone.substring(0,1);//取出第一位数
      		 var thecond = phone.substring(1,2);
      		 if(first != 1){
      		 	phoneSpan.innerHTML="<span id='spanPhone'>电话号码第一位输入有误，第一位应该为1</span>";
      			yanP=false;
      		 }else{
      		 	if(thecond == 3){
      		 		yanP=true;
      		 	}else if(thecond == 5){
      		 		yanP=true;
      		 	}else if(thecond == 8){
      		 		yanP=true;
      		 	}else{
      		 		phoneSpan.innerHTML="<span id='spanPhone'>电话号码第二位输入有误，第二位只能为3、5、8</span>";
      		 		yanP=false;
      		 	}
      		 }
      	}
      	return yanP;
      }
      
      function yanMedical(){
    	//验证身份证号码
    	var ic=false;
    	var number=document.getElementById("medical").value;//获取身份证号码
    	var card = number.replace(/\s+/g,"");
    	ocument.getElementById("medical").value=card;
    	var spanCard=document.getElementById("spanMedical");
    	spanCard.innerHTML="<span id='spanMedical'></span>";
    	//判断身份证号码的长度是否为18位
    	if(card == null || card == ""){
    		spanCard.innerHTML="<span id='spanMedical'>社保号码不能为空</span>";
    		ic=false;
    	}else if(card.length != 18){
    		spanCard.innerHTML="<span id='spanMedical'>社保号码位数错误</span>";
    		ic=false;
    	}else if(isNaN(card) || card.indexOf('.') > -1){
    		//不为纯数字,判断前17位是否为纯数字，最后一位是否为x
    		 var card17 =  card.substring(0,17); 
    		 var card18= card.substring(17,18);
    		 if(isNaN(card17) || card.indexOf('.') > -1){
    		 	//不为纯数字
    		 	spanCard.innerHTML="<span id='spanMedical'>社保号码输入有误</span>";
    		 	ic=false;
    		 }else{
    		 	//前17位为纯数字
    		 	if(card18 =="X" || card18 == "x"){
    		 	//合法
    		 		ic=true;
    		 	}else{
    		 		spanCard.innerHTML="<span id='spanMedical'>社保号码输入有误</span>";
    		 		ic=false;
    		 	}
    		 }
    	}else{
    		//合法
    		ic=true;
    	}
    	return ic;
    }
     function getDoctor(){
     	var d=document.getElementsByName("pdoctor")[0];
     //	alert(d);
     return d.value;
     	//alert(d.value);
     } 
    </script>
</head>
<body>
<form action="registration/index.jsp" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="pname" id="name" value="" onkeyup="yan()"/><span id="nspan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="pidcard" id="idcard" onkeyup="isValidityBrithBy18IdCard()"/><span id="icSpan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><input type="text" name="pmedical" id="medical" onkeyup="yanMedical()" value=""/><span id="spanMedical"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td><input type="text" name="pmoney" id="money" value="" onkeyup="yanMoney()"/>元<span id='moneySpan'></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" name="pphone" id="phone" value="" onkeyup="yanPhone()"/><span id="spanPhone"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><input type="radio" name="pself" id="self" value="0" checked/>否&nbsp;&nbsp;&nbsp;<input type="radio" name="pself" value="1"/>是</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="psex" id="sex" value="男" readonly="readonly"/>男&nbsp;&nbsp;&nbsp;<input type="radio" id="sex" name="psex" value="女" readonly="readonly"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="page" id="age" value="" readonly="readonly"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td><input type="text" name="pwork" id="work" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><input type="radio" id="look" name="plook" value="0" checked/>初诊&nbsp;&nbsp;&nbsp;<input type="radio" name="pname1" value="1"/>复诊</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td><select name="poffice" id="office" onchange="liangdong()">
        		<option>请选择科室</option>
        	<c:forEach var="of" items="${dList }">
        		<option value="${of.d_keshi }">${of.d_keshi }</option>
        	</c:forEach>
        </select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>
        	<select name="pdoctor">
        		<option>请选择指定医生</option>
        	</select>
        </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="pdesc" id="desc"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button class="btn btn-primary" onclick="insert()" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>