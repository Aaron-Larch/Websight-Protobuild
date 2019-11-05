<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Display The Record Created</title>
	<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
	<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<textarea id="message" rows="12" cols="150" readonly>${Message}</textarea>

 <div class="container">
 <select name="category" onChange="window.location.href=this.value">
    <option value="none">Choose A region</option>
    <c:forEach items="${listCategory}" var="category" varStatus="loop">
    	<spring:url value="/Stats/chosenewdata/${loop.index}" var="DataSelectURL" />
        <option value="${DataSelectURL }">${category}</option>
    </c:forEach>
</select>
 </div>

</body>
</html>