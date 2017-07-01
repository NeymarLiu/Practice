<%@page import="com.zrgk.bean.MenuBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>山东省第二人民医院信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear" style="float:left">
            <!--<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">信息采编系统</div></li>-->
			<li style=" color:#fff; font-size:20px; margin-top:10px; margin-left:20px;">山东省第二人民医院管理平台</li>
		</ul>
		<div  style="float:right; color:#fff;">欢迎您，<span class="dl-log-user">${sessionScope.ub.u_truename}</span><a href="javascript:void(0)" onclick="loginOut()" title="退出系统" class="dl-log-quit">[退出]</a></div>
    </div>
	
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.6.js"></script>
<script type="text/javascript" src="assets/js/bui.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>


<script>
	function loginOut(){
		window.location.href="LoginServlet.do?method=loginOut";
	}


    BUI.use('common/main',function(){
        var config = [
		{id:'1',menu:[
		{text:'快速通道',items:[
		<% List<MenuBean> list=(List<MenuBean>)request.getAttribute("list");
			for(int i=0;i<list.size();i++){
			if(i<list.size()-1){%>
				{id:'<%=list.get(i).getM_id()%>',text:'<%=list.get(i).getM_name()%>',href:'<%=list.get(i).getM_url()%>'},
			<%}else{%>
			{id:'<%=list.get(i).getM_id()%>',text:'<%=list.get(i).getM_name()%>',href:'<%=list.get(i).getM_url()%>'}
			<%}}%>
		
		
		]}
		
		]}
		];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
</body>
</html>