/**
 * 首页
 */
 $(function () {
		$('#newNav').click(function(){
				window.location.href="UserServlet.do?method=addUser";
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
			deleteUser(ids);
		}else{
			alert("请选中要删除的项");
		}
	}
	function edit(obj){
		var page=document.getElementsByName("page")[0].value;
		var type=document.getElementsByName("type")[0].value;
		var params=document.getElementsByName("params")[0].value;
		window.location.href="UserServlet.do?method=editUser&uid="
			+obj+"&page="+page+"&type="+type+"&params="+params;
	}
	/**
	 * 删除用户
	 * */
	function deleteUser(o1){
		var dstate=confirm("确认删除?");
		if(dstate==true){
		var xmlhttp=getXhr();
		xmlhttp.open("get","UserServlet.do?method=deletetUser&uid="+o1+"&time="+new Date().getTime());
		xmlhttp.onreadystatechange = function (){
		if(xmlhttp.readyState == 4 && xmlhttp.status==200){
			var flag=xmlhttp.responseText;
			var state=parseInt(flag);
			if(state==1){
				alert("提示:删除成功!")
				var userId=document.getElementById("user").value;
				var user=new String(o1);
				if(user.indexOf(",")!=-1){
					var ids=user.split(",");
					for(var i=0;i<ids.length;i++){
						if(ids[i]==userId){
							systemOut();
							break;
						}
					}
					window.location.href="UserServlet.do?method=initUser";
				}else if(userId==o1){
					systemOut();
				}else{
					window.location.href="UserServlet.do?method=initUser";
					
				}
			}else if(state==0){
				alert("提示:删除失败!")
				window.location.href="UserServlet.do?method=initUser";
			}
		} 
		};
		xmlhttp.send(null);
		}
	}
	function systemOut(){
		alert("您已被禁用本系统，将强制下线!");
		window.parent.location.href="LoginServlet.do?method=loginOut";
	}
		