<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/jquery.SuperSlide.2.1.1.js"></script>
    <script src="js/calendar.js"></script>
    <script src="js/function.js"></script>
    
    <style>
    	.reg p .error{
    		display:inline-block;
    		border:1px solid #ff855a;
    		background-color:#ffe8e0;
    		line-height:25px;
    		padding:0px 20px;
    		margin-left:20px;
    		
    	}
    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">		<!-- 用户没有输入用户名这些选项时告知用户没有输入对应选项，当用户点击对应选项框之后警示要消失 -->
    <form action="register" method="post" onsubmit="return checkForm(this)">
        <p><h1 style="padding:0px;width:530px;height:60px;magin:0px;font-size:30px;background:#3344AA;text-align:center;line-height:60px;color:#FFFFFF">用户注册</h1></p>
        <p><input type="text" name="ID" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span></span></p>
        <p><input type="text" name="NAME" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入姓名"><span></span></p>
        <p><input type="text" name="PASSWORD" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span></span></p>
        <p><input type="text" name="REPASSWORD" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="确认密码"><span></span></p>
        <p>				<!-- onfocus是鼠标放在框内则不显示提示框，onblur则是鼠标点击其他区域后再做相应检查 -->
        	<input style="width:15px;height:15px;" type="radio" name="SEX" value="T" checked="checked">男
        	<input style="width:15px;height:15px;" type="radio" name="SEX" value="F" >女
        	</p>
        <p><input type="text" name="BIRTHDAY" value="" onfocus="c.show(this)" placeholder="请输入出生日期"><span></span></p>
        <p><input type="text" name="EMAIL" value="" placeholder="请输入邮箱"><span></span></p>
        <p><input type="text" name="MOBILE" value="" placeholder="请输入手机号码"><span></span></p>
        <p><input type="text" name="ADDRESS" value="" placeholder="请输入送货地址"><span></span></p>
        <p><input class="code" type="text" name="veryCode" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码">
        <img height="30" src="getCode" alt="看不清，换一张" onclick="change(this)"><span></span></p>
        <p><input type="submit" name="" value="注册"></p>
        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>