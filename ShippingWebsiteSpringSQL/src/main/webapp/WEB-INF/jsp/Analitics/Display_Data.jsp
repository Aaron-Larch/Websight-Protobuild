<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>Data Overview</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style><%@include file="../../resources/css/common.css"%></style>
</head>
<body>
 <!-- A stylish Header that contains all futuer user options -->
    <div class="headder">
  <div style="text-align:left;float:left;">
  <h2>JBA Shipping Inc.</h2>
  <p class="c" >We Deliver the Best to Deliver You Success</p>
  </div>
  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2 style="text-align:right; float:right;">Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    <hr style="clear:both;"/>
</div>
<!-- Hidden load value To dictate the form the page will take -->
<div id="divLoad" style="display: none;">${Page}</div>

<!-- Basic confirmation message to show the user the data collected by the sorted categories -->
<div class= "display">
	</main>
	<!-- Main print statemnt to see all of the collected Data -->
		<h1 id="tital1">Data created for the ${Name} file:</h1>
		<textarea id="message" rows="22" cols="135" readonly>${Message}</textarea>
	</main>

	<aside>
		<!-- Secondary collum intrutions -->
  		<p id="tital2">Data associated with set ${Name} ready for statistical calculations.</p>
		<p id="tital3" style= "float: left;"> To generate statistical data with ${Name}, click 
		
		<!-- Trigger for object Switch -->
  		<a class="astext Calculate" id="BuildRecord" onclick="SwichLoadout(1)" role="buttona">Calculate</a>.</p>
	
		<p id="tital4" style= "float: left;">Otherwise, obtain data under a different name by clicking 
  		
  		<!-- Go Back to the previous controller with a button -->
  		<spring:url value="/Shipping/manager" var="updateURL" />
  		<a class="astext Restart" id="LoadFile" href="${updateURL }" role="button" >Restart</a>.
  		</p>
  	</aside>
</div>

<!-- A user select statement to create custom objects for the end user report -->
<div class = "Populate">
 	<div id="arrayDisply">
 	<p id="intro">what operation do you wish to perform on array ${Name}-${id}</p>
	<textarea id="CurrentArray" rows="3" cols="150" readonly>${Arrays.toString(Information)}</textarea>
	</div>
	<spring:url value="/Stats/BuildRecord/${id}" var="BuildRecordURL" />
	<form:form modelAttribute="operation" id="Servlet" method="post" action="${BuildRecordURL }">
    	<fieldset>
    		<legend>Please select which statistical operations you want displayed:
    		<span class=legend2>Then click 
    		<input class="astext Calculate" type="submit" value="Submit" id="CheckTest" disabled="disabled" >.</span>
    		</legend> <!-- Add a Disable function to prevent a null exception for 0 inputs -->
    		<input type="checkbox" name="operation" id="srth" value="SortHi" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="srth">Sort Highest to Lowest</label><br />
    		<input type="checkbox" name="operation" id="srtl" value="SortLo" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="srtl">Sort Lowest to Highest</label><br />
    		<input type="checkbox" name="operation" id="ave" value="Average" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="ave">Find the Average</label><br />
    		<input type="checkbox" name="operation" id="mid" value="Median" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="mid">Find the Median</label><br />
    		<input type="checkbox" name="operation" id="mode" value="Mode" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="mode">Find the Mode</label><br />
    		<input type="checkbox" name="operation" id="min" value="Min" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="min">Find the Lowest value</label><br />
    		<input type="checkbox" name="operation" id="max" value="Max" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="max">Find the Highest value</label><br />
    		<input type="checkbox" onclick="checkAll(this)" onchange="document.getElementById('CheckTest').disabled = !this.checked;"/>
    		<label for="all">Select All</label><br />
		<br>
		</fieldset>
	</form:form>
		<button id="placeholder" style="display: none" onclick="SwichLoadout(2)">Back</button>
</div>	
<script>
/*Build page on load function*/
window.onload = function () {loadValues(document.getElementById("divLoad").innerHTML);}

/*Display a combination of clases based of a given variabule*/
function loadValues(flag){
	if(flag=="page1"){
		[].forEach.call(document.querySelectorAll(".Populate"), function (i) {i.style.display="none";});
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(".display"), function (i) {i.style.display="none";});
		}
}

/*Switch states button*/
function SwichLoadout(a){
	if(a==1){
		[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
		  	i.style.display="block";});
		[].forEach.call(document.querySelectorAll(".display"), function (i) {
		  	i.style.display="none";});
		}else{
			[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
		  		i.style.display="none";});
			[].forEach.call(document.querySelectorAll('.display'), function (i) {
		  		i.style.display="block";});
		}
	}

/*Check all function*/
function checkAll(source) {
	  checkboxes = document.getElementsByName('operation');
	  for(var i=0; i<checkboxes.length; i++) {
	    checkboxes[i].checked = source.checked;
	  }
	}
</script>
</body>
</html>