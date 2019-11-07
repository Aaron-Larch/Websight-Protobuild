<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <jsp:include page="Analitics/ModelLibrary.jsp" />
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Article List</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

 <div class="container">
 <select name="category" onChange="window.location.href=this.value">
    <option value="none">Choose A region</option>
    <c:forEach items="${listCategory}" var="category">
    	<spring:url value="/Shipping/${category}" var="PageSelectURL" />
        <option value="${PageSelectURL}">${category}</option>
    </c:forEach>
</select>
 </div>
 
 <div class="container">
  <h2>Article List</h2>
  <table class="table table-striped" id="Product Table">
   <thead>
    <th scope="row">#ID</th>
    <th scope="row">CUSTOMERID</th>
    <th scope="row">EMPLOYEEID</th>
	<th scope="row">SHIPVIA</th>
    <th scope="row">FREIGHT</th>
    <th scope="row">SHIPNAME</th>
 	<th scope="row">SHIPCOUNTRY</th>
    <th scope="row">Update</th>
    <th scope="row">Delete</th>
   </thead>
   <tbody>
    <c:forEach items="${ordersList}" var="article" >
     <tr>
      <td>${article.ORDERID}</td>
      <td>${article.CUSTOMERID}</td>
      <td>${article.EMPLOYEEID}</td>
      <td>${article.SHIPVIA}</td>
      <td>${article.FREIGHT}</td>
      <td>${article.SHIPNAME}</td>
      <td>${article.SHIPCOUNTRY}</td>

      <td>
       <spring:url value="/Shipping/SingleObject/${article.ORDERID }" var="updateURL" />
       <a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>
      </td>
      <td>
       <spring:url value="/Shipping/SpainDelete/${article.ORDERID }" var="deleteURL" />
       <a class="btn btn-primary" href="${deleteURL }" role="button" onclick="myFunction()">Delete</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <spring:url value="/Shipping/addneworder" var="addURL" />
  <a class="btn btn-primary" href="${addURL }" role="button" >Add New Article</a>
  <div id="modal-isi-body"></div>
  <button type="button" class="btn btn-primary" onclick="loadModal('1')">Open Modal</button>
  
 </div>
 
 <script>
	function myFunction() {alert(${update });}
	// Get the modal
	function loadModal(input){
	$(document).ready(function(){
		$("#AssineParamiters").modal().on('shown.bs.modal', function (e) {loadValues(input)});
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	});
	function load_page(url){$('modal-isi-body').load(url) ;}
}
</script>
</body>
</html>