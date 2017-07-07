<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!--Head-->
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8" />
<title>广告资源管理平台</title>
<meta name="description" content="login page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/img/favicon.png" type="image/x-icon"> --%>

<!--Basic Styles-->
	<link href="${pageContext.request.contextPath}/plugins/beyond/css/bootstrap.min.css" rel="stylesheet" />
	<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/plugins/beyond/css/font-awesome.min.css" rel="stylesheet" />

	<!--Fonts-->
	<!--     <link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css"> -->

	<!--Beyond styles-->
	<link id="beyond-link" href="${pageContext.request.contextPath}/plugins/beyond/css/beyond.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/plugins/beyond/css/demo.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/plugins/beyond/css/animate.min.css" rel="stylesheet" />
	<link id="skin-link" href="" rel="stylesheet" type="text/css" />

	<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/skins.min.js"></script>
<style type="text/css">
	.error {color:red;}
</style>
</head>
<!--Head Ends-->
<!--Body-->
<body>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div class="login-container animated fadeInDown">
			<div class="loginbox bg-white">
			
				<div class="loginbox-signup"> </div>
				<div class="loginbox-signup"> </div>
				
				<div class="loginbox-title">运营服务平台</div>
				
				<div class="loginbox-signup"> </div>
	            <div class="loginbox-signup"> </div>
	            
				<div class="loginbox-or">
					<div class="or-line"></div>
					<div class="or">登录</div>
				</div>
				
				<div class="loginbox-signup"> </div>
	            <div class="loginbox-signup"> </div>
	            <div class="loginbox-signup error">${errorMessages}</div>
	            <div class="loginbox-signup"> </div>
	            <div class="loginbox-signup"> </div>
	            
				<div class="loginbox-textbox">
					<input type="text" name="username" class="form-control" placeholder="账户" />
				</div>
				
				<div class="loginbox-signup"> </div>
				
				<div class="loginbox-textbox">
					<input type="password" name="password" class="form-control" placeholder="密码" />
				</div>
				
				<div class="loginbox-signup"> </div>
				<div class="loginbox-signup"> </div>
				
				<div class="loginbox-submit">
					<input type="submit" class="btn btn-primary btn-block" value="登录">
				</div>
			</div>
		</div>
	</form>
	<!--Basic Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/jquery-2.0.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/bootstrap.min.js"></script>
</body>
<!--Body Ends-->
</html>
