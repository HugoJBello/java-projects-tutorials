<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #111;
}

.active {
    background-color: #4CAF50;
}
</style>

<title>Spring Security Example</title>
</head>
<body class="security-app">
	<ul>
  <li><a href="/login">Login</a></li>
  <li><a href="/home">Home</a></li>
  <li><a href="/webcam">Webcam</a></li>
  <li><a class="active" href="/webcam/about">About</a></li>
</ul>
	Developed by Hugo J. Bello.
</body>
</html>
