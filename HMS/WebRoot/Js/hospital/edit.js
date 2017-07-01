function getXhr() {
	var xhr;
	try {
		xhr = new XMLHttpRequest();
	} catch (e) {
		try {
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			try {
				xhr = new ActiveXObject("Msxm12.XMLHTTP");
			} catch (e) {
				alert("你的浏览器不支持ajax，请跟换浏览器，推荐火狐。");
			}
		}
	}
	return xhr;
}

function panduan2() {
	// 创建XMLHttpReqyest对象
	var sp2 = document.getElementById("sp2");
	var xa = document.getElementsByName("eid")[0].value;
	var pa = document.getElementsByName("patbed")[0].value;
	if (pa == null || pa == "" || pa == "null") {
		sp2.innerHTML = "<span style='color:red'>床位号不能为空</span>";
		isok = false;
	} else {
		var xhr = getXhr();
		var isok = true;
		// 打开链接
		xhr.open("post", "BeHospitalServlet.do?type=cwh&cid=" + xa + "&pbed="
				+ pa + "&time=" + new Date().getTime(), false);
		// 设置post的提交方式头
		// xhr.setRequestHeader("Content-type",
		// "application/x-ww-form-urlencoded");
		// 回调函数
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				// 四个状态值jax
				var result = xhr.responseText;
				if (result == "true") {
					sp2.innerHTML = "<span style='color:red'>床位号已存在！</span>";
					isok = false;
				} else {
					sp2.innerHTML = "<span style='color:green'>正确</span>";
				}
			}

		};
		// 发送
		xhr.send(null);
	}
	return isok;
}
function panduan1() {
	var nu = document.getElementsByName("nursepeople")[0].value;
	var isok = true;
	var sp = document.getElementById("sp");
	if (nu == null || nu == "" || nu == "null") {
		sp.innerHTML = "<span id='sp' style='color:red'>护理人不能为空</span>";
		isok = false;
	} else {
		sp.innerHTML = "<span id='sp' style='color:green'>正确</span>";
	}
	return isok;
}
function test() {
	var isok = false;
	var a = panduan1();
	var b = panduan2();
	if (a && b) {
		isok = true;
	}
	return isok;
}
function test2(){
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
$(function() {
	$('#backid').click(function() {
		window.location.href = "BeHospitalServlet.do?type=mohu";
	});
});