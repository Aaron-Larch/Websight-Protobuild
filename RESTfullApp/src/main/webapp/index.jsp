<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Create User</title>
</head>
<body>
	<div style="padding-left:50px;font-family:monospace;">
			CRUD Operations</br></br>
		<a href="${pageContext.request.contextPath}/create.jsp"><div style="color:saffron">Create User</div></a><br>
		<a href="${pageContext.request.contextPath}/rest/userInfo"><div style="color:saffron">Get User details</div></a><br>
		<a href="${pageContext.request.contextPath}/update.jsp"><div style="color:saffron">Update User</div></a><br>
		<a href="${pageContext.request.contextPath}/delete.jsp"><div style="color:saffron">Delete User</div></a><br>
	</div>
</body>
</html>