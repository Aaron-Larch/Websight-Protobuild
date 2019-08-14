<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Print the report to a File</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<table border="1">
  <thead>
  <tr>
    <th>Mode</th>
    <th>Number of Hits</th>
  </tr>
  <thead>
  <tbody>
  	<c:forEach items="${table}" var="frequency">
		<tr>
			<td>${frequency.getKey().toString()}</td>
        	<td>${frequency.getValue().toString()}</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>
<div>${message}</div>
</body>
</html>