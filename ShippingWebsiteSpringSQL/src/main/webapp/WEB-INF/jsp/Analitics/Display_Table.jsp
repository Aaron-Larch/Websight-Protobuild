<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complete Report Collection</title>
 <jsp:include page="ModelLibrary.jsp" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style><%@include file="../../resources/css/common.css"%></style>
   <script>
  	<%@include file="../../resources/js/reportfunctions.js"%>
	//Build page on load function
  	window.onload = function () {loadValues(".SearchStatement", ".BuildTepfile", document.getElementById("divLoad").innerHTML);}
  </script>
</head>
<body>
<!-- A stylish Header that contains all futuer user options -->
    <div class="headder">
  <div style="text-align:left; float:left;">
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
<!-- Hidden load value To dictate the form the page will take -->
<div id="divLoad" style="display: none;">${Page}</div>

<!-- Build a Dynamic table around the number of objects created and there primary key values -->
<div Class="SearchStatement">
		<h2>This page Is a temporary database of all the files created and all of the objects in each file:</h2>
		<div id="title1">To Select a file to look at. Or if you want to look at every file, Select one of the options presented.</div>
		<c:set var="hidden" value="0"/>
		<c:set var="count" value="0"/>
		<table class="table table-striped" id="MasterTable">
		<c:forEach items="${Record}" var="block" varStatus="row">
			<c:if test="${not empty block[0]}">
				<c:set var="place" value="${block[0].getreportId().indexOf('-')}"/>
  					<tr>
  					<!-- Create a stored value for a search engine -->
    				<th style="text-align:left">
    					<input type="radio" name="Record" value="${block[0].getreportId().substring(0, place)}" form="Servlet">
						<c:out value="${block[0].getreportId().substring(0, place)}"></c:out>
					</th>
				<c:forEach items="${block}" var="file">
					<c:if test="${not empty file}">
					<!-- Create a link for a pop up window modal to view the objects contents -->
						<td style="text-align:center">
							<a href="#" onclick="loadPopUp('${count}')">
							<c:out value="${file.getreportId()}"/></a>
						</td>
						<c:set var="count" value="${count+1}"/>
					</c:if>
				</c:forEach>
				<c:set var="hidden" value="${row.index}"/>
				</tr>
			</c:if>
		</c:forEach>
		</table>
		<!-- A check test to display an optional button in the event of multiple Primary key types  -->
		<c:choose>
    		<c:when test="${hidden > '0'}">
        		<div id="Optional" style="display: block;">
					<input type="radio" name="Record" value="all" form="Servlet">Select All
				</div>
    		</c:when>    
    		<c:otherwise>
        		<div id="Optional" style="display: none;">
					<input type="radio" name="Record" value="all" form="Servlet">Select All
				</div>
    		</c:otherwise>
		</c:choose>
		
		<!-- a select all option for the search engine -->
		<input class="btn btn-primary" type="submit" value="Display all Selected objects" form="Servlet"/>
		<br>
		 <hr style="background-color:black;"/>
		 <br>		
   		<p id="tital2">To Select Which Record to turn into Graphs please preform one or more of the following action.<br>
		First select one of the rows you wish to search through. If there is more than one row you wish to search through
		 the choose the Select All option is available.<br>
		To further sort through The Records you wish to very write a search statement in plain English about 
		which field you want to search by and the criteria of the search. Ie: Average >= 20.<br>
		If you wish to see All of the selected Records displayed then click the Display all Selected objects Button.<br>
		</p>
		<!-- A plain english search engine -->
  		<spring:url value="/Stats/SearchFile" var="FileSortURL" />
  		<form action="${FileSortURL }" id="Servlet" method="get">
  			<div id="Searchbar">
  			<input style="float:Right;" class="btn btn-primary" type="submit" value="Search"/>
  			<input id="searcline" type="text" name="input" Class="form-control">
    		</div>
		</form>
			<div id="FailedResult">${Result}</div>
</div>

<!-- Second Class to display User search results -->
<div Class="BuildTepfile">
	<h2>There are ${NumHits} entries matching your request:</h2>
	<textarea id="message" rows="12" cols="150" readonly>${Message}</textarea>
	<spring:url value="/Stats/buildFinalRecord" var="StoretURL" />
	<!-- User Decides to keep or ignore returned Object from search -->
	<form action="${StoretURL }" id="Servlet" method="get">
		<p style="text-align:center;">Do you wish to add this record to the final graph report. Or do you want to discard the record.</p>
		<div id="buttons">
  		<input  style="float:left;" class="btn btn-primary" type="submit" name="choice" value="add">
  		<input  style="float:Right;" class="btn btn-primary" type="submit" name="choice" value="discard">
  		</div>
	</form>
</div>	

<footer>
	<hr style="background-color:black;" />
	Copyright &copy; 2020. All rights reserved
</footer>
</body>
</html>