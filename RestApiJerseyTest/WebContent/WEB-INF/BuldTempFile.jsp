<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>There are ${NumHits} enteries matching your request</h2>
<textarea id="message" rows="12" cols="150">${Message}</textarea>
	<form action="FileSort" id="Servlet" method="get">
		<p>Do you wish to make a graph of this record?</p>
		<div id="buttons">
  			<input type="submit" name="choice" value="yes">
  			<input type="submit" name="choice" value="no">
  		</div>
	</form>

</body>
</html>