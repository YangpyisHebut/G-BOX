<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">      
<h1>注册成功</h1>
你的id：${id}<br/>
登录账号：${phonenumber}<br/>
名称：${name}<br/>
图片地址：${pictureaddr}<br/>
图片名称：${picturename}<br/>
<script>
function change(){
	var picturename = '${picturename}';
}
change();
</script>
<img src="../picture/${picturename}"><br/>
<input type="submit" value="返回登录" onclick = "window.location.href = 'login'">
