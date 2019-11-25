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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style><%@include file="../../resources/css/common.css"%></style>
</head>
</head>
<body>
<!-- A stylish Header that contains all futuer user options -->
    <div class="headder">
  <div style="text-align:left;float:left;">
  <h2>JBA Shipping Inc.</h2>
  <p class="c" >We Deliver the Best to Deliver You Success</p>
  </div>
  <!-- Call server for User information -->
  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2 style="text-align:right; float:right;">Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    <hr style="clear:both;"/>
</div>

<div class="printResult">
<p>The Results of your selected operations:</p>
<!-- Display The result of the users choices -->
<textarea id="CreatedObject" rows="12" cols="150" readonly>${Message}</textarea>

<p id="UserChoice1">Would you like to crate another object or close the curent working file</p>
<!-- A list of array choices to analyze -->
 <div id="sizecontroll">
 <select name="category"  style="float:left;" class="form-control" onChange="window.location.href=this.value">
    <option value="none">Choose A region</option>
    <c:forEach items="${listCategory}" var="category" varStatus="loop">
    	<spring:url value="/Stats/chosenewdata/${loop.index}" var="DataSelectURL" />
        <option value="${DataSelectURL }">${category}</option>
    </c:forEach>
</select>
 </div>
 
 <!-- button to launch the Save and quit button options modal  -->
<div id="modal-isi-body"></div>
<button  style="float:right;" class="btn btn-primary" id="LoadFile" onclick="loadModal('1')">Close file</button>
</div>

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