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
    <title>发药--中软高科-2015</title>
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
				window.location.href="DrugPeopleServlet.do?method=getAllDrugPeople";
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
		var number;
		var beh_leftmoney;
		var outprise;
		var a = false;
		var aaa;
		function changedrug(obj){
			var dr_id = obj;
			var bid = document.getElementsByName("bid")[0].value;
			var xhr = getXhr();
			var sp = document.getElementById("spnumber1");
			xhr.open("get","DrugPeopleServlet.do?method=giveDrugNumber&bid="+bid+"&dname="+dr_id+"&time="+new Date().getTime(),true);
			xhr.onreadystatechange = function (){
				if(xhr.readyState == 4 && xhr.status == 200){
					var DrugNumber = document.getElementById("DrugNumber");
					var array = eval(xhr.responseText);
					number = array[0];
					document.getElementById("DrugNumber").value=number;
					beh_leftmoney = array[1];
					outprise = array[2];
					aaa = parseInt(beh_leftmoney/outprise);
					a = true;
				}else{
				a=false;
				}
			}
			xhr.send(null);
		}
		function fyNumber(){
			var b = false;
			var number2 = document.getElementsByName("number")[0].value;
			var sp = document.getElementById("spnumber2");
			if(number2 != null && number2 !=""){
				if(isNaN(number2)){
					sp.innerHTML="<span style='color:red'>发药数只能是数字</span>";
				}else{
					if(parseInt(number2) == number2){
						if(number2<=number){
							if(number2 >0){
								if(number2>aaa){
									sp.innerHTML="<span style='color:red'>发药数不能大于病人余额最大可发数，提示病人交钱</span>";
								}else{
									sp.innerHTML="<span style='color:green'>√</span>";
									b = true;
								}
							}else{
								sp.innerHTML="<span style='color:red'>发药数不能为负数和0</span>";
							}
							
						}else{
							sp.innerHTML="<span style='color:red'>发药数不能大于未发数</span>";
						}	
					}else{
						sp.innerHTML="<span style='color:red'>发药数不能为小数</span>";
					}
				}
			}else{
				sp.innerHTML="<span style='color:red'>发药数不能为空</span>";
			}
			return b; 
		}
		function tijiao(){
			var tj = false;
			var b = fyNumber();
			if(a && b){
				var one = window.confirm("确定该操作？");
				if(one){
					tj = true;
				}
			}
			return tj;
		}
		
    </script>
</head>
<body>
<form action="DrugPeopleServlet.do?method=giveDrug" method="post" class="definewidth m20" onsubmit="return tijiao()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td><input type="hidden" name="bid" value="${dplist[0].beh_id}"/>${dplist[0].beh_id}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${dplist[0].hosr_name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td>
        <select name="dname"  onchange="changedrug(this.value)" id="dname">
        <option value="0">请选择要发的药品...</option>
        <c:forEach items="${dplist }" var="dplist">
        			<option value="${dplist.dr_id }">${dplist.dr_id }-${dplist.dr_name }</option>
        </c:forEach>
       </select>
       <span id="spnumber1"></span>
       </td>	
    </tr>
    <tr>
        <td width="10%" class="tableleft">发药数量</td>
        <input type="hidden" name="road" value="tosend1"/>
        <input type="hidden" name="fyjg" value="${fahao }"/>
        <script type="text/javascript">
       		 	var jg = document.getElementsByName("fyjg").value;
				if(jg != null){
					if(jg == 1){
						alert("发药成功");
					}else if(jg == 2){
						alert("病人住院药品修改失败，退还病人押金，退还药品库存，并提醒重新发药");
					}else if(jg == 3){
						alert("药品扣除失败，以归还病人押金");
					}else if(jg == 4){
						alert("病人余额扣除失败，重新操作");
					}else if(jg == 5){
						alert("病人余额不足，提醒病人去交钱");
					}else if(jg == 6){
						alert("药品库存不足，请添加库存");
					}else if(jg == 7){
						alert("病人押金归还失败");
					}else if(jg == 8){
						alert("病人押金归还成功,药品库存退还失败");
					}else if(jg == 9){
						alert("病人押金归还失败,药品库存退还成功");
					}else if(jg == 10){
						alert("病人押金归还失败,药品库存退还失败");
					}
				}
       		 </script>
        <td><input type="text" name="number" value="" id="DrugNumber" onblur="fyNumber()"/><span id="spnumber2"></span></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>