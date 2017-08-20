<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Hello World!</title>
</head>
<body class="security-app">
	
	<div class="lc-block">


		<form:form method="post" action=""
			modelAttribute="formBean">
			<h3>introduce text here</h3>
			<table>
				<tr>
					<td>text 1:</td>
					<td><form:input type="text" path="text1" /></td>
				</tr>
				<tr>
					<td>text 2:</td>
					<td><form:input type="text" path="text2" /></td>
				</tr>
			</table>
			<p>
				<input type="submit" value="Start" />
			</p>
		</form:form>




	</div>

</body>
</html>
