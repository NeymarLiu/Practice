/**
 *菜单资源管理首页 
 */

	/**
	 * 添加菜单
	 * */
	 $(function () {
			$('#newNav').click(function(){
					window.location.href="Resource/add.jsp";
			 });
	    });
		
		function edit(id){
			var page=document.getElementsByName("page")[0].value;
			var params=document.getElementsByName("params")[0].value;
			var type=document.getElementsByName("type")[0].value;
			window.location.href="MenuServlet.do?method=getMenuInfo&mid="+id+"&page="+page
				+"&type="+type+"&params="+params;
		}
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
	 
	 /**
	  * 删除多个角色
	  * */
	function delAll(){
		var alls=document.getElementsByName("check");
		var ids=new Array();
		for(var i=0;i<alls.length;i++){
			if(alls[i].checked){
				ids.push(alls[i].value);
			}		
		}
		if(ids.length>0){
			deleteMenu(ids);
		}else{
			alert("请选中要删除的项!");
		}
	}
	
	
	/**
	 * 删除角色
	 * */
	function deleteMenu(o1){
		var dstate=confirm("确认删除?");
		if(dstate==true){
		var xmlhttp=getXhr();
		xmlhttp.open("get","MenuServlet.do?method=deleteMenu&mid="+o1+"&time="+new Date().getTime());
		xmlhttp.onreadystatechange = function (){
		if(xmlhttp.readyState == 4 && xmlhttp.status==200){
			var flag=xmlhttp.responseText;
			var state=parseInt(flag);
			if(state==1){
				alert("删除成功!");
			}else if(state==0){
					alert("删除失败!");
			}
				window.location.href="MenuServlet.do?method=getAllMenus";
		} 
		};
		xmlhttp.send(null);
			}
	}
			  