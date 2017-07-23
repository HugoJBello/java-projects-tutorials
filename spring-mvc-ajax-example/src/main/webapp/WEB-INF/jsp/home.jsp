<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" />"></script>
<link href="<spring:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Welcome</title>
</head>
<body class="app">

	<div>
		<h1>Welcome!</h1>
			Click the button to send the form and recieve the response via ajax:
    </div>
	
	<h2>Ajax request:</h2>
	<form id="callAjax">
        <table>
				<tr>
					<td>Text 1:</td>
					<td><input type=text id="text1"></td>
				</tr>
				<tr>
					<td>Text 2:</td>
					<td><input type=text id="text2"></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">Send Ajax request</button></td>
				</tr>
			</table>
		</form>	
		<h2>Ajax response:</h2>
		        <table>
				<tr>
					<td>Text 1 (response):</td>
					<td><div id="text1_response"></div></td>
				</tr>
				<tr>
					<td>Text 2 (response):</td>
					<td><div id="text2_response"></div></td>
				</tr>
				<tr>
					<td>Date (response):</td>
					<td><div id="date_response"></div></td>
				</tr>
			</table>	
			
<%-- 	       	<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
            <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>	 --%>	
            
<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#callAjax").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		callAjax();

	});
});

function callAjax() {
	/* var token = $('#csrfToken').val();
	var header = $('#csrfHeader').val(); */	
	var data = {}
	data["text1"] = parseInt($("#text1").val());
	data["text2"] = parseInt($("#text2").val());

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "${home}/ajaxEndpoint/",
		data : JSON.stringify(data),
		dataType : 'json',
		/* beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	        xhr.setRequestHeader(header, token);
	    }, */
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function display (data){
 	var text1_response = data.text1;
 	var text2_response = data.text2;
 	var date = data.date;
 
    $("#text1_response").html(text1_response);	
    $("#text2_response").html(text2_response);
    $("#date_response").html(date);
}
</script>
</body>
</html>
