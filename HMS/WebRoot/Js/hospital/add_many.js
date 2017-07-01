$(function() {
	$('#backid').click(function() {
		window.location.href = "BeHospitalServlet.do?type=mohu";
	});
});

function panduan() {
	var isok = true;
	var money = document.getElementsByName("money")[0].value;
	var sp = document.getElementById("sp");
	if (money != null && money != "" && money != "null") {
		var regex=/\s+/g;
		money=money.replace(regex,"");
		if(money > 0){
			if (!isNaN(money)) {
				// 数字
				sp.innerHTML = "<span style='color:green'>正确</span>";
			} else {
				sp.innerHTML = "<span style='color:red'>不是数字</span>";
				isok = false;
			}
		}else{
			if(money == 0) {
				sp.innerHTML = "<span style='color:red'>押金不能为0</span>";
				isok = false;
			}else{
				sp.innerHTML = "<span style='color:red'>押金不能为负数</span>";
				isok = false;
			}
			
		}
		
	} else {
		sp.innerHTML = "<span style='color:red'>不能为空</span>";
		isok = false;
	}
	return isok;
}
function test() {
	return panduan();
}
function test2() {
	var is = document.getElementById("isok").value;
	if (is != null && is != "") {
		if (is) {
			alert("充值成功");
		}
		if (!is) {
			alert("充值失败");
		}
	}
}