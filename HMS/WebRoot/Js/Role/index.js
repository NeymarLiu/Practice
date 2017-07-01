/**
 * 
 */
  $(function () {
		$('#newNav').click(function(){
				window.location.href="RoleServlet.do?method=addRole";
		 });
    });
	
	
    function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
					deleteRole(ids);
			}else{
				alert("请选中要删除的项");
			}
		}
		function deleteRole(o1){
		var dstate=confirm("确认删除?");
			if(dstate==true){
			var xmlhttp=getXhr();
			xmlhttp.open("get","RoleServlet.do?method=deleteRole&rid="+o1+"&time="+new Date().getTime());
			//回调函数
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var state=parseInt(flag);
						if(state==1){
							alert("删除成功!");
							var uid=document.getElementById("user").value;
							var user=new String(o1);
							if(user.indexOf(",")!=-1){
								var ids=user.split(",");
								for(var i=0;i<ids.length;i++){
									if(ids[i]==uid){
										systemOut();
										break;
									}
								}
								window.location.href="RoleServlet.do?method=getAllRoles";
							}else if(o1==uid){
								systemOut();
							}else{
								window.location.href="RoleServlet.do?method=getAllRoles";
							}
						}else if(state==0){
							alert("删除失败!");
						}
					} 
			};
			//发送
			xmlhttp.send(null);
			}
			}mn
		function systemOut(){
			alert("您已被禁用本系统，将强制下线!");
			window.parent.location.href="LoginServlet.do?method=loginOut";
		}
		
		function edit(rid){
			var page=document.getElementsByName("page")[0].value;
			var type=document.getElementsByName("type")[0].value;
			var params=document.getElementsByName("params")[0].value;
			window.location.href="RoleServlet.do?method=getRoleInfo&rid="+rid+"&page="+page+"&type="+type+"&params="+params;
		}