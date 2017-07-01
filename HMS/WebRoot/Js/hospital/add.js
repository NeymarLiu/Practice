$(function () {       
		$('#backid').click(function(){
				window.location.href="BeHospitalServlet.do?type=mohu";
		 });
    });
function getXhr(){
    		var xhr;
    		try{
    			xhr= new XMLHttpRequest();
	    	}catch(e){
	    		try{
	    			xhr=new ActiveXObject("Microsoft.XMLHTTP");
	    		}catch(e){
	    			try{
	    				xhr=new ActiveXObject("Msxm12.XMLHTTP");
	    			}catch(e){
	    				alert("你的浏览器不支持ajax，请跟换浏览器，推荐火狐。");
	    			}
	    		}
	    	}
	    	return xhr;
    	}
    	function bingli(){
    		var cid=document.getElementsByName("id")[0].value;
    		var sp=document.getElementById("sp");
    			var regex=/\s+/g;
        		cid=cid.replace(regex,"");
        		// alert(cid);
        		// 创建XMLHttpReqyest对象
        		var xhr=getXhr();
        		
        		// 打开链接
        		xhr.open("post", "BeHospitalServlet.do?type=addOne&cid="+encodeURI(cid)+"&time="+new Date().getTime(),true);
        		// 设置post的提交方式头
        		//xhr.setRequestHeader("Content-type", "application/x-ww-form-urlencoded");
        		// 回调函数
        		xhr.onreadystatechange = function() {
    	    		if(xhr.readyState == 4 && xhr.status ==200){
    	    			// 四个状态值
    	    			var result=xhr.responseText;
    	    			//eval("result="+result);
    	    			if(result !=0){
    	    				//
    	    				document.getElementById("divmenus").style.display = "block";
    						document.getElementById("divmenus").innerHTML = result;
    						hbb=true;
    	    			}else{
    	    				//
    	    				document.getElementById("divmenus").style.display = "none";
    	    				sp.innerHTML="<span style='color:red'>该用户不存在或已经住院</span>";
    	    				//
    	    				hbb=false;
    	    			}
    	    		}
        		};
        		// 发送
        		xhr.send(null);
    		
    	}
    	//联动
		function cil(id,name,idcard,secard,phone,self1,sex1,age,look1,keshi,doctor){
			var sp=document.getElementById("sp");
			sp.innerHTML="<span style='color:green'>√</span>"
			document.getElementById("divmenus").style.display = "none";
			document.getElementById("id").value=id;
			document.getElementById("name").innerHTML="<font>"+name+"</font>";
			  document.getElementById("sheng").innerHTML="<font>身份证</font>";
			  document.getElementById("idcard").innerHTML="<font>"+idcard+"</font>";
			  document.getElementById("secard").innerHTML="<font>"+secard+"</font>";
			  document.getElementById("phone").innerHTML="<font>"+phone+"</font>";
			  document.getElementById("age").innerHTML="<font>"+age+"</font>";
			  document.getElementById("keshi").innerHTML="<font>"+keshi+"</font>";
			  document.getElementById("doctor").innerHTML="<font>"+doctor+"</font>";
			  var self=document.getElementsByName("self");
			  if(self1==0){//自费
			  self[0].checked=true; }
			  if(self1==1){//用社保
			  self[1].checked=true; } 
			  var sex=document.getElementsByName("sex");
			  if(sex1=="男"){ 
				  sex[0].checked=true;
			  }else if(sex1=="女"){
			  sex[1].checked=true; } 
			  var look=document.getElementsByName("look");
			  if(look1==0){//0初诊
			  look[0].checked=true; }
			  if(look1==1){//1复诊
			  look[1].checked=true; }
		}
		
		function bing(){
			binll=false;
			var cid=document.getElementsByName("id")[0].value;
			var self=document.getElementsByName("ke")[0].value;
			//alert(self);
    		var sp=document.getElementById("sp");
    		if(cid != null && cid !=""){
    			if(self == null || self == ""){
        			sp.innerHTML="<span style='color:red'>请输入正确的病例号</span>";
        		}else{
        			binll=true;
        		}
    		}else{
    			sp.innerHTML="<span style='color:red'>请输入的病例号</span>";
    		}
    		
    		
		}
    	function panduan1(){
        	var isok=true;
        	var nu=document.getElementsByName("nursepeople")[0].value;
        	var sp=document.getElementById("sp1");
        	if(nu == null || nu=="" || nu=="null"){
        			// alert(nu=="");
        			sp.innerHTML="<span style='color:red'>护理人不能为空</span>";
        			isok=false;
        		}else{
        			sp.innerHTML="<span style='color:green'>√</span>";
        		}
        		return isok;
        }
        function panduan2(){
        var sp2 = document.getElementById("sp2");
    	var xa = document.getElementsByName("id")[0].value;
    	var pa = document.getElementsByName("patbed")[0].value;

    	if (pa == null || pa == "" || pa == "null") {
    		sp2.innerHTML = "<span style='color:red'>床位号不能为空</span>";
    		isok = false;
    	} else {
    		if(isNaN(pa)){
        		sp2.innerHTML = "<span style='color:red'>床位号只能是数字</span>";
        		isok = false;
    		}else{
    			var regex=/\s+/g;
        		pa=pa.replace(regex,"");
    			var xhr = getXhr();
        		var isok = true;
        		// 打开链接
        		xhr.open("post", "BeHospitalServlet.do?type=addCWH&cid=" + xa + "&pbed="
        				+ pa + "&time=" + new Date().getTime(), false);
        		// 设置post的提交方式头
        		// xhr.setRequestHeader("Content-type",
        		// "application/x-ww-form-urlencoded");
        		// 回调函数 /\s+/g
        		
        		xhr.onreadystatechange = function() {
        			if (xhr.readyState == 4 && xhr.status == 200) {
        				// 四个状态值jax
        				var result = xhr.responseText;
        				if (result == "true") {
        					sp2.innerHTML = "<span style='color:red'>床位号已存在！</span>";
        					isok = false;
        				} else {
        					sp2.innerHTML = "<span style='color:green'>√</span>";
        				}
        			}

        		};
        		// 发送
        		xhr.send(null);
    		}
    		
    	}
    	return isok;
        }
        function panduan3(){
        	var isok=false;
        	var money=document.getElementsByName("money")[0].value;
        	var sp3=document.getElementById("sp3");
        	if(money == null || money=="" || money=="null"){
    	    		sp3.innerHTML="<span id='sp3' style='color:red'>不能为空</span>";
        		}else{
        			var regex=/\s+/g;
        			money=money.replace(regex,"");
        			if(money >0){
        				if(!isNaN(money)){
        	    			// 数字
        	    			sp3.innerHTML="<span id='sp3' style='color:green'>√</span>";
        	    			isok=true;
        	    		}else{
        	    			sp3.innerHTML="<span id='sp3' style='color:red'>不是数字</span>";
        	    		}
        			}else{
        				if(money == 0) {
        					sp3.innerHTML = "<span style='color:red'>押金不能为0</span>";
        					isok = false;
        				}else{
        					sp3.innerHTML = "<span style='color:red'>押金不能为负数</span>";
        					isok = false;
        				}
        			}
        		}
        		return isok;
        }	
        function panduan4(){
        	var isok=true;
        	var pa=document.getElementsByName("illness")[0].value;
        	var sp2=document.getElementById("sp4");
        	if(pa == null || pa=="" || pa=="null"){
        			sp2.innerHTML="<span style='color:red'>病情不能为空</span>";
        			isok=false;
        		}else{
        			sp2.innerHTML="<span style='color:green'>√</span>";
        		}
        		return isok;
        }
        function test(){
        	bing()
        	if(binll){
        		var s1=panduan1();
	        	var s2=panduan2();
	        	var s3=panduan3();
	        	var s4=panduan4();
	        	if(s1 && s2 && s3 && s4 ){
	        		return true;
	        	}
        	}else{
        		alert("请填写正确的病例号");
        	}
	        	
        	return false;
        }