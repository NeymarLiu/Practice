
  $(function () {       
		$('#backid').click(function(){
				window.location.href="UserServlet.do?method=initUser";
		 });
    });


$(function(){ 
	$('#pass').keyup(function () { 
		var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
		var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
		var enoughRegex = new RegExp("(?=.{6,}).*", "g"); 
		var sp=document.getElementById("spps");
	    if($(this).val().length>5&&$(this).val().length<17){
	    	sp.innerHTML="<font color='green' size='+2'>√</font>";
	    }else{
	    	sp.innerHTML="<font color='red'>密码长度必须在6-16之间!</font>";
	    }
		if (false == enoughRegex.test($(this).val())) { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-defule'); 
			 //密码小于六位的时候，密码强度图片都为灰色 
		} 
		else if (strongRegex.test($(this).val())) { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-strong'); 
			 //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强 
		} 
		else if (mediumRegex.test($(this).val())) { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-medium'); 
			 //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 
		} 
		else { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass('pw-weak'); 
			 //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的 
		} 
		return true; 
	}); 
}) 


/**
 * 密码框状态改变
 * */
function ShowPs(){
		var ps=document.getElementById("pass");
		var img=document.getElementById("im");
		if(ps.type=="password"){
			img.src="Images/text.jpg";
			ps.type="text";
		}else if(ps.type=="text"){
			img.src="Images/ps.jpg";
			ps.type="password";
		}
	}
function CheckId(id) {
	var b=false;
	var state=IdCardValidate(id);
	var sp=document.getElementById("spid");
	if(state==false){
		sp.innerHTML="<font color='red'>身份证不可用!</font>";
		b=false;
	}else if(state==true){
		var xmlhttp=getXhr();
		xmlhttp.open("get","UserServlet.do?method=getIdCardCount&idCard="+id+"&time="+new Date().getTime(),false);
		xmlhttp.onreadystatechange = function (){
			if(xmlhttp.readyState == 4 && xmlhttp.status==200){
				var flag=xmlhttp.responseText;
				var count=parseInt(flag);
					var d=document.getElementById("splo");
					if(count==0){
						sp.innerHTML="<font color='green' size='+2'>√</font>";
						var sex=document.getElementsByName("sex");
						var sexType=maleOrFemalByIdCard(id);
						if(sexType==0){
							sex[0].checked=true;
						}else if(sexType==1){
							sex[1].checked=true;
						}
						b=true;
					}else if(count>0){	
						sp.innerHTML="<font color='red'>身份证号码已存在!</font>";
						b=false;
					}else if(count<0){
						sp.innerHTML="<font color='red'>您没有输入!</font>";
						b=false;
					}
				}
		};
		xmlhttp.send(null);
	}
	return b;
}
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 
    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }   
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}


function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));        // 对身份证号码做处理。包括字符间有空格。   
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 1;   
        }else{   
            return 0;   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 1;   
        }else{   
            return 0;   
        }   
    }else{   
        return -1;   
    }   
}   
/**
 * 密码长度
 * 
 */
 function CheckPas(str){
	 var b=false;
    var sp=document.getElementById("spps");
    if(str.length>5&&str.length<17){
    	sp.innerHTML="<font color='green' size='+2'>√</font>";
    	b=true;
    }else{
    	sp.innerHTML="<font color='red'>密码长度必须在6-16之间!</font>";
    	 b=false;
    }
    return b;
}
/**
 * 邮箱验证
 * 
 */
 function CheckEmail(str){
	 var b=false;
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    var sp=document.getElementById("spem");
    if(re.test(str)){
    	sp.innerHTML="<font color='green' size='+2'>√</font>";
    	b=true;
    }else{
    	sp.innerHTML="<font color='red'>请正确填写邮箱!</font>";
    	 b=false;
    }
    return b;
}
 
 /**
  * 手机号码
  * 
  */
  function CheckPhone(str){
 	 var b=false;
     var re = /^1[3|4|5|7|8][0-9]{9}$/;
     var sp=document.getElementById("spph");
     if(re.test(str)){
     	sp.innerHTML="<font color='green' size='+2'>√</font>";
     	b=true;
     }else{
     	sp.innerHTML="<font color='red'>手机号码无效!</font>";
     	 b=false;
     }
     return b;
 }
  /**
   * 验证姓名
   * */
 function CheckName(str){
	var b=true;
	var sp=document.getElementById("spna");
	 if(str==""){
		 sp.innerHTML="<font color='red'>姓名不能为空!</font>";
		 b=false;
	 }else{
		 sp.innerHTML="<font color='green' size='+2'>√</font>";
		 b=true;
	 }
	 return b;
	 
 }
 /**
  * 验证住址
  * */
