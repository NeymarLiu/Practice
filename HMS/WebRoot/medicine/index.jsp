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
    <title>药品查询--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

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
		$('#newNav').click(function(){
				window.location.href="medicine/add.jsp";
		 });
    });
	
    	function checkall(){
			var alls=document.getElementsByName("id");
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
			var alls=document.getElementsByName("id");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					alert("成功!");
				}
			}else{
				alert("请选中要操作的项");
			}
		}
		function qingkong(){
			var one = window.confirm("是否确定清空");
			if(one){
				window.location.href="DrugServlet.do?method=getAllDrug";
				alert("清空成功");
			}
		}
			function daochu(){
			var id = "";
			var count=0;
			var ids = document.getElementsByName("id");
			for(var i=0; i<ids.length;i++){
				if(ids[i].checked == true){
					if(id == ""){
						id = ids[i].value+",";
					}else{
						id=id+ids[i].value+",";
					}
					count++;
				}
			}
			if(count >0){
				if(confirm("你确定要导出吗？")){
					location.href="DrugServlet.do?method=daoChuExcel&id="+id;
					alert("导出成功");
				}
			}else{
				alert("请选择操作项？");
			}
			
		}
    </script>
</head>
<body>
<div>
<form action="DrugServlet.do?method=getAllDrug" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft" >药品名称：</td>
        <td><input type="text" name="pname" value="${dr_name}"/></td>
        <td width="10%" class="tableleft" >药品类型：</td>
        <td><select name="types" >
        	<c:if test="${dr_type==1 }">
	        	<option value="1" selected="selected">中药</option>
	        	<option value="2">西药</option>
	        	<option value="3">处方</option>
	        	<option value="4">非处方</option>
	        </c:if>
	        <c:if test="${dr_type==2 }">
	        	<option value="1">中药</option>
	        	<option value="2" selected="selected">西药</option>
	        	<option value="3">处方</option>
	        	<option value="4">非处方</option>
	        </c:if>
	        <c:if test="${dr_type==3 }">
	        	<option value="1">中药</option>
	        	<option value="2">西药</option>
	        	<option value="3" selected="selected">处方</option>
	        	<option value="4">非处方</option>
	        </c:if>
	        <c:if test="${dr_type==4 }">
	        	<option value="1">中药</option>
	        	<option value="2">西药</option>
	        	<option value="3">处方</option>
	        	<option value="4" selected="selected">非处方</option>
	        </c:if>
	        <c:if test="${dr_type==0 || dr_type==null }">
	        	<option value="0" selected="selected">请选择药品类型</option>
	        	<option value="1">中药</option>
	        	<option value="2">西药</option>
	        	<option value="3">处方</option>
	        	<option value="4">非处方</option>
	        </c:if>
        </select></td>
    </tr>
    <tr>
		  <td colspan="4">
		  	<center>	
				<button type="submit" class="btn btn-primary">查询</button> 
				<!-- <button type="reset" class="btn btn-primary" type="button" name="newNav" id="newNav" >清空</button> -->
				<a href="DrugServlet.do?method=getAllDrug" class="btn btn-primary" type="button">清空</a>
			</center>
        </td>
    </tr>
