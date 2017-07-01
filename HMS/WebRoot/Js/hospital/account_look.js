$(function() {
	$('#newNav').click(function() {
		window.location.href = "hospital/add.jsp";
	});
});

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
$(function() {
	$('#backid').click(function() {
		window.location.href = "AccountServlet.do?type=mohu";
	});
});
function daoChu() {
	var obj = document.getElementById("myid").value;
	var pid = document.getElementById("ex").value;
	if(pid>0){
		if (confirm("你确定要导出吗？")) {
			location.href = "AccountServlet.do?type=daoChuExcel1&id=" + obj;
		}
	}else{
		alert("没有要导出的数据！");
	}
	
}