<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <style><%@include file="../../resources/css/common.css"%></style>
  </head>

  <body>
   <!-- A stylish Header that contains all futuer user options -->
  <div class="headder">
  <h2>JBA Shipping Inc.</h2>
  <p class="c">We Deliver the Best to Deliver You Success</p>
   <hr style="clear:both;"/>
</div>

    <div class="container">
        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

			 <spring:bind path="roleid">
			 <div class="form-group">
			 <label for="sel1">Select A Role:</label>
 		<form:select path="roleid" class="form-control" id="sel1">
    		<option value="none">Choose A Role</option>
    		<c:forEach items="${Rolelist}" var="Role">
			<form:option value="${Role.DIVISIONID}">${Role.DIVISIONNAME}</form:option>
    		</c:forEach>
		</form:select>
 		</div>
 		</spring:bind>
 		
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>
    
    <footer>
	<hr />
	Copyright &copy; 2020. All rights reserved
</footer>
  </body>
</html>