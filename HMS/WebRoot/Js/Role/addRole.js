/**
 * 页面合法性验证
 */
 $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="RoleServlet.do?method=getAllRoles";
		 });
    });	

 /**
  * 角色添加：判断名称的唯一性
  * */
	function jugeUnique2(){
		var f=false;
			var xmlhttp=getXhr();
			var rname=document.getElementsByName("title")[0].value;
			var d=document.getElementById("sp");
			if(rname==""){
				d.innerHTML="<font color='red'>您没有输入!</font>";
				f=false;
			}else{
			xmlhttp.open("get","RoleServlet.do?method=judgeUnique&rname="+rname+"&time="+new Date().getTime(),false);
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var count=parseInt(flag);
						if(count==0){
							d.innerHTML="<font color='green' size='+2'>√</font>";
							f=true;
						}else if(count>0){	
							d.innerHTML="<font color='red'>角色名称已存在!</font>";
							f=false;
						}else if(count<0){
							d.innerHTML="<font color='red'>您没有输入!</font>";
							f=false;
						}
					}
			};
			xmlhttp.send(null);
			}
			return f;
	}

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
	
/**
 * 添加角色菜单资源
 * */
	function insertMenu(){
		var nameState=jugeUnique2();
		if(nameState==true){//如果用户名合法
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
				menu=menu+d3[di].value+",";
			}
		}
		xmlhttp.open("get","RoleServlet.do?method=insertMenu&group[]="+menu+"&title="+rname+"&status="+state+"&time="+new Date().getTime());
			xmlhttp.onreadystatechange = function (){
				if(xmlhttp.readyState == 4 && xmlhttp.status==200){
					var flag=xmlhttp.responseText;
					var state=parseInt(flag);
						if(state==1){
							alert("添加成功!");
							var fo=document.getElementById("addform");
							fo.reset();
							var sp=document.getElementById("sp");
							var sp2=document.getElementById("sp2");
							sp.innerHTML="";
							sp2.innerHTML="";
						}else if(state==2){
							alert("添加失败!");
						}
					
					} 
			};
			xmlhttp.send(null);
		}else{
			alert("请输入合法的角色名称!");
		}
	}