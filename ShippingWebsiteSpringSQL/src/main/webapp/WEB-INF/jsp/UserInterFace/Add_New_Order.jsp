<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Create new Article Form</title>
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
  <!-- Call server for User information -->
  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2 style="text-align:right; float:right;">Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    <hr style="background-color:white;"/>
</div>

<!--User Input class For all fields of the table object-->
 <div class="container">
  <spring:url value="/Shipping/tableUpdate" var="addURL" />
  <h2>Article</h2>
  <form:form modelAttribute="order" method="post" action="${addURL }" cssClass="form" >
   
   <!-- Fixed Id should not allow user interaction -->
   <form:hidden path="ORDERID" cssClass="form-control" id="orderId" Value="${OrderID}"/>
   <div class="form-group">
   
   <!-- Fixed list of Values needs drop down menu -->
    <label>Customer Id</label>
    <form:input path="CUSTOMERID" cssClass="form-control" id="customerId"/>
   </div>
   <div class="form-group">
    <label>Employee Id</label>
    <form:input path="EMPLOYEEID" cssClass="form-control" id="employeeId"/>
   </div>
   <div class="form-group">
    <label>Ship Via</label>
    <form:input path="SHIPVIA" cssClass="form-control" id="shipVia"/>
   </div>
   <div class="form-group">
    <label>Freight</label>
    <form:input path="FREIGHT" cssClass="form-control" id="freight"/>
   </div>
   <div class="form-group">
    <label>Ship Name</label>
    <form:input path="SHIPNAME" cssClass="form-control" id="shipName"/>
   </div>
   <div class="form-group">
   
   <!-- Fixed list of Values needs drop down menu -->
    <label>Ship Country</label>
    <form:input path="SHIPCOUNTRY" cssClass="form-control" id="shipCountry"/>
   </div>
   <button type="submit" class="btn btn-primary">Save</button>
  </form:form>
 </div>
</body>
</html>