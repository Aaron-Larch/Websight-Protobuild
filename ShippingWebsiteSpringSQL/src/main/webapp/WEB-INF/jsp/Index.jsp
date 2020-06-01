<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Hello World!</title>
	<script><%@include file="../resources/js/reportfunctions.js"%></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body>
    <h2 class="hello-title">This is a home page to launch the project proper</h2>
    <p>Insert into here a fancy page to sell the compony and what it dose</p>
    <div id="Placeholder">${test}</div>
   	<div id="display2"></div>
   	<div id="stage"></div>
    <canvas class="item" id="chartContainer"  style="height: 300px; width: 100%;"></canvas>
    
    <spring:url value="${contextPath}/login" var="StartURL" />
  	<a id="LoadFile" href="${StartURL }" >Login</a>.
  <script>
	window.onload = function () {renderChart(${Arrays.toString(Data)})}
  </script>
  
</body>
</html>
