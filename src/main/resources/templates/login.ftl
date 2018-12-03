<!DOCTYPE html>
<#--<#assign cdx = request.contextPath + "/cdn/bar">
<#assign ctx = request.contextPath + "/server">
<#assign cpx = request.contextPath + "/admin">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>租赁管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<link rel="stylesheet" href="../../css/login/login.css" type="text/css"/>
</head>
<script src="../../css/themes/global/js/jquery.min.js"></script>
<script src="../../css/themes/global/js/jquery.min.js"></script>
<script src="../../css/themes/global/js/plugins/md5.js"></script>
<script type="text/javascript" src="../../css/login/login.js"></script>
<body class="login-body">
<form id="loginForm">
	<div class="login-wrap">

		<div class="login-logo tc">
			<span class="heading">租赁管理系统<span>
		</div>
		<div class="login-box">
			<div class="loginFormIpt">
				<b class="ico"></b>
				<input class="inpt" title="请输入登录账户" id="userCode" name="userCode"  type="text"
					   placeholder="请输入登录账户"/>
			</div>
			<div class="loginFormIpt">
				<b class="ico pwd"></b>
				<input class="inpt" title="请输入密码" id="pwd" maxlength="8" type="password" placeholder="请输入密码"
					   autocomplete="off"/>
			</div>
			<div class="btn">
				<a class="login-btn" href="javascript:login()">登录</a>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(function () {
		$(document).keypress(function (e) {
			if (e.keyCode == 13) {
				login();
			}
		});
	});
</script>
</body>
</html>
