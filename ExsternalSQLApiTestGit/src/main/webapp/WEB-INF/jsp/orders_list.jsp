<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Article List</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
 <div class="container">
  <h2>Article List</h2>
  <table class="table table-striped">
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
       <spring:url value="/Shipping/SpainSingleObject/${article.ORDERID }" var="updateURL" />
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
 </div>
 <script>
	function myFunction() {alert(${update });}
</script>
</body>
</html>