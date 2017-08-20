<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
<link href="<spring:url value="css/app.css" />" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Hello world!</h1>
	<form action="send">
		<input type="text" name="id" /> <input type="submit" />
	</form>
</body>
</html>
