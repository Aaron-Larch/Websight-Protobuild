<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <jsp:include page="../Analitics/ModelLibrary.jsp" />
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>User Home Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style><%@include file="../../resources/css/common.css"%></style>
  <script><%@include file="../../resources/js/reportfunctions.js"%></script>
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
    <hr style="background-color:white;"/>
</div>

<div>
<main>
<!-- Fixed list of country names to sort the database by -->
 <select style="display: ${role};" name="category" class="form-control" onChange="window.location.href=this.value">
    <option value="none">Choose A region</option>
    <c:forEach items="${listCategory}" var="category">
    	<spring:url value="/Shipping/Europe/${category}" var="PageSelectURL" />
        <option value="${PageSelectURL}">${category}</option>
    </c:forEach>
</select>
 <!-- Table of all available information as well as a delete and update options  -->
  <table class="table table-striped" id="Product Table">
   <thead>
    <th scope="row">#ID</th>
    <th scope="row">CUSTOMERID</th>
    <th scope="row">EMPLOYEEID</th>
	<th scope="row">PRODUCT CODE</th>
    <th scope="row">PROFET</th>
    <th scope="row">PRODUCT NAME</th>
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
      <!-- Button for changing data in a row  -->
       <spring:url value="/Shipping/SingleObject/${article.ORDERID }" var="updateURL" />
       <a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>
      </td>
      <td>
      <!-- Button for removing data in a row  -->
       <spring:url value="/Shipping/SpainDelete/${article.ORDERID }" var="deleteURL" />
       <a class="btn btn-primary" href="${deleteURL }" role="button" onclick="myFunction()">Delete</a>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  </main>
  
  <aside>
  <section>
	<h4>Shipping History</h4>
	<p>Here you can Track, Manage, Or create a new shipping order as well as view your order history</p>
</section>

<section>
	<!-- Button for adding a new row  -->
  <spring:url value="/Shipping/addneworder" var="addURL" />
  <a class="btn btn-primary" href="${addURL }" role="button" >Add New Article</a>
  
  <!-- button to launch Create reports modal  -->
  <div id="modal-isi-body"></div>
  <button  style="display: ${role};" type="button" class="btn btn-primary" onclick="loadModal('page1')">Create Report</button>
  </section>
  </aside>
 </div>
 
<footer>
	<hr style="background-color:black;"/>
	Copyright &copy; 2020. All rights reserved
</footer>
</body>
</html>