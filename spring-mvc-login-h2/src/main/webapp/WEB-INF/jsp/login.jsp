<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Spring Security Login</title>
</head>
<body class="security-app">

	<form action="/login" method="post">
		<div class="login">
			<div class="login-table">
				<div class="row">
					<div class="cell">
						<label>Username</label>
					</div>
					<div class="cell">
						<input type="text" name="username" placeholder="User Name" />
					</div>
				</div>
				<div class="row">
					<div class="cell">
						<label>Password</label>
					</div>
					<div class="cell">
						<input type="password" name="password" placeholder="Password" />
					</div>
				</div>
				<div class="row">
					<div class="cell">
						<input type="submit" value="Sign In"/>
					</div>
				</div>
			</div>
			<c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid username and password.</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div class="alert-normal">You have been logged out.</div>
			</c:if>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
	</form>
</body>
</html>
