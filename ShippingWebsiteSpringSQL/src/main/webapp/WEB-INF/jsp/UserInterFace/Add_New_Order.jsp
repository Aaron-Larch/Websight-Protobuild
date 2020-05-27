<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!--variabule call for the class selction javascript program-->
<div id="divLoad" style="display: none;">${Flag}</div>

<!--table object for product catigories-->
<div class="welcome">
<table class="table table-striped" id="Info-Table">
   <thead>
    <th colspan="2">Welcome to JBA bulk shopping. What would you like to buy?</th>
   </thead>
   <tbody>
    <c:forEach items="${Storfront}" var="category">
     <tr>
      <td>
      <!-- Button for changing data in a row  -->
       <spring:url value="/Shipping/addneworder/${category.CATEGORYID}" var="Firstchoice" />
       <a class="btn btn-primary" href="${Firstchoice}" role="button" >${category.CATEGORYNAME}</a>
      </td>
      <td>${category.DESCRIPTION}</td>
      
     </tr>
    </c:forEach>
   </tbody>
  </table>
</div>

<!--table object for distinct product listings -->
<div class="Shoping">
<table class="table table-striped" id="Shopping-Table">
   <thead>
    <th scope="row">Product</th>
    <th scope="row">QuantityPerUnit</th>
    <th scope="row">UnitsInStock</th>
	<th scope="row">UnitPrice</th>
    <th scope="row">SupplierID</th>
   </thead>
   <tbody>
    <c:forEach items="${Shop}" var="Store" >
     <tr>
      <td>
      <!-- Button for changing data in a row  -->
       <spring:url value="/Shipping/addneworder/${Store.productName}" var="product" />
       <a class="btn btn-primary" href="${product}" role="button" >${Store.productName}</a>
      </td>
      <td>${Store.quantityPerUnit}</td>
      <td>${Store.unitsInStock}</td>
      <td>${Store.unitprice}</td>
      <td>${Store.supplierID}</td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
</div>

<!--table object for compairison shopping and discount coupons future proofed-->
 <div class="checkout">
	<table class="table table-striped" id="Invoice-Table">
	<tbody>
		<c:forEach items="${Owner}" var="comp" varStatus="status" >
	<tr>
	<td style="white-space:pre-wrap; word-wrap:break-word">
		<c:out value="${comp.companyName}: ${comp.address}, ${comp.city}, ${comp.country} ${comp.postalCode}"/>
		<c:out value="    ${Product[status.index].productName}: There are ${Product[status.index].quantityPerUnit} to a unit. 
		 		There are ${Product[status.index].unitsInStock} units in stock at $${Product[status.index].unitprice} per unit"/> 
		<c:out value="Avalibule Cupons and discounts:"/>
		 <c:forEach items="${Discnt}" var="cupon" varStatus="disc" ><c:if test="${cupon.productID == Product[status.index].productID}">
		 	<label for="discount">Coupon Name ${cupon.orderID}:  ${cupon.discount} Discount </label> <input type="checkbox" id="discount" <c:set var="coupon" value="${disc.index}"/> >
		 	</c:if></c:forEach>
	</td>
	<td style="white-space:pre-wrap; word-wrap:break-word">
		<label>Amount Purchased</label>
		<input type="text" name="Ammount" form="my_form" />
		<div id="FailedResult">${error}</div>
		<spring:url value="/Shipping/addneworder/Invoicve-${status.index}/${coupon}" var="invoice" />
		<form:form ModelAttribute="Ammount" method="post" action="${invoice}" id="my_form"></form:form>
		
	</td>
	<td style="white-space:pre-wrap; word-wrap:break-word">

		<button type="submit" class="btn btn-primary" form="my_form">Place order</button> 
	</td>
	</tr>
		</c:forEach>
	</tbody>
	</table>
	
 </div>
 
<!--User Input class For all fields of the table object-->
 <div class="container">
  <spring:url value="/Shipping/tableUpdate" var="addURL" />
  <h2>Article</h2>
  <form:form modelAttribute="order" method="post" action="${addURL }" cssClass="form" >
   
	<div class="form-group">
   <!-- Fixed Id should not allow user interaction -->
   <label>Order Id</label>
   <form:input path="ORDERID" cssClass="form-control" id="orderId" Value="${OrderID}"/>
   </div>
  
   <div class="form-group">
   <!-- Fixed list of Values needs drop down menu -->
    <label>Customer Id</label>
    <form:input path="CUSTOMERID" cssClass="form-control" id="customerId"/>
   </div>
   <div class="form-group">
    <label>Discount Id</label>
    <form:input path="EMPLOYEEID" cssClass="form-control" id="employeeId" Value="${order.EMPLOYEEID}"/>
   </div>
   <div class="form-group">
    <label>Amount Purchased</label>
    <form:input path="SHIPVIA" cssClass="form-control" id="shipVia" Value="${order.SHIPVIA}"/>
   </div>
   <div class="form-group">
    <label>Total Price</label>
    <form:input path="FREIGHT" cssClass="form-control" id="freight" Value="${order.FREIGHT}"/>
   </div>
   <div class="form-group">
    <label>Company Name</label>
    <form:input path="SHIPNAME" cssClass="form-control" id="shipName" Value="${order.SHIPNAME}"/>
   </div>
   <div class="form-group">
   
   <!-- Fixed list of Values needs drop down menu -->
    <label>Ship Country</label>
    <form:input path="SHIPCOUNTRY" cssClass="form-control" id="shipCountry" Value="${order.SHIPCOUNTRY}"/>
   </div>
   <button type="submit" class="btn btn-primary">Save</button>
  </form:form>
 </div>
 <footer><!--stylish footer to denote legal-->
	<hr style="background-color:black;"/>
	Copyright &copy; 2020. All rights reserved
</footer>
<script>
	<%@include file="../../resources/js/reportfunctions.js"%>
	//Build page on load function
	window.onload = function () {loadChannel(
			".Shoping", ".container", ".checkout", ".welcome", document.getElementById("divLoad").innerHTML);}
</script>
</body>
</html>