<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Article Form</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
 <style>
.astext {
    background:none;
    border:none;
    margin:0;
    padding:0;
    cursor: pointer;
}
.astext:hover {background: #eee;}

.Calculate {color: dodgerblue;}
.Restart {color: orange;}

fieldset { position: relative; padding: 0.35em 0.625em 0.75em; }
.legend2 { position: absolute; bottom: -1.4ex; left: 10px; background: white  }
caption, .legend2 { padding: 0 2px }

</style>
</head>
<body>
<div id="divLoad" style="display: none;">${Page}</div>

<div class= "display">
	<h1 id="tital1">Data created for the ${Name} file</h1>
	<textarea id="message" rows="22" cols="150" readonly>${Message}</textarea>
	<p id="tital2">Data associated with set ${Name} ready for statistical calculations.</p>
	<p id="tital3" style= "float: left;"> To generate statistical data with ${Name}, click </p>
	
	<!-- Trigger for object Switch -->
  	<button class="astext Calculate" id="BuildRecord" style= "float: left;" onclick="SwichLoadout(1)"> Calculate</button> <p>.</p>
	<p id="tital4" style= "float: left;">Otherwise, obtain data under a different name by clicking</p>
  	
  	<!-- Go Back to the previous controller with a button -->
  	<spring:url value="/Shipping/home" var="updateURL" />
  	<a class="astext Restart" id="LoadFile" style= "float: left;" href="${updateURL }" role="button" >Restart</a>
  	<br>
</div>

<div class = "Populate">
 	<p id="intro">what operation do you wish to perform on array ${Name}-${id}</p>
	<textarea id="Display" rows="3" cols="200" readonly>${Arrays.toString(Information)}</textarea>
	<spring:url value="/Stats/BuildRecord/${id}" var="BuildRecordURL" />
	<form:form modelAttribute="operation" id="Servlet" method="post" action="${BuildRecordURL }" cssClass="form" >
    	<fieldset>
    		<legend>Please select which statistical operations you want displayed:
    		<span class=legend2>Then click submit.</span></legend>
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
		<br>
  		<input type="submit" value="Submit" id="CheckTest" disabled="disabled" >
	</form:form>
		<button id="placeholder" onclick="SwichLoadout(2)">Back</button>
</div>	
<script>
window.onload = function () {loadValues(document.getElementById("divLoad").innerHTML);}

function loadValues(flag){
	if(flag=="page1"){
		[].forEach.call(document.querySelectorAll(".Populate"), function (i) {i.style.display="none";});
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(".display"), function (i) {i.style.display="none";});
		}
}

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
	
function checkAll(source) {
	  checkboxes = document.getElementsByName('operation');
	  for(var i=0; i<checkboxes.length; i++) {
	    checkboxes[i].checked = source.checked;
	  }
	}
</script>
</body>
</html>