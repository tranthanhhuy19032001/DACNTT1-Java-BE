<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="loginbox rounded">
		<img src="https://i.imgur.com/ZYzTdD0.png" class="avatar">
		<c:if test="${not empty message }">
			<div class="alert alert-${ alert }" role="alert">
				${ message }
			</div>
		</c:if>
		<h1>Login</h1>
		<form action="<c:url value='/dang-nhap'/>" id="formLogin"
			method="post">
			<p>Username</p>
			<input type="text" id="userName" name="userName"
				placeholder="Enter Username">

			<p>Password</p>
			<input type="password" id="password" name="password"
				placeholder="Enter Password"> <input type="hidden"
				value="login" name="action" />
			<button type="submit" class="btn btn-primary">Đăng nhập</button>

			<br> <a class="forgotPassword" href="/">Forgot password?</a> <br> <a href="/">Don't
				have an account?</a>
		</form>
	</div>
</body>
</html>