</table>
</form>
</div>
		<table class="table table-bordered table-hover definewidth m10" >
   		<thead>
    	<tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>药品编号</th>
        <th>药品名称</th>
        <th>药品类型</th>
        <th>简单描述</th>
        <th>状态</th>
        <th>剩余量</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach var="d" items="${dlist}">
	     <tr >
         	<td style="vertical-align:middle;"><input type="checkbox" name="id" value="${d.dr_id }"></td>
            <td style="vertical-align:middle;">${d.dr_id }</td>
            <td style="vertical-align:middle;">${d.dr_name}</td>
            <td style="vertical-align:middle;">${d.dr_type }</td>
            <td style="vertical-align:middle;">${d.dr_simdesc }</td>
            <td style="vertical-align:middle;">
            	<c:if test="${d.dr_state==0 }">销售中</c:if>
				<c:if test="${d.dr_state==1 }">下架</c:if>
            </td>
            <td style="vertical-align:middle;">${d.dr_number }</td>
            <td style="vertical-align:middle;">
			<a href="DrugServlet.do?method=editChoose&id=${d.dr_id }">编辑</a>&nbsp;&nbsp;&nbsp;
			<a href="DrugServlet.do?method=addMedicine&id=${d.dr_id }">添加库存</a>&nbsp;&nbsp;&nbsp;
			<a href="DrugServlet.do?method=lookOneDrug&id=${d.dr_id }">详情>>></a>
			</td>
        </tr> 
        </c:forEach>   
  </table>
   <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  		<c:if test="${dp.count ==0 }">
  		首页&nbsp;上一页&nbsp;下一页&nbsp;尾页&nbsp;
  		</c:if>
  		<c:if test="${dp.count !=0 }">
  		<c:if test="${dp.nowpage ==1}">首页&nbsp;上一页</c:if>
  		<c:if test="${dp.nowpage !=1}">
          <a href="DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=1" >首页</a>
          <a href="DrugServlet.do?method=getAllDrug&sss=1&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage-1}">上一页</a> 
        </c:if>   
         <!--  总页码大于5时 -->
        <c:if test="${pageMax>5 }">
    			     <!--  当前页小于总页数-4 -->
        <c:if test="${dp.nowpage <pageMax-4}">
          			<!--  当前页小3页 -->	
        <c:if test="${dp.nowpage <3}">
          			<!--  当前页小3页，页面显示1-5页，跨步1 -->
     <c:forEach begin="1" end="5" step="1" varStatus="n">
        <c:if test="${dp.nowpage !=n.index}">
         		<a href="DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${n.index }" name="nowpage">${n.index }</a>
        </c:if>
        <c:if test="${dp.nowpage ==n.index}">
          				<!-- 当前页是为红色，不可点击 -->
          		<span class="current" name="nowpage">${dp.nowpage }</span>
        </c:if>
     </c:forEach>
          			<!-- 总页码大于本页面所显示的最大页码时,之后的只显示... -->
          	    <a href="DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage +2}" name="nowpage">...</a>
        </c:if>
          			<!--  当前页大与3页 -->	
        <c:if test="${dp.nowpage >3}">
          			<!--  当前页大与3页，起始页码为当前页-2，，结束页码为当前页+2 -->
          	<a href='DrugServlet.do?type=getAllDrug&zm=1&DrugName=${dr_name }&DrugType=${dr_type }&nowpage=${nowpage -2}'>...</a>
          	<c:forEach begin="${dp.nowpage-2 }" end="${dp.nowpage+2 }" step="1" varStatus="n">
         		<c:if test="${dp.nowpage !=n.index}">
         			<a href="DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${n.index }" name="nowpage">${n.index }</a>
          		</c:if>
          		<c:if test="${dp.nowpage ==n.index}">
          				<!-- 当前页是为红色，不可点击 -->
          			<span class="current" name="nowpage">${dp.nowpage }</span>
          		</c:if>
          	</c:forEach>
          			<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage +2}'>...</a>
        </c:if>
          			
      </c:if>
          		<!--  当前页大于等于总页数-4 -->
        <c:if test="${dp.nowpage >=pageMax-4}">
          	<c:if test="${dp.nowpage<pageMax-2 }">
          		<c:forEach begin="${dp.nowpage-2 }" end="${dp.nowpage+2 }" step="1" varStatus="n">
	         		<c:if test="${dp.nowpage !=n.index}"> 
	         			<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${n.index }'>${n.index }</a>
	         		</c:if>
	          		<c:if test="${dp.nowpage ==n.index}"> 
	          				<!-- 当前页是为红色，不可点击 -->
	          			<span class='current'>${dp.nowpage }</span>
	          		</c:if>
          		</c:forEach>
          				<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage +2}'>...</a>
          	</c:if>
          	<c:if test="${dp.nowpage>=pageMax-2 }">
          			<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage -2}'>...</a>
          		<c:forEach begin="${pageMax-4 }" end="${pageMax }" step="1" varStatus="n">
	         		<c:if test="${dp.nowpage !=n.index}"> 
	         			<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${n.index }'>${n.index }</a>
	         		</c:if>
	          		<c:if test="${dp.nowpage ==n.index}">
	          				<!-- 当前页是为红色，不可点击 --> 
	          			<span class='current'>${dp.nowpage }</span>
	          		</c:if>
          		</c:forEach>
          	</c:if>
          </c:if>
          </c:if>
          <!--  总页码小于等于5时 -->
          <c:if test="${pageMax<=5 }">
          	<!-- 显示1-总页码，循环得到不同的页码显示 -->
          		<c:forEach begin="1" end="${pageMax }" step="1" varStatus="n">
          			<c:if test="${dp.nowpage !=n.index}"> 
          				<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${n.index }'>${n.index }</a>
          			</c:if>
          			<!-- 当前页是为红色，不可点击 -->
          			<c:if test="${dp.nowpage ==n.index}">
          				<span class='current'>${dp.nowpage }</span>
          			</c:if>
          		</c:forEach>
          </c:if>
      
          <!-- 当前页码为最后一页的显示情况 -->
          <c:if test="${dp.nowpage == pageMax }">下一页&nbsp;尾页</c:if>
          <c:if test="${dp.nowpage != pageMax }">
          	<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${dp.nowpage +1}' >下一页</a>
          	<a href='DrugServlet.do?method=getAllDrug&pname=${dr_name }&types=${dr_type }&nowpage=${pageMax }' >尾页</a> 
          </c:if>
		  </c:if>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>${dp.count }</span>条记录<span class='current'> ${dp.nowpage }/${pageMax } </span>页
		  </div>
	</th></tr>
	<tr><th colspan="5" align="center">
		 <div> 
			<button type="button" class="btn btn-success" id="newNav">添加新药</button>
			<button type="button" class="btn btn-success" onclick="daochu()">导出Excel</button>			
		 </div>
		 
		 </th></tr>
  </table>
</body>
</html>
