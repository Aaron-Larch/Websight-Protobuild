<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
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
		<spring:url value="/TestPage" var="invoice" />
		<form:form ModelAttribute="testVal" method="post" action="${invoice}" id="my_form">
		<label>Amount Purchased</label>
		<input type="text" name="testVal" form="my_form" />
		<button type="submit" class="btn btn-primary" form="my_form">Save</button> 
		</form:form>
		
		
<div class="page">
    <header>
      <h1>LOLCAT CLOCK</h1>
    </header>

    <h4>
      I CAN HAZ TIME?? <br>
      <span id="clock">Clock goes here</span>
    </h4>

    <img id="lolcatImage" src="https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/08/normalTime.jpg" alt="lolcat">

    <blockquote id="timeEvent">This is where time events are reported</blockquote>


    set Wake Up Time
    <select id="wakeUpTimeSelector">
      <option value="1">1 AM - 2AM</option>
      <option value="2">2 AM - 3AM</option>
      <option value="3">3 AM - 4AM</option>
      <option value="4">4 AM - 5AM</option>
      <option value="5">5 AM - 6AM</option>
      <option value="6">6 AM - 7AM</option>
      <option value="7">7 AM - 8AM</option>
      <option value="8" selected>8 AM - 9AM</option>
      <option value="9">9 AM - 10AM</option>
      <option value="10">10 AM - 11AM</option>
      <option value="11">11 AM - 12PM</option>
      <option value="12">12 PM - 1PM</option>
      <option value="13">1 PM - 2PM</option>
      <option value="14">2 PM - 3PM</option>
      <option value="15">3 PM - 4PM</option>
      <option value="16">4 PM - 5PM</option>
      <option value="17">5 PM - 6PM</option>
      <option value="18">6 PM - 7PM</option>
      <option value="19">7 PM - 8PM</option>
      <option value="20">8 PM - 9PM</option>
      <option value="21">9 PM - 10PM</option>
      <option value="22">10 PM - 11PM</option>
      <option value="23">11 PM - 12AM</option>
      <option value="24">12 AM - 1AM</option>
    </select>
    <br>
    set Lunch Time
      <select id="lunchTimeSelector">
        <option value="1">1 AM - 2AM</option>
        <option value="2">2 AM - 3AM</option>
        <option value="3">3 AM - 4AM</option>
        <option value="4">4 AM - 5AM</option>
        <option value="5">5 AM - 6AM</option>
        <option value="6">6 AM - 7AM</option>
        <option value="7">7 AM - 8AM</option>
        <option value="8">8 AM - 9AM</option>
        <option value="9">9 AM - 10AM</option>
        <option value="10">10 AM - 11AM</option>
        <option value="11">11 AM - 12PM</option>
        <option value="12" selected>12 PM - 1PM</option>
        <option value="13">1 PM - 2PM</option>
        <option value="14">2 PM - 3PM</option>
        <option value="15">3 PM - 4PM</option>
        <option value="16">4 PM - 5PM</option>
        <option value="17">5 PM - 6PM</option>
        <option value="18">6 PM - 7PM</option>
        <option value="19">7 PM - 8PM</option>
        <option value="20">8 PM - 9PM</option>
        <option value="21">9 PM - 10PM</option>
        <option value="22">10 PM - 11PM</option>
        <option value="23">11 PM - 12AM</option>
        <option value="24">12 AM - 1AM</option>
      </select>
    <br>
    set Nap Time
    <select id="napTimeSelector">
      <option value="1">1 AM - 2AM</option>
      <option value="2">2 AM - 3AM</option>
      <option value="3">3 AM - 4AM</option>
      <option value="4">4 AM - 5AM</option>
      <option value="5">5 AM - 6AM</option>
      <option value="6">6 AM - 7AM</option>
      <option value="7">7 AM - 8AM</option>
      <option value="8">8 AM - 9AM</option>
      <option value="9">9 AM - 10AM</option>
      <option value="10">10 AM - 11AM</option>
      <option value="11">11 AM - 12PM</option>
      <option value="12">12 PM - 1PM</option>
      <option value="13">1 PM - 2PM</option>
      <option value="14">2 PM - 3PM</option>
      <option value="15" selected>3 PM - 4PM</option>
      <option value="16">4 PM - 5PM</option>
      <option value="17">5 PM - 6PM</option>
      <option value="18">6 PM - 7PM</option>
      <option value="19">7 PM - 8PM</option>
      <option value="20">8 PM - 9PM</option>
      <option value="21">9 PM - 10PM</option>
      <option value="22">10 PM - 11PM</option>
      <option value="23">11 PM - 12AM</option>
      <option value="24">12 AM - 1AM</option>
      </select>
    <br>
    <button id="partyTimeButton">Party!</button>

    <footer>
      <p>&copy; Skillcrush 2016</p>
    </footer>
  </div>
  <script>
  <%@include file="../resources/js/SystemClock.js"%>
	$(document).ready(function(){
		renderChart(${Arrays.toString(Data)});
	});
  </script>
  
</body>
</html>
