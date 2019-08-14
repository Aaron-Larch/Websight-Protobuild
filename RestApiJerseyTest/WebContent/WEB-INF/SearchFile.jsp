<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Records</title>
</head>
<body>
<div id="divLoad" style="display: none;">${Page}</div>
    <div Class="SearchStatement">
    	<div id="proofofRecord" style="display: none;">${Message}</div>
		<h2>The titles of the files stored and the number of records in each file are:</h2>
		<div id="title1">Please select a file do you want to look at. Or if you want to look at every file Select all.</div>
		<c:set var="hidden" value="0"/>
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
		<c:choose>
    		<c:when test="${hidden > '1'}">
        		<div id="Optional" style="display: block;">
					<input type="radio" name="Record" value="all" form="Servlet">Search All
				</div>
    		</c:when>    
    		<c:otherwise>
        		<div id="Optional" style="display: none;">
					<input type="radio" name="Record" value="all" form="Servlet">Search All
				</div>
    		</c:otherwise>
		</c:choose>
		
   		<p id="tital2">what parameters do you want to sort the records by? Name, Average, Median, Mode, Min, or Max<br>
				Please Format your response in the following format: 'Average >= 20', 'Name contains 3', or 'Mode of 4'</p>
  		<form action="FileSort" id="Servlet" method="post">
  			<input type="text" name="input">
  			<input type="hidden" name="data" value="${mailbox}">
    		<input type="submit" value="Search"/>
		</form>
			<div id="FailedResult">${Result}</div>
	</div>
	<div Class="BuildTepfile">
		<h2>There are ${NumHits} entries matching your request</h2>
		<textarea id="message" rows="12" cols="150">${Message}</textarea>
		<form action="FileSort" id="Servlet" method="get">
			<p>Do you wish to add this record to the final graph report. Or do you want to discard the record.</p>
			<div id="buttons">
  			<input type="submit" name="choice" value="add">
  			<input type="submit" name="choice" value="discard">
  			</div>
		</form>
	</div>

<script type="text/javascript">
window.onload = function () {loadValues(document.getElementById("divLoad").innerHTML);}

function loadValues(flag){
	if(flag=="page1"){
		[].forEach.call(document.querySelectorAll(".BuildTepfile"), function (i) {i.style.display="none";});
		alert(document.getElementById("proofofRecord").innerHTML);
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(".BuildTepfile"), function (i) {i.style.display="none";});
	}else{
		[].forEach.call(document.querySelectorAll(".SearchStatement"), function (i) {i.style.display="none";});	
	}
}
</script>
</body>
</html>