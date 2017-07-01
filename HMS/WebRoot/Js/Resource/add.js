/**
 * 添加菜单页面
 */
function backIndex(){
			window.location.href="MenuServlet.do?method=getAllMenus";
		}
 /**
  * 添加:判断菜单名称唯一性
  * */
	function jugeUnique(){
			var nameState=true;
			var xmlhttp=getXhr();
			var mname=document.getElementsByName("realname")[0].value;
			var d=document.getElementById("sp");
			if(mname==""){
				d.innerHTML="<font color='red'>您没有输入!</font>";
				nameState=false;
			}else{
			xmlhttp.open("get","MenuServlet.do?method=judgeUnique&mname="+mname+"&time="+new Date().getTime(),false);
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var count=parseInt(flag);
						if(count==0){
							d.innerHTML="<font color='green' size='+2'>√</font>";
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
			}
		return nameState;	
	} 
	
	function insertMenu(){
		var st1=getURL();
		var st2=jugeUnique();
		if(st2==true&&st1==true){
			var mname=document.getElementsByName("realname")[0].value;
			var murl=document.getElementsByName("url")[0].value;
			var	d2=document.getElementsByName("status");
			for(var di=0;di<d2.length;di++){
				if(d2[di].checked==true){
					state=d2[di].value;
				}
			}
			 var xmlhttp = getXhr();
			xmlhttp.open("get","MenuServlet.do?method=insertMenu&mname="+mname+"&murl="+murl+"&mstate="+state+"&time="+new Date().getTime());
				xmlhttp.onreadystatechange = function (){
					if(xmlhttp.readyState ==4 &&xmlhttp.status==200){
							var flag=xmlhttp.responseText;
							var sta=parseInt(flag);
							if(sta==1){
								alert("添加成功!");
								var d=document.getElementById("form1");
								var sp=document.getElementById("sp");
								var sp2=document.getElementById("sp2");
								sp.innerHTML="";
								sp2.innerHTML="";
								d.reset();
							}else if(sta==2){
								alert("添加失败!");
							}
						} 
				};
				xmlhttp.send(null);
			
			
		 }else{
			 alert("您提交了不合法的数据!请仔细检查。");
		 }
	}