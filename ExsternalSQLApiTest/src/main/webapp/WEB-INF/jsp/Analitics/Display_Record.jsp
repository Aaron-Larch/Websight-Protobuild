<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<jsp:include page="ModelLibrary.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Display The Record Created</title>
	<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
	<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
</head>
<body>
<!-- Display The result of the users choices -->
<textarea id="message" rows="12" cols="150" readonly>${Message}</textarea>

<!-- A list of array choices to analyze -->
 <div class="container">
 <select name="category" onChange="window.location.href=this.value">
    <option value="none">Choose A region</option>
    <c:forEach items="${listCategory}" var="category" varStatus="loop">
    	<spring:url value="/Stats/chosenewdata/${loop.index}" var="DataSelectURL" />
        <option value="${DataSelectURL }">${category}</option>
    </c:forEach>
</select>
 </div>
 
 <!-- button to launch the Save and quit button options modal  -->
<div id="modal-isi-body"></div>
<button id="LoadFile" onclick="loadModal('1')">Close file</button>

<script type="text/javascript">
//call modal function
function loadModal(input){
	$(document).ready(function(){
		$("#CreateNewRecord").modal().on('shown.bs.modal', function (e) {loadValues(input)});
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	});
	function load_page(url){$('modal-isi-body').load(url) ;}
}
</script>
</body>
</html>