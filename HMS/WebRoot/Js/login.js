/**
 *登陆
 */
 function login(){
	
		window.location.href="LoginServlet.do?method=test";
	}
function director(obj){
	if("1"==obj){
	window.parent.location.href="/HMS/login.jsp";
	}
}
function  newCode(){
    	 var  today = new  Date();
    	 document.getElementById("imgNumber").src="ValidateNumberServlet.do?tm="+today.getTime(); 	 
  }
function checkCode(){
	var validate=document.getElementsByName("verify")[0].value;
	var state=false;
	var xmlhttp=getXhr();
	xmlhttp.open("get","LoginServlet.do?method=validate&time="+new Date().getTime()+"&inCode="+validate,false);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	xmlhttp.onreadystatechange = function (){
		if(xmlhttp.readyState == 4 && xmlhttp.status==200){
			var validateCode=xmlhttp.responseText;
			var sp=document.getElementById("validate");
			if(validateCode=="true"){
				sp.innerHTML="<font color='green' size='+2'> √ </font>";
				state=true;
			}
			if(validateCode=="false"){
				sp.innerHTML="<font color='color' size='+2'> × </font>";
				state=false;
			}
			}
	};
	xmlhttp.send(null);
	return state;
}
function checkName(){
	var state=false;
	var name=document.getElementsByName("username")[0].value;
	var sp=document.getElementById("spna");
	if(""==name){
		sp.innerHTML="<font color='color' size='-1'>请输入用户名!</font>";
		state=false;
	}else{
		sp.innerHTML="";
		state=true;
	}
	return state;
}
function checkPs(){
	var state=false;
	var ps=document.getElementsByName("password")[0].value;
	var sp=document.getElementById("spps");
	if(ps==""){
		sp.innerHTML="<font color='color' size='-1'>请输入密码!</font>";
		state=false;
	}else{
		sp.innerHTML="";
		state=true;
	}
	return state;
}
function check (){
	var s1=checkPs();
	var s2=checkName();
	var s3=checkCode();
	if(s1&&s2&s3){
		return true;
	}else{
		var sp=document.getElementById("spna");
		newCode();
		return false;
	}
}