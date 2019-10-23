<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Create new Article Form</title>
 <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
 <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
 <div class="container">
  <spring:url value="/Shipping/tableUpdate" var="addURL" />
  <h2>Article</h2>
  <form:form modelAttribute="NewOrder" method="post" action="${addURL }" cssClass="form" >
   <form:hidden path="ORDERID" cssClass="form-control" id="orderId" Value="9919719"/>
   <div class="form-group">
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
    <label>Ship Country</label>
    <form:input path="SHIPCOUNTRY" cssClass="form-control" id="shipCountry"/>
   </div>
   <button type="submit" class="btn btn-primary">Save</button>
  </form:form>
 </div>
</body>
</html>