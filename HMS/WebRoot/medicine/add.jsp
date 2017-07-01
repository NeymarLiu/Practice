<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>添加药品--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/ckeditor/ckeditor.js"></script>
 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="DrugServlet.do?method=getAllDrug";
		 });
    });
    </script>
  <script type="text/javascript">
  function  getXhr(){
			var xhr;
			try{
				xhr = new XMLHttpRequest();
			}catch(e){
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
		var aa = false;
    	function drugId(){
    		var number = document.f.id.value;
    		var sp = document.getElementById("spid");
    		var num=number.replace(/\s+/g,"");
    		if(number != null && number !=""){
    			if(num==number){
    			if(!isNaN(number)){
					var xhr = getXhr();
					xhr.open("get","DrugServlet.do?method=yanzheng&id="+number+"&time="+new Date().getTime(),true);
					xhr.onreadystatechange = function (){
						if(xhr.readyState == 4 && xhr.status == 200){
							var a = xhr.responseText;
							if(a==1){
								sp.innerHTML="<span style='color:green'>√</span>";
								aa = true;
							}else{
								sp.innerHTML="<span style='color:red'>编号存在</span>";
							}
						}
					}
					xhr.send(null);
				}else{
					sp.innerHTML="<span style='color:red'>编号必须为数字</span>";
				}
			}else{
			sp.innerHTML="<span style='color:red'>你输入的病历号不规范，请输入正确的病历号</span>";
			}
    		}else{
    			sp.innerHTML="<span style='color:red'>编号不能为空</span>";
    		}
    	}
    	/* 药品的图片 */
    	function drugUrl(){
    		var b = false;
    		var imge = document.f.url.value;
    		var reg2=/\.jpg$|\.png/;
    		var sp = document.getElementById("spurl");
    		if(imge != null && imge !=""){
					if(reg2.test(imge)){
    				sp.innerHTML="<span style='color:green'>√</span>";
					b = true;
    			}else{
    				sp.innerHTML="<span style='color:red'>图片格式不对，请选择png/jpg格式的图片</span>";
    			}
					
    		}else{
    			sp.innerHTML="<span style='color:red'>图片不能为空</span>";
    		}
    		return b;
    	}
    	/* 进价 */
    	function drugIn(){
    		var c = false;
    		var In = document.f.inprice.value;
    		var out = document.f.outprice.value;
    		var str=In.replace(/\s+/g,"");
    		var sp = document.getElementById("spIn");
    		if(In != null && In !=""){
    			if(str==In){
    			if(isNaN(out) && isNaN(In)){
    				sp.innerHTML="<span style='color:red'>只能为数字</span>";
    				c = false;
    			}else{
    				if(out != null && out !=""){
    				if((In-out)<0){
    					sp.innerHTML="<span style='color:green'>√</span>";
    					c = true;
    				}else{
    					sp.innerHTML="<span style='color:red'>进价不能比售价高</span>";	
    				}
				}else{
						sp.innerHTML="<span style='color:green'>√</span>";
    					c = true;
				}
    			}
    			}else{
    			sp.innerHTML="<span style='color:red'>进价格式不规范，请重新输入</span>";
    			}
    		}else{
    			sp.innerHTML="<span style='color:red'>进价不能为空</span>";
    		}
    		return c;
    	}
    	/* 售价 */
    	function drugOut(){
    		var d = false;
    		var out = document.f.outprice.value;
    		var sp = document.getElementById("spOut");
    		var In = document.f.inprice.value;
    		if(out != null && out !=""){
    			var str = out.replace(/\s+/g, "");
					if (str == out) {
						if (isNaN(out) && isNaN(In)) {
						sp.innerHTML = "<span style='color:red'>只能为数字</span>";
						d = false;
						} else {
						if (In != null && In != "") {
						if ((In - out) < 0) {
							sp.innerHTML = "<span style='color:green'>√</span>";
							d = true;
						} else {
							sp.innerHTML = "<span style='color:red'>售价不能比进价低</span>";
						}
					}
		}
		}else{
			sp.innerHTML = "<span style='color:red'>售价格式不规范，请重新输入</span>";
		}
		} else {
			sp.innerHTML = "<span style='color:red'>售价不能为空</span>";
		}
		return d;
    	}
    	/* 药品名称 */
    	function drugName(){
    		var e = false;
    		var Dname = document.f.name.value;
    		var sp = document.getElementById("spname");
    		if(Dname != null && Dname !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					e = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>药品名称不能为空</span>";
    		}
    		return e;
    	}
    	/* 简单描述 */
    	function drugSimdesc(){
    		var f = false;
    		var jian = document.f.simdesc.value;
    		var sp = document.getElementById("spSD");
    		if(jian != null && jian !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					f = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>简单描述不能为空</span>";
    		}
    		return f;
    	}
    	/* 保质期 */
    	function drugST(){
    		var g = false;
    		var bzq = document.f.savetime.value;
    		var sp = document.getElementById("spst");
    		if(bzq != null && bzq !=""){
    			if(isNaN(bzq)){
    				sp.innerHTML="<span style='color:red'>保质期只能是数字</span>";
    			}else{
    				if(parseInt(bzq) == bzq){
    					if(bzq > 0){
    						sp.innerHTML="<span style='color:green'>√</span>";
							g = true;
    					}else{
    						sp.innerHTML="<span style='color:red'>保质期必须大于0</span>";
    					}
    				}else{
    					sp.innerHTML="<span style='color:red'>保质期不能为小数</span>";
    				}
    			}
    		}else{
    			sp.innerHTML="<span style='color:red'>保质期不能为空</span>";
    		}
    		return g;
    	}
    	
    	/* 生产厂商 */
    	function drugFac(){
    		var h = false;
    		var sc = document.f.factory.value;
    		var sp = document.getElementById("spFC");
    		if(sc != null && sc !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					h = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>生产厂商不能为空</span>";
    		}
    		return h;
    	}
    	/* 详细描述 */
    	function drugDesc(){
    		var i = false;
    		var xx = document.f.desc.value;
    		var sp = document.getElementById("spdesc");
    		if(xx != null && xx !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					i = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>详细描述不能为空</span>";
    		}
    		return i;
    	}
    /* 	服用说明 */
    	function drugDC(){
    		var j = false;
    		var fy = document.f.direction.value;
    		var sp = document.getElementById("spDC");
    		if(fy != null && fy !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					j = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>服用说明不能为空</span>";
    		}
    		return j;
    	}
    	/* 备注 */
    	function drugRemark(){
    		var k = false;
    		var bz = document.f.remark.value;
    		var sp = document.getElementById("spRM");
    		if(bz != null && bz !=""){
					sp.innerHTML="<span style='color:green'>√</span>";
					k = true;
    		}else{
    			sp.innerHTML="<span style='color:red'>备注不能为空</span>";
    			
    		}
    		return k;
    		
    	}
		function tijiao(){
			var tj = false;
			var b = drugUrl();
			
			var c = drugIn();
			var d = drugOut();
			var e = drugName();
			
			var f = drugSimdesc();
			
			var g = drugST();
			
			var h = drugFac();
			
			var i = drugDesc();
			
			var j = drugDC();
			
			var k = drugRemark();
			
			if(aa){
				if(b && c && d && e && f && g && h && i && j && k){
					var one = window.confirm("确定要添加该药品吗？");
					if(one){
						tj = true;
						alert("添加成功！");
					}else{
						tj = false;
					}
				}
			}
			return tj;
		}
  </script>  
</head>
<body>
<form action="DrugServlet.do?method=addDrug" method="post" class="definewidth m20" name="f" onsubmit="return tijiao()" enctype="multipart/form-data">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td><input type="text" name="id" value="" onblur="drugId()"/></td>
        <td><span id="spid">*请输入药品编号(全为数字)</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td><input type="file" name="url" value="" onblur="drugUrl()"/></td>
        <td><span id="spurl">*请选择上传的图片</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td><input type="text" name="inprice" value="" onblur="drugIn()"/></td>
        <td><span id="spIn">*请输入药品的进价</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td><input type="text" name="outprice" value="" onblur="drugOut()"/></td>
        <td><span id="spOut">*请输入药品的售价</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td><input type="text" name="name" value="" onblur="drugName()"/></td>
        <td><span id="spname">*请输入药品的名称</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft" >药品类型</td>
        <td>
        <c:if test="${druglist.dr_type =='处方药' }">
	        	<input type="radio" name="type" value="处方药" checked="checked"/>处方药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="中药"/>中药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="西药"/>西药
        	</c:if>
        	<c:if test="${druglist.dr_type=='中药' }">
	        	<input type="radio" name="type" value="处方药"/>处方药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="中药" checked="checked"/>中药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="西药"/>西药
        	</c:if>
        	<c:if test="${druglist.dr_type=='西药' }">
	        	<input type="radio" name="type" value="处方药"/>处方药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="中药"/>中药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="西药" checked="checked"/>西药
        	</c:if>
        	<c:if test="${druglist.dr_type==null }">
	        	<input type="radio" name="type" value="处方药"/>处方药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="中药"/>中药&nbsp;&nbsp;&nbsp;
	        	<input type="radio" name="type" value="西药" checked="checked"/>西药
        	</c:if>
        	</td>
        <td><span id="sptype">*请选择药品的类型</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td><input type="text" name="simdesc" value="" onblur="drugSimdesc()"/></td>
        <td><span id="spSD">*请输入药品的简单描述</span></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">保质期</td>
        <td><input type="text" name="savetime" value="" onblur="drugST()"/>月</td>
        <td><span id="spst">*请输入药品的保质期</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td><textarea name="desc" onblur="drugDesc()"></textarea></td>
        <td><span id="spdesc">*请输入药品的详细描述</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td><textarea name="factory" onblur="drugFac()"></textarea></td>
        <td><span id="spFC">*请输入药品的生产厂商</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td><input type="text" name="direction" value="" onblur="drugDC()"/></td>
        <td width="40%"><span id="spDC">*请输入药品的服用说明</span></td>
    </tr>
   
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark" onblur="drugRemark()"></textarea></td>
        <td><span id="spRM">*请输入药品的备注</span></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button class="btn btn-primary"  type="submit">保存</button> &nbsp;
				&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>