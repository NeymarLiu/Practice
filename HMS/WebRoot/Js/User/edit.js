/**
 * 编辑用户信息
 */
  $(function () {       
		$('#backd').click(function(){
			var page=document.getElementsByName("page")[0].value;
			var type=document.getElementsByName("type")[0].value;
			var params=document.getElementsByName("params")[0].value;
			window.location.href="UserServlet.do?method="+type+"&page="+page+"&params="+params;
		 });
    });
/**
  * 验证登录名唯一
  *//*
 function CheckLoginName(uid){
	    var nameState=false;
		var xmlhttp=getXhr();
		var str=document.getElementsByName("loginName")[0].value;
		xmlhttp.open("post","UserServlet.do?method=judgeUnique&uname="+str+"&uid="+uid+"&time="+new Date().getTime(),false);
		xmlhttp.onreadystatechange = function (){
			if(xmlhttp.readyState == 4 && xmlhttp.status==200){
				var flag=xmlhttp.responseText;
				var count=parseInt(flag);
					var d=document.getElementById("splo");
					if(count==0){
						d.innerHTML="<font color='green' size='+2'>√</font>";
						nameState=true;
					}else if(count>0){	
						d.innerHTML="<font color='red'>角色名称已存在!</font>";
						nameState=false;
					}else if(count<0){
						d.innerHTML="<font color='red'>您没有输入!</font>";
						nameState=false;
					}
				}
		};
		xmlhttp.send(null);
		return nameState;	
 }*/
function updateUser(uid){
	 //var loginName=document.getElementsByName("loginName")[0].value;
	 var pass=document.getElementsByName("pass")[0].value;
	 var phone=document.getElementsByName("phone")[0].value;
	 var email=document.getElementsByName("email")[0].value;
	 var address=document.getElementsByName("address")[0].value;
	 var status=document.getElementsByName("status");
	 for(var i=0;i<status.length;i++){
			if(status[i].checked==true){
				var state=status[i].value;
			}
		}
	 var role=document.getElementsByName("role")[0].value;
	 if(CheckPas(pass)==true&&CheckPhone(phone)==true&&CheckEmail(email)
			 ==true&&CheckAddress(address)==true){
		 var xmlhttp=getXhr();
			xmlhttp.open("get","UserServlet.do?method=updateUser&pass="+pass
					 +"&phone="+phone+"&email="+email+"&address="+address+"&state="+state
					 +"&uid="+uid+"&role="+role+"&time="+new Date().getTime());
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
						if(1==flag){
							alert("提示:更新失败!");
						}else if(0==flag){	
							alert("提示:更新成功!");
						}
				}
			};
			xmlhttp.send(null);
	 }else{
		 alert("请根据提示更正数据!");
	 }
}