<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div id="header" style="text-align:center">
	  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2> ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    </div>
    <div>${message}</div>
<table class="table table-striped" id="Product Table">
   <thead>
    	<th scope="row">#ID</th>
    	<th scope="row">Username</th>
    	<th scope="row">Password</th>
		<th scope="row">Role CODE</th>
		<th scope="row">Email</th>
    	<th scope="row">Update</th>
   </thead>
   <tbody>
    <c:forEach items="${UserTable}" var="account" >
     <tr>
      <td>${account.getId()}</td>
      <td>${account.username}</td>
      <td>${account.password}</td>
      <td>${account.roleid}</td>
      <td>${account.email}</td>

      <td>
      <!-- Button for changing data in a row  -->
       <spring:url value="/Account/ResetPassword" var="Account" />
	<form:form method="post" action="${Account}">
		<input name="Email" value="${account.email}" style="display: none;"/>
	<button type="submit" class="btn btn-primary">Reset</button>
	</form:form>
      </td>
     </tr>
    </c:forEach>
   </tbody>
  </table>
</body>
</html>