<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<spring:url value="css/app.css" context="spring-mvc-security-webcam"/>" rel="stylesheet"
	type="text/css">
<title>Welcome</title>
</head>
<body class="security-app">

	<div class="details">
	 
	</div>
	<div class="text_centered">
		<h1>Welcome!</h1>
		<div class="alert-normal">
			Click <a href="<spring:url value='/application' />">here</a> Enter the application.
		</div>
	</div>
</body>
</html>
