/**
 * 菜单编辑页面
 */
 
  
  $(function () {       
	$('#backid').click(function(){
		var page=document.getElementsByName("page")[0].value;
		var type=document.getElementsByName("type")[0].value;
		var params=document.getElementsByName("params")[0].value;
		window.location.href="MenuServlet.do?method="+type+"&page="+page
								+"&params="+params;
		});
 });
 
 /**
  * 更新菜单资源
  * */
 function updateResource(obj){
	 var st1=jugeUnique(obj);
	 var st2=getURL();
	 if(st2==true&&st1==true){
		var mName=document.getElementsByName("realname")[0].value;
		var mUrl=document.getElementsByName("url")[0].value;
		var	d2=document.getElementsByName("status");
		for(var di=0;di<d2.length;di++){
			if(d2[di].checked==true){
				state=d2[di].value;
			}
		}
		 var xmlhttp = getXhr();
		xmlhttp.open("get","MenuServlet.do?method=updateMenu&mName="+mName+"&mId="+obj+"&mUrl="+mUrl+"&mState="+state+"&time="+new Date().getTime());
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState ==4 &&xmlhttp.status==200){
						var flag=xmlhttp.responseText;
						var sta=parseInt(flag);
						if(sta==1){
							alert("编辑成功!");
						}else if(sta==2){
							alert("编辑失败!");
						}
					} 
			};
			xmlhttp.send(null);
	 }else{
		 alert("您提交了不合法的数据!请仔细检查。");
	 }
	}
 
 /**
  * 检查url的有效性
  * */
 function getURL() {
	 var st=true;
	 var path=document.getElementById("path").value;
	 var d=document.getElementsByName("url")[0].value;
	 var sp=document.getElementById("sp2");
	 if(d==""){
		sp.innerHTML="<font color='red'>您没有输入!</font>"; 
		st=false;
	 }else{
	 var url=path+d;
     var xmlhttp = getXhr();
     xmlhttp.open("get",url+"&time="+new Date().getTime(),false);
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 ){
						if(xmlhttp.status!=200){
							sp.innerHTML="<font color='red'>URL不可用!</font>";
							 st=false;
						}else{
							sp.innerHTML="<font color='green'>URL可用!</font>";
							 st=true;
						}
					} 
			};
			xmlhttp.send(null);
	 
	 }
	return st;
	}
 
 /**
  * 判断菜单名称唯一性
  * */
	function jugeUnique(obj){
			var nameState=true;
			var xmlhttp=getXhr();
			var mname=document.getElementsByName("realname")[0].value;
			xmlhttp.open("get","MenuServlet.do?method=judgeUnique&mid="+obj+"&mname="+mname+"&time="+new Date().getTime(),false);
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var count=parseInt(flag);
						var d=document.getElementById("sp");
						if(count==0){
							d.innerHTML="<font color='green'>菜单名称可用!</font>";
							nameState=true;
						}else if(count>0){	
							d.innerHTML="<font color='red'>菜单名称已存在!</font>";
							nameState=false;
						}else if(count<0){
							d.innerHTML="<font color='red'>您没有输入!</font>";
							nameState=false;
						}
					}
			};
			xmlhttp.send(null);
		return nameState;	
	}