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
    <title>发药详情--中软高科-2015</title>
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
    	function checkall(){
			var alls=document.getElementsByName("pid");
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
			var alls=document.getElementsByName("pid");
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
    $(function () {       
		$('#backid').click(function(){
				window.location.href="javascript:history.go(-1)";
		 });
    });
    function fayao(obj){
		var b = false;
		if(obj == 0){
			var one = window.confirm("确定该操作？");
			if(one){
				b = true;
			}
		}else if(obj == 1){
			var one = window.confirm("确定该操作？");
			if(one){
				b = true;
			}
		}else if(obj == 2){
			var one = window.confirm("确定该操作？");
			if(one){
				b = true;
			}
		}else if(obj == 3){
			var one = window.confirm("确定该操作？");
			if(one){
				b = true;
			}
		}
		return b;
	}
    </script>
</head>
<body>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>药品名称</th>
        <th>药品数量</th>
        <th>已发数量</th>
        <th>未发数量</th>
        <th>操作</th>
    </tr>
    </thead>
    	<c:forEach var="dp" items="${dplist}">
	     <tr >
            <td style="vertical-align:middle;" name="pid" value="${dp.beh_id}">${dp.beh_id}</td>
            <td style="vertical-align:middle;">${dp.hosr_name}</td>
            <td style="vertical-align:middle;">${dp.dr_name}</td>
            <td style="vertical-align:middle;">${dp.dr_number}</td>
            <td style="vertical-align:middle;">${dp.dr_hadsent}</td>
            <td style="vertical-align:middle;">${dp.dr_notsent}</td>
            <td style="vertical-align:middle;">
            		<input type="hidden" name="dname" value="${dp.dr_id }">
            		<c:if test="${dp.dr_notsent >=30}">
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=${dp.dr_notsent}&nowpage=1&road=tosend2" onclick="return fayao(0)">发全</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=1&nowpage=1&road=tosend2" onclick="return fayao(1)">发1</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=5&nowpage=1&road=tosend2" onclick="return fayao(2)">发5</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=30&nowpage=1&road=tosend2" onclick="return fayao(3)">发30</a>
	            	</c:if>
	            	<c:if test="${dp.dr_notsent <30 && dp.dr_notsent>=5}">
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=${dp.dr_notsent}&nowpage=1&road=tosend2" onclick="return fayao(0)">发全</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=1&nowpage=1&road=tosend2" onclick="return fayao(1)">发1</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=5&nowpage=1&road=tosend2" onclick="return fayao(2)">发5</a>&nbsp;&nbsp;&nbsp;
	            	</c:if>
	            	<c:if test="${dp.dr_notsent <5}">
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=${dp.dr_notsent}&nowpage=1&road=tosend2" onclick="return fayao(0)">发全</a>&nbsp;&nbsp;&nbsp;
	            		<a href="DrugPeopleServlet.do?method=giveDrug&bid=${dp.beh_id }&dname=${dp.dr_id }&number=1&nowpage=1&road=tosend2" onclick="return fayao(1)">发1</a>&nbsp;&nbsp;&nbsp;
	            	</c:if>
        
            </td>
        </tr>
  </c:forEach>
  </table>
  <form action="#" name="f" method="post" class="definewidth m20">
  <input type="hidden" name="fyjg" value="${fayao }"/>
  <table>
  	<tr>
  		<td><span id="spJQ"></span></td>
  	</tr>
  </table>
  </form>
  			<script type="text/javascript">
       		 	var jg = document.f.fyjg.value;
       		 	var sp = document.getElementById("spJQ");
				if(jg != null){
					if(jg == 1){
						alert("发药成功");
					}else if(jg == 2){
						sp.innerHTML="<span style='color:red'>病人住院药品修改失败，退还病人押金，退还药品库存，并提醒重新发药</span>";
					}else if(jg == 3){
						sp.innerHTML="<span style='color:red'>药品扣除失败，已归还病人押金</span>";
					}else if(jg == 4){
						sp.innerHTML="<span style='color:red'>病人余额扣除失败，重新操作</span>";
					}else if(jg == 5){
						sp.innerHTML="<span style='color:red'>病人余额不足，提醒病人去交钱</span>";
					}else if(jg == 6){
						sp.innerHTML="<span style='color:red'>药品库存不足，请添加药品库存</span>";
					}else if(jg == 7){
						sp.innerHTML="<span style='color:red'>病人押金归还失败</span>";
					}else if(jg == 8){
						sp.innerHTML="<span style='color:red'>病人押金归还成功,药品库存退还失败</span>";
					}else if(jg == 9){
						sp.innerHTML="<span style='color:red'>病人押金归还失败,药品库存退还成功</span>";
					}else if(jg == 10){
						sp.innerHTML="<span style='color:red'>病人押金归还失败,药品库存退还失败</span>";
					}
				}
       		 </script>
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  		<c:if test="${dp.count ==0 }">
  		首页&nbsp;上一页&nbsp;下一页&nbsp;尾页&nbsp;
  		</c:if>
  		<c:if test="${dp.count !=0 }">
           <c:if test="${dp.nowpage ==1}">首页</c:if>
  				<c:if test="${dp.nowpage !=1}">
         			 <a href="DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=1" >首页</a> 
        		</c:if>   
           <!-- 当前页码为第一页的显示情况 -->
        <c:if test="${dp.nowpage ==1}">上一页</c:if>
        <c:if test="${dp.nowpage !=1}">
          	<a href="DrugPeopleServlet.do?method=lookDrug&sss=1&bid=${id }&nowpage=${dp.nowpage-1}">上一页</a>
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
         					<a href="DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${n.index }" name="nowpage">${n.index }</a>
        				</c:if>
        				<c:if test="${dp.nowpage ==n.index}">
          				<!-- 当前页是为红色，不可点击 -->
          					<span class="current" name="nowpage">${dp.nowpage }</span>
        				</c:if>
    				</c:forEach>
          			<!-- 总页码大于本页面所显示的最大页码时,之后的只显示... -->
          	    	<a href="DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${dp.nowpage +2}" name="nowpage">...</a>
        		</c:if>
          			<!--  当前页大与3页 -->	
        		<c:if test="${dp.nowpage >=3}">
          			<!--  当前页大与3页，起始页码为当前页-2，，结束页码为当前页+2 -->
          	<c:forEach begin="${dp.nowpage-2 }" end="${dp.nowpage+2 }" step="1" varStatus="n">
         		<c:if test="${dp.nowpage !=n.index}">
         			<a href="DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${n.index }" name="nowpage">${n.index }</a>
          		</c:if>
          		<c:if test="${dp.nowpage ==n.index}">
          				<!-- 当前页是为红色，不可点击 -->
          			<span class="current" name="nowpage">${dp.nowpage }</span>
          		</c:if>
          	</c:forEach>
          			<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${dp.nowpage +2}'>...</a>
        </c:if>
          			
      </c:if>
          		<!--  当前页大于等于总页数-4 -->
        <c:if test="${dp.nowpage >=pageMax-4}">
          	<c:if test="${dp.nowpage<pageMax-2 }">
          		<c:forEach begin="${dp.nowpage-1 }" end="${dp.nowpage+2 }" step="1" varStatus="n">
	         		<c:if test="${dp.nowpage !=n.index}"> 
	         			<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${n.index }'>${n.index }</a>
	         		</c:if>
	          		<c:if test="${dp.nowpage ==n.index}"> 
	          				<!-- 当前页是为红色，不可点击 -->
	          			<span class='current'>${dp.nowpage }</span>
	          		</c:if>
          		</c:forEach>
          				<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${dp.nowpage +2}'>...</a>
          	</c:if>
          	<c:if test="${dp.nowpage>=pageMax-2 }">
          			<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${dp.nowpage -2}'>...</a>
          		<c:forEach begin="${pageMax-4 }" end="${pageMax }" step="1" varStatus="n">
	         		<c:if test="${dp.nowpage !=n.index}"> 
	         			<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${n.index }'>${n.index }</a>
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
          				<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${n.index }'>${n.index }</a>
          			</c:if>
          			<!-- 当前页是为红色，不可点击 -->
          			<c:if test="${dp.nowpage ==n.index}">
          				<span class='current'>${dp.nowpage }</span>
          			</c:if>
          		</c:forEach>
          </c:if>
      
          <!-- 当前页码为最后一页的显示情况 -->
          <c:if test="${dp.nowpage == pageMax }">下一页</c:if>
          <c:if test="${dp.nowpage != pageMax }">
          	<a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${dp.nowpage +1}' >下一页</a> 
          </c:if>
          <c:if test="${dp.nowpage == pageMax }">尾页</c:if>
          <c:if test="${dp.nowpage != pageMax }">
          <a href='DrugPeopleServlet.do?method=lookDrug&bid=${id }&nowpage=${pageMax }' >尾页</a>
          </c:if>
		   </c:if>
          &nbsp;&nbsp;&nbsp;共<span class='current'>${dp.count }</span>条记录<span class='current'> ${dp.nowpage }/${pageMax } </span>页
		  </div>
		 <div>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
