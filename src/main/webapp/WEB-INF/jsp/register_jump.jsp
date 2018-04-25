<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
function display(){
	var check = '${check}';
	//document.write(check);
	
	alert(check);
	
	
	location.assign("/register");
}

display();
</script>
