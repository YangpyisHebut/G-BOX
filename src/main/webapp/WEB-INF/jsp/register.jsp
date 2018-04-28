<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">   
<form action="register_handle" method="post" enctype="multipart/form-data">

	用户名：<input type="text" name="name"><br/>
	手机号码：<input type="text" name="phonenumber"> <br/>
	密码：<input type="password" name="password"><br/>
	头像：<input type="file" name="file"><br/>
	<input type="submit" value="注册">
</form>