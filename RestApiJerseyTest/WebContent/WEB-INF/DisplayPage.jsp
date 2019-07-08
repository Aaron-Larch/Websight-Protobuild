<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="Modal.jsp" />
<title>Create Records</title>
</head>
<body>
<div id="divLoad" style="display: none;">${Page}</div>
<div class= "display">
<div id="modal-isi-body"></div>
<h1 id="tital1">Data created for the ${Name} file</h1>
    <textarea id="message" rows="12" cols="200">${Message}</textarea>
    <br>
    <p id="tital2">Do you wish to further analyze ${Name}:</p>
    <!-- Trigger for object Switch -->
  	<button id="BuildRecord" onclick="SwichLoadout(1)">yes</button>

  	<!-- Trigger the modal with a button -->
  	<button id="LoadFile" onclick="loadModal('1')">No</button>
</div>

<div class = "Populate">
    <p id="intro">what operation do you wish to perform on array ${Name}-</p>
    <div id="Display"></div>
	<form action="BuildRecord" id="Servlet" method="post">
    	<fieldset>
    		<legend>you can perform one or more of the following operations:</legend>
    		<input type="checkbox" name="operation" id="srth" value="SortHi" /><label for="srth">Sort Highest to Lowest</label><br />
    		<input type="checkbox" name="operation" id="srtl" value="SortLo"  /><label for="srtl">Sort Lowest to Highest</label><br />
    		<input type="checkbox" name="operation" id="ave" value="Average" /><label for="ave">Find the Average</label><br />
    		<input type="checkbox" name="operation" id="mid" value="Median" /><label for="mid">Find the Median</label><br />
    		<input type="checkbox" name="operation" id="mode" value="Mode"  /><label for="mode">Find the Mode</label><br />
    		<input type="checkbox" name="operation" id="min" value="Min" /><label for="min">Find the Lowest value</label><br />
    		<input type="checkbox" name="operation" id="max" value="Max" /><label for="max">Find the Highest value</label><br />
    		<input type="checkbox" onclick="checkAll(this)" /><label for="all">Select All</label><br />
		</fieldset>
		<input type="hidden" name="file" value= "${Record}">
		<input type="hidden" name="name" value= "${Name}">
		<input type="hidden" name="reset" value= "${Count}">
		<input type="hidden" id="data" name="data">
		<input type="hidden" id="ArraySize" name="length">
		<input type="hidden" id="ArrayPosition" name="place">
  		<input type="submit" value="Submit">
	</form>
		<button id="placeholder" onclick="SwichLoadout(2)">Back</button>
</div>

	
<script>
document.getElementById("placeholder").style.display = "none";

window.onload = function () {loadValues(document.getElementById("divLoad").innerHTML);}

function SetArray(value){
	document.getElementById("intro").innerHTML += value;
	var obj = ${Arrays.deepToString(Data)};
	var string = JSON.stringify(obj);
	var block = string.substring(1, string.length-1);
	var arr = JSON.parse("[" + block + "]");
	document.getElementById("ArraySize").value=arr.length;
	if(value < arr.length){
		document.getElementById("Display").innerHTML=arr[value];
		document.getElementById("data").value=arr[value];
		document.getElementById("ArrayPosition").value= value;
	}else if(value >= arr.length){
		document.getElementById("Display").innerHTML="This value is not reconized as a Real array";
		document.getElementById("Servlet").style.display = "none";
		document.getElementById("placeholder").style.display = "block";
	}
}

function loadValues(flag){
	var res = flag.split(", ");
	if(res[0]=="page1"){
		[].forEach.call(document.querySelectorAll(".Populate"), function (i) {i.style.display="none";});
		SetArray(res[1]);	
	}else if(res[0]=="page2"){
		[].forEach.call(document.querySelectorAll(".display"), function (i) {i.style.display="none";});
		SetArray(res[1]);
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

function loadModal(i){
		$(document).ready(function(){
    		$("#myModal").modal().on('shown.bs.modal', function (e) {setValues(i);});
    		load_page('/WEB-INF/Modal.jsp #container');
		});
		function load_page(url){$('modal-isi-body').load(url) ;}
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