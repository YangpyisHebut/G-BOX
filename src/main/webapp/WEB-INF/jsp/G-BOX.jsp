<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<h1>MAIN</h1>

<img id = 'imgid1' src="images/1.jpg">
<img id = 'imgid2' src="images/2.jpg">
<img id = 'imgid3' src="images/3.jpg">
<img id = 'imgid4' src="images/4.jpg"><br/>
<input type = "button" value = "登录" onclick = "window.location.href = 'login'">
<input type = "button" value = "注册" onclick = "window.location.href = 'register'">

<script>
        //如果图片过大，处理图片大小。使用要求：传递id和大小要求。示例：autoImgSize('img1',1000)
        function  autoImgSize(img,size){
            if(!size){
                size=1000;//设置默认最大值为1000
            }
            var imgobj= document.getElementById(img); //获取对象
            imgobj.style.width = "auto";
            imgobj.style.height = "auto";  //释放图片本来的大小
            var imgwidth=imgobj.width;
            var imgheight=imgobj.height;
             if(imgwidth>imgheight){     //判断是width和height哪一个大。大的先处理。设置最大值size
                 if(imgwidth>size){
                     imgobj.style.width = size + "px";
                 }
                 imgheight=imgobj.height;
                 if(imgheight>size){
 
                     imgobj.style.height = size + "px";
                 }
             }else{
                 if(imgheight>size){
                     imgobj.style.height = size + "px";
                 }
                 imgwidth=imgobj.width;
                 if(imgwidth>size){
                     imgobj.style.width = size + "px";
                 }
             }
            }
            window.onload = function(){
                autoImgSize('imgid1',screen.width/4-10);
                autoImgSize('imgid2',screen.width/4-10);
                autoImgSize('imgid3',screen.width/4-10);
                autoImgSize('imgid4',screen.width/4-10);
            }
 
        </script>