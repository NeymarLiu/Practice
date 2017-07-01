$(function() {
	$('#newNav').click(function() {
		window.location.href = "hospital/add.jsp";
	});
});
//详情
function lookOneInfos(obj){
	location.href = "BeHospitalServlet.do?type=lookOneInfos&id="+obj;
}
//更改
function lookEditInfos(obj){
	location.href = "BeHospitalServlet.do?type=lookEditInfos&id="+obj;
}
//缴纳押金
function lookOneMany(obj){
	location.href = "BeHospitalServlet.do?type=lookOneMany&id="+obj;
}

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
// 全选
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
// 批量处理出院，退院
function delAll(obj) {
	var alls = document.getElementsByName("check");
	var sta = document.getElementsByName("state");
	var temp = 0;
	var ids = new Array();
	for (var i = 0; i < alls.length; i++) {
		if (alls[i].checked) {
			temp = temp + 1;
			if (sta[i].value == 2) {

				ids.push(alls[i].value);
			}
		}
	}
	if (temp > 0) {
		if (ids.length == temp) {
			if (obj == 1) {
				// 出院
				if (confirm("确认出院吗?")) {
					location.href = "BeHospitalServlet.do?type=chuyuan&ids="
							+ ids;
				}
			} else if (obj == 2) {
				// 退院
				if (confirm("确认退院吗?")) {
					location.href = "BeHospitalServlet.do?type=tuiyuan&ids="
							+ ids;
				}
			}
		} else {
			alert("选择不符合要求");
		}
	} else {
		alert("请选中要操作的项");
	}
}
// 单个处理退院
function tui(obj) {
	if (confirm("确认退院吗?")) {
		location.href = "BeHospitalServlet.do?type=tuiy&mid=" + obj;
	}
}
// 单个处理出院
function chu(obj) {
	if (confirm("确认出院吗?")) {
		location.href = "BeHospitalServlet.do?type=chuy&mid=" + obj;
	}
}
// 成功够在页面的反馈
function test1() {
	var chu = document.getElementById("chu").value;
	var tui = document.getElementById("tui").value;
	if (chu != "" && chu != null) {
		if (chu) {
			alert("出院成功");
		} else {
			alert("出院失败");
		}
	}
	if (tui != "" && tui != null) {
		if (tui) {
			alert("退院成功");
		} else {
			alert("退院失败");
		}
	}
	var tianjia = document.getElementById("tianjia").value;
	if (tianjia != "" && tianjia != null) {
		if (tianjia) {
			alert("添加成功");
		} else {
			alert("添加失败");
		}
	}
	var is = document.getElementById("isok").value;
	if (is != null && is != "") {
		if (is) {
			alert("修改成功");
		}
		if (!is) {
			alert("修改不成功");
		}
	}
}
// 清空
function qingkong() {
	location.href = "BeHospitalServlet.do?type=mohu";
}
// 导出excel
function daoChu() {
	var id = "";
	var count = 0;
	var ids = document.getElementsByName("check");
	for (var i = 0; i < ids.length; i++) {
		if (ids[i].checked == true) {
			if (id == "") {
				id = ids[i].value + ",";
			} else {
				id = id + ids[i].value + ",";
			}
			count++;
		}
	}
	if (count > 0) {
		if (confirm("你确定要导出吗？")) {
			location.href = "BeHospitalServlet.do?type=daoChuExcel1&id=" + id;
			// window.location.href="DaoChuExcel?type=daoChuExcel&id="+id+"&time="+new
			// Date().getTime());
		}
	} else {
		alert("请选择操作项？");
	}
}

ff=0;
function chackTime() {
	b = false;
	if(ff>0){
		var q = document.getElementsByName("qtime")[0].value;
		var h = document.getElementsByName("htime")[0].value;
		var tt=Date.parse(new Date());
		if (q != "" && q != null && h != null && h != "") {
			if (Date.parse(q) <= Date.parse(h)) {
				if(Date.parse(h)<=tt){
					b = true;
				}else{
					alert("时间超出，请重新输入");
				}
			}
		}
	}
	
	if ((q == null || q == "") && (h == null || h == "")) {
		b = true;
	}
	ff+=1;
}
function piker1(){
	ff=0;
	WdatePicker();
}
flag=0;
function qqq(){
	b1=true;
	if(flag>0){
		var q = document.getElementsByName("qtime")[0].value;
		var tt=Date.parse(new Date());
		if(Date.parse(q)>tt){
			b1=false;
			alert("时间超出，请重新输入");
		}
	}
	
	flag+=1;
}
function piker(){
	flag=0;
	WdatePicker();
}
function shijian() {
	var b2 = true;
	var b3=bing();
	if (!b || !b1) {
		alert("时间有问题,请重新填写");
		b2=false;
	}
	if(!b3){
		b2=false;
	}
	return b2;
}

//得到有格式是的时间
Date.prototype.Format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

function time11(){

	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd hh:mm:ss");
} 