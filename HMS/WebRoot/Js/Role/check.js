/**
 * 页面用户名合法性验证
 */

	f=0;
	/** 恢复默认*/
	function defaultSelect(rid){
		var page=document.getElementsByName("page")[0].value;
		var type=document.getElementsByName("type")[0].value;
		var params=document.getElementsByName("params")[0].value;
		//alert("RoleServlet.do?method=getRoleInfo&rid="+rid+"&page="+page+"&type="+type+"&params="+params);
		window.location.href="RoleServlet.do?method=getRoleInfo&rid="+rid+"&page="+page+"&type="+type+"&params="+params;
	
	}
	/**
	 * 清空输入框内容：只执行一次
	 * */
	function clearTitle(){
		var d=document.getElementsByName("title")[0];
		if(f<1){
			d.value="";
			f++;
		}
	
	}
	/** 
	 * 角色信息更改:判断名称的唯一性
	 * */
	function jugeUnique(obj){
		var checkState=true;
		if(f==1){
			var xmlhttp=getXhr();
			var rname=document.getElementsByName("title")[0].value;
			xmlhttp.open("get","RoleServlet.do?method=judgeUnique&rname="+rname+"&rid="+obj+"&time="+new Date().getTime(),false);
			//回调函数
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var a=parseInt(flag);
							var d=document.getElementById("sp");
						if(a==0){
							d.innerHTML="<font color='green'>角色名称可用!</font>";
							checkState=true;
						}else if(a>0){
							d.innerHTML="<font color='red'>角色名称已存在!</font>";
							checkState=false;
						}else if(a<0){
							d.innerHTML="<font color='red'>您没有输入!</font>";
							checkState=false;
						}
					} 
			};
			//发送
			xmlhttp.send(null);
		}
		return checkState;
	}
	
	/**
	 *  清空选项
	 *  */
	function clearSelect(){
		var d=document.getElementsByName("group[]");
		for(var i=0;i<d.length;i++){
			if(d.item(i).checked==true){
				d.item(i).checked=false;
			}
		}
	
	}
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
			var page=document.getElementsByName("page")[0].value;
			var params=document.getElementsByName("params")[0].value;
			var type=document.getElementsByName("type")[0].value;
				window.location.href="RoleServlet.do?method="+type+"&page="+page+"&params="+params;
		 });

    });
    /** 获取ajxa*/
    function  getXhr(){
			var xhr;
			try{
				xhr = new XMLHttpRequest();//没有    报错   就会产生异常  异常就可以  try catch 处理
			}catch(e){
				//IE  6  或者   6 之上的
				try{
					xhr=new ActiveXObject("Microsoft.XMLHTTP"); 
				}catch(e){
					try{
						xhr= new new ActiveXObject("Msxml2.XMLHTTP");
					}catch(e){
						alert("你的浏览器不支持ajax，请跟换浏览器，建议换火狐");
					}
				}
			}
			return xhr;
	}
    
    /**更新菜单资源*/
	function updateMenu(obj){
		var st=jugeUnique(obj);
		var d=document.getElementsByName("title")[0];
		if(d.value!="" && st==true){//如果用户名合法
		var xmlhttp=getXhr();
		var menu="";
		var menus=new Array();
		var rname=document.getElementsByName("title")[0].value;
		var d2=document.getElementsByName("status");
		for(var di=0;di<d2.length;di++){
			if(d2[di].checked==true){
				state=d2[di].value;
			}
		}
		var d3=document.getElementsByName("group[]");
		for(var di=0;di<d3.length;di++){
			if(d3[di].checked==true){
				menu+=d3[di].value+",";
			}
		}
		xmlhttp.open("get","RoleServlet.do?method=changeMenus&roleid="+obj+"&group[]="+menu+"&title="+rname+"&status="+state+"&time="+new Date().getTime());
			//回调函数
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var stat=parseInt(flag);
						if(stat==1){
							alert("操作成功!");
							if("1"==state){
								var us=document.getElementById("user").value;
								if(obj==us){
									systemOut();
								}
							}
						}else if(stat==2){
							alert("操作失败!");
						}
					
					} 
			};
			//发送
			xmlhttp.send(null);
		}else{
			alert("请输入合法的角色名称!");
		}
	}
	function systemOut(){
		alert("您已被禁用本系统，将强制下线!");
		window.parent.location.href="LoginServlet.do?method=loginOut";
	} 