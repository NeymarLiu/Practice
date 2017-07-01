<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<%@  taglib  uri="http://testTag/lxc"   prefix="p" %>
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
    <title>挂号--中软高科-2015</title>
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
				window.location.href="ReigisterServlet.do?method=query";
		 });
    });
    
    function getOffice(){
    	//判断此人对应的科室
    	var office=document.getElementById("ks").value;
    	var allOffice=document.getElementById("ofc");
    	for(var i=0;i < allOffice.length;i++){
    		if(allOffice[i].value == office){
    			allOffice[i].selected=true;
    		}
    	}
    }
    
   
    
    /* function yanPhone(){
    	//验证座机号码是否合格
    	var boo4=true;
    	var number=document.getElementById("phone").value;
    	var zuoji = number.replace(/\s+/g,"");
    	var span4=document.getElementById("spanPhone");
    	span4.innerHTML="<span id='spanPhone'></span>";
    	var isPhone = /([0-9]{2,3}-)-?[0-9]{8,9}$/;
    	if(zuoji == null || zuoji == ""){
    		boo4=false;
    		span4.innerHTML="<span id='spanPhone'>座机号不能为空</span>";
    	}else if(!isPhone.test(zuoji)){
    		boo4=false;
    		span4.innerHTML="<span id='spanPhone'>座机号输入格式有误</span>";
    	}else{
    		boo4=true;
    	}
    	return boo4;
    } */
    
    </script>
</head>
<body>
<form action="ReigisterServlet.do?method=update&hid=${hosr.hosr_id }" onsubmit="return yanPhone()" method="post" class="definewidth m20">
<input type="hidden" name="keshi" id="ks" value="${hosr.keshi }"/>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="pname" readonly="readonly" value="${hosr.hosr_name }"/></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="pidcard" readonly="readonly" value="${hosr.hosr_idcar }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td><input type="text" name="pmoney" value="${hosr.hosr_regprice }" readonly="readonly"/>元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><input type="text" name="pmedocal" readonly="readonly" value="${hosr.hosr_medical }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" onkeyup="yanPhone()" name="pphone" id="phone" value="${hosr.hosr_phone }"/><span id='spanPhone'></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td>
        	<c:if test="${hosr.hosr_selfprice == 0 }">
    			<input type="radio" name="pslef" value="1"/>否
        		<input type="radio" name="pslef" value="0" checked/>是
        	</c:if>
        	<c:if test="${hosr.hosr_selfprice == 1 }">
    			<input type="radio" name="pslef" value="1" checked/>否
        		<input type="radio" name="pslef" value="0"/>是
        	</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
        	
        	<c:if test="${hosr.hosr_sex == '男'}">
        		<input type="radio" name="psex" value="男" readonly="readonly" checked/>男
    			<input type="radio" name="psex" value="女" readonly="readonly"/>女
        		
        	</c:if>
        	
        	<c:if test="${hosr.hosr_sex == '女'}">
    			<input type="radio" name="psex" value="男" readonly="readonly"/>男
        		<input type="radio" name="psex" value="女" readonly="readonly" checked/>女
        	</c:if>
        	
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="page" readonly="readonly" value="${hosr.hosr_age }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td><input type="text" name="pwork" value="${hosr.hosr_word }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td>
        	<c:if test="${hosr.hosr_lookdoctor == 0 }">
        		<input type="radio" name="plook" value="0" checked/>初诊
				<input type="radio" name="plook" value="1"/>复诊
        	</c:if>
        	<c:if test="${hosr.hosr_lookdoctor == 1 }">
        		<input type="radio" name="plook" value="0"/>初诊
				<input type="radio" name="plook" value="1" checked/>复诊
        	</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
        	<select id="office" name="ofc">
        		<c:forEach var="office" items="${keshi }">
        			<c:if test="${office.sta == 1 }">
        				<option value="${office.d_keshi }" selected="selected">${office.d_keshi }</option>
        			</c:if>
        		</c:forEach>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>
        	<select name="pdoctor">
        		<c:forEach items="${dname }" var="doctorName">
        			<c:if test="${doctorName.dsta == 1 }"><option selected="selected">${doctorName.d_name }</option></c:if>
        			<c:if test="${doctorName.dsta == 0 }"><option>${doctorName.d_name }</option></c:if>		
        		</c:forEach>
        	</select>
        </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="pdesc">${hosr.hosr_remark }</textarea></td>
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