<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>网上书店后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link rel="stylesheet" type="text/css" href="css/admin_menu.css"/>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/jquery.SuperSlide.2.1.1.js"></script>
    <script src="js/admin_function.js"></script>
    <script src="js/calendar.js"></script>
    
    <style>
    	.admin_useradd td .error{
    		display:inline-block;
    		border:1px solid #ff855a;
    		background-color:#ffe8e0;
    		line-height:25px;
    		padding:0px 20px;
    		margin-left:20px;
    		
    	}
    </style>
    
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="/bookshop/manage/admin_index.jsp" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="/bookshop/manage/admin_index.jsp">首页</a></li>
                <li><a href="/bookshop/index.jsp" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="/bookshop/manage/admin_logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="/bookshop/manage/admin_douserselect"><i class="icon-font">&#xe008;</i>用户管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>