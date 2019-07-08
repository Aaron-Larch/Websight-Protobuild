<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="Modal.jsp" />
<title>Print Statement loop</title>
</head>
<body>
<div id="divValue" style="display: none;">${Name}</div>
	<h1 id="tital1">The Record created for the ${Name} file</h1>
    	<textarea id="message" rows="10" cols="150">${Message}</textarea>
    	<br>
	<div class = "Results">
		<div id="modal-isi-body"></div>
    	<p id="tital2">Do you wish to further analyze ${Name}:</p>
    	
    	<!-- Trigger for object Switch -->
  		<button id="BuildRecord" onclick="SwichLoadout()">yes</button>

  		<!-- Trigger the modal with a button -->
  		<button id="LoadFile" onclick="loadModal('0')">No</button>
	</div>

    <div Class="setInputs">
    <p id="tital3">Witch Array do you wish continue to use Next?:</p>
  	<form action="BuildRecord" id="Servlet" method="get">
  		<select id="arrayList" name="choice"></select>
    	<input type="submit" value="Submit"/>
	</form>
    </div>
   	   
   <script>	
	[].forEach.call(document.querySelectorAll(".setInputs"), function (i) {
	  	i.style.display="none";});

	
	function SwichLoadout(){
		[].forEach.call(document.querySelectorAll(".Results"), function (i) {
				 i.style.display="none";});
		[].forEach.call(document.querySelectorAll(".setInputs"), function (i) {
				 i.style.display="block";});
	}
	
	function loadModal(i){
		$(document).ready(function(){
    		$("#myModal").modal().on('shown.bs.modal', function (e) {setValues(i);});
    		load_page('/WEB-INF/Modal.jsp #container');
		});
		function load_page(url){$('modal-isi-body').load(url) ;}
	}
	
	(function() { // don't leak
		var sel = document.getElementById('arrayList'), // get the select
			fragment = document.createDocumentFragment(), // create a document fragment to hold the options while we create them
			Name = document.getElementById("divValue").innerHTML;
		for (var i = 0; i < ${Size}; i++) {
			var option = document.createElement('option'); // create the option element
			option.value = i; // set the value property
			option.appendChild(document.createTextNode(Name+"-" + i)); // set the textContent in a safe way.
			fragment.appendChild(option); // append the option to the document fragment
		}
		sel.appendChild(fragment); // append the document fragment to the DOM.
	}());
	</script>
</body>
</html>