function CheckAddress(str){
	var b=true;
	var sp=document.getElementById("spad");
	 if(str==""){
		 sp.innerHTML="<font color='red'>姓名不能为空!</font>";
		 b=false;
	 }else{
		 sp.innerHTML="<font color='green' size='+2'>√</font>";
		 b=true;
	 }
	 return b;
	 
}
/**
 * 验证角色
 * */
function CheckRole(){
	var b=true;
	var sp=document.getElementById("spro");
	var role=document.getElementsByName("role")[0].value
	 if(role==""){
		 sp.innerHTML="<font color='red'>请选择角色!</font>";
		 b=false;
	 }else{
		 sp.innerHTML="<font color='green' size='+2'>&nbsp;√</font>";
		 b=true;
	 }
	 return b;
	 
}
 /**
  * 验证登录名唯一
  */
 function CheckLogin(str){
	 var nameState=false;
	 var d=document.getElementById("splo");
	 if(str==""){
			d.innerHTML="<font color='red'>您没有输入!</font>"; 
			nameState=false;
		 }else{
		var xmlhttp=getXhr();
		xmlhttp.open("get","UserServlet.do?method=judgeUnique&uname="+str+"&time="+new Date().getTime(),false);
		xmlhttp.onreadystatechange = function (){
			if(xmlhttp.readyState == 4 && xmlhttp.status==200){
				var flag=xmlhttp.responseText;
				var count=parseInt(flag);
					
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
		 }
		return nameState;
 }
 
 function InsertUser(){
	 var loginName=document.getElementsByName("loginName")[0].value;
	 var pass=document.getElementsByName("pass")[0].value;
	 var trueName=document.getElementsByName("trueName")[0].value;
	 var idCard=document.getElementsByName("idcard")[0].value;
	 var sexs=document.getElementsByName("sex");
	 for(var i=0;i<sexs.length;i++){
			if(sexs[i].checked==true){
				var sex=sexs[i].value;
			}
		}
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
	 if(CheckLogin(loginName)==true&&CheckName(trueName)==true&&CheckPas(pass)
			 ==true&&CheckId(idCard)==true&&CheckPhone(phone)==true&&CheckEmail(email)
			 ==true&&CheckAddress(address)==true&&CheckRole()==true){
		/* window.location.href="UserServlet.do?method=insertUser&loginName="+loginName+"&pass="+pass
		 +"&trueName="+trueName+"&idCard="+idCard+"&sex="+sex+"&phone="+phone+"&email="+email
		 +"&address="+address+"&state="+state+"&role="role;*/
		 var xmlhttp=getXhr();
			xmlhttp.open("get","UserServlet.do?method=insertUser&loginName="+loginName+"&pass="+pass
					 +"&trueName="+trueName+"&idCard="+idCard+"&sex="+sex+"&phone="+phone+"&email="+email
					 +"&address="+address+"&state="+state+"&role="+role+"&time="+new Date().getTime());
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
						if("1"==flag){
							alert("添加失败!")
						}else {	
							alert("添加成功!");
							window.location.href="User/addUser.jsp";
						}
				}
			};
			xmlhttp.send(null);
		 
		
		 
	 }else{
		 
		 alert("请根据提示更正数据!");
	 }
 }