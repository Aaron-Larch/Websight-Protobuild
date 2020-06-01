<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Line Chart</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<style><%@include file="../../resources/css/common.css"%></style>
	<script><%@include file="../../resources/js/reportfunctions.js"%></script>
</head>
<body>
<!-- Report Headers Object to tell the viewer which object is being viewed   -->
	 <div id="header" style="text-align:center">
	  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2> ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
	
		<h1>Graphs Generated for the object: ${Label}</h1>
	</div>
		<div id="divName" style="display: none;">${Label}</div>

<!-- main object for charts organization  -->
	<div class="main-display">
		<!--Main report information, Bell curve Chart and Histogram Chart object  -->
		<div class="column">
			<textarea class="item" id="message">${Message}</textarea>
			<canvas class="item" id="BellcurveChart"></canvas>
			<canvas class="item" id="HistogramChart"></canvas>
		</div>
		<!-- High Line Graph, Low Line Graph, Box and Whisker Chart object -->
		<div class="column">
			<canvas class="item" id="HighLineGraph"></canvas>
			<canvas class="item" id="LowLineGraph"></canvas>
			<div class="item" id="BoxandWhisker"></div>
		</div>
	</div>
<script>
	/*Collect all the requierd variabuls*/
	window.onload = function () {buildReport(
		${Arrays.toString(Xaxis)}, 
		${Arrays.toString(BellCurveGraph)}, 
		${Arrays.toString(YaxisHigh)}, 
		${Arrays.toString(YaxisLow)}, 
		${Arrays.toString(BoxAndWhiskersGraph)}, 
		${Arrays.deepToString(BarGraph)}, 
		${Arrays.toString(Histogram)}
	)}
</script>
</body>
<!-- Links to other objects Chart pages -->
<footer2>
	<spring:url value="/Stats/chart" var="ScrollURL" />
	<form id="Servlet" method="GET" action="${ScrollURL }" >
		<button type="submit" id="btn1" name="action" value="-1">Back</button>
		<button type="submit" id="btn3" name="action" value="1">Next</button>
	</form>
	<!-- Save information and close the current project-->
	<form action="FinalPagePrintFile" id="Servlet2" method="get">
		<input type="submit" id="btn2" name="action" value="Print File"/>
	</form>
</footer2>
</html>