<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Records</title>
</head>
<body onload="getMessage()">
<div id="proofofRecord" style="display: none;">${Message}</div>
    <div Class="SearchStatement">
		<h2>The titles of the files stored and the number of records in each file are:</h2>
		<div id="title1">What file do you want to look at? If you want to look at every file Select all.</div>
		
		<c:forEach items="${Record}" var="block">
			<c:set var="count" value="0"/>
			<c:if test="${not empty block}">
				<c:forEach items="${block}" var="file">
					<c:if test="${not empty file}">
						<c:set var="count" value="${count+1}"/>
						<c:set var="place" value="${file.getreportId().indexOf('-')}"/>
						<c:set var="name" value="${file.getreportId().substring(0, place)}"/>
					</c:if>
				</c:forEach>
				<input type="radio" name="Record" value="${name}" form="Servlet">
				<c:out value="${name}"/>: <c:out value="${count}"/><br> 
			</c:if>
		</c:forEach>
		<input type="radio" name="Record" value="all" form="Servlet">all

   		<p id="tital2">what paramiter do you want to sort by? Name, Average, Median, Mode, Min, or Max<br>
				Please Format your response in the following format: 'Average >= 20', 'Name contains 3', or 'Mode of 4'</p>
  		<form action="FileSort" id="Servlet" method="post">
  			<input type="text" name="input">
  			<input type="hidden" name="data" value="${mailbox}">
    		<input type="submit" value="Search"/>
		</form>
	</div>
	<div id="FailedResult">${Result}</div>
<script type="text/javascript">
function getMessage() {alert(document.getElementById("proofofRecord").innerHTML);}
</script>
</body>
</html>