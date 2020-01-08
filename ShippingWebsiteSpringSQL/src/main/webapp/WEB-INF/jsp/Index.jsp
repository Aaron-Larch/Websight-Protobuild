<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<head>
    <meta charset="UTF-8">
    <title>Hello World!</title> 
</head>
<body>
    <h2 class="hello-title">This is a home page to launch the project proper</h2>
    <p>Insert into here a fancy page to sell the compony and what it dose</p>
    <spring:url value="/login" var="StartURL" />
  	<a id="LoadFile" href="${StartURL }" >Login</a>.
</body>
</html>
