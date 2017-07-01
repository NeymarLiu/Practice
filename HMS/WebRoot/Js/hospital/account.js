$(function() {
	$('#newNav').click(function() {
		window.location.href = "dispensing-gives.html";
	});
});
function bing(){
	var b=true;
	var bid = document.getElementsByName("id")[0].value;
	if(bid != null && bid != ""){
		var regex=/\s+/g;
		bid=bid.replace(regex,"");
		if(isNaN(bid)){
			//不是数字
			alert("病例号查询，请输入数字！");
			b=false;
		}
	}
	return b;
}
function checkall() {
	var alls = document.getElementsByName("check");
	var ch = document.getElementById("checkall");
	if (ch.checked) {
		for (var i = 0; i < alls.length; i++) {
			alls[i].checked = true;
		}
	} else {
		for (var i = 0; i < alls.length; i++) {
			alls[i].checked = false;
		}
	}
}
function delAll() {
	var alls = document.getElementsByName("check");
	var ids = new Array();
	for (var i = 0; i < alls.length; i++) {
		if (alls[i].checked) {
			ids.push(alls[i].value);
		}
	}
	if (ids.length > 0) {
		if (confirm("确认操作?")) {
			alert("成功!");
		}
	} else {
		alert("请选中要操作的项");
	}
}

function qingkong() {
	location.href = "AccountServlet.do?type=mohu";
}
function test2() {
	var js = document.getElementById("js").value;
	if (js != null && js != "") {
		if (js) {
			alert("结算成功");
		} else {
			alert("结算失败,可能余额不足！");
		}
	}
}
function daoChu(){
	var id = "";
	var count=0;
	var ids = document.getElementsByName("check");
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
			location.href="AccountServlet.do?type=daoChuExcel&id="+id;
		}
	}else{
		alert("请选择操作项？");
	}
}

function xinxi(obj){
   	location.href="AccountServlet.do?type=xinxi&&myid="+obj;
   }
   
   function jiesuan(obj){
   	if(confirm("确认结算吗？")){
   		location.href="AccountServlet.do?type=jiesuan&&cid="+obj;
   	}
   }