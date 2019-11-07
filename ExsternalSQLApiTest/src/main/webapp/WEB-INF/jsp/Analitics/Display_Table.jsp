<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complete Report Collection</title>
<style type="text/css">
	#content {width: 50%;}
	#piecanvas {width: 50%;}
	
	table {
  		font-family: arial, sans-serif;
  		border: 1;
  		width: 95%;
  		margin-left: 2.5%; 
    	margin-right: 2.5%;
	}

	td, th {
  		border: 1px solid #dddddd;
  		padding: 8px;
	}

	tr:nth-child(even) {
  		background-color: #dddddd;
	}
</style>
 <jsp:include page="ModelLibrary.jsp" />
</head>
<body>
<div id="divLoad" style="display: none;">${Page}</div>
<div Class="SearchStatement">
		<h2>This page Is a temporary database of all the files created and all of the objects in each file:</h2>
		<div id="title1">To Select a file to look at. Or if you want to look at every file, Select one of the options presented.</div>
		<c:set var="hidden" value="0"/>
		<c:set var="count" value="0"/>
		<table>
		<c:forEach items="${Record}" var="block" varStatus="row">
			<c:if test="${not empty block[0].getreportId()}">
				<c:set var="place" value="${block[0].getreportId().indexOf('-')}"/>
  					<tr>
    				<th style="text-align:left">
    					<input type="radio" name="Record" value="${block[0].getreportId().substring(0, place)}" form="Servlet">
						<c:out value="${block[0].getreportId().substring(0, place)}"></c:out>
					</th>
				<c:forEach items="${block}" var="file">
					<c:if test="${not empty file}">
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
		<input type="submit" value="Display all Selected objects" form="Servlet"/>		
   		<p id="tital2">To Select Which Record to turn into Graphs please preform one or more of the following action.<br>
		First select one of the rows you wish to search through. If there is more than one row you wish to search through
		 the choose the Select All option is available.<br>
		To further sort through The Records you wish to very write a search statement in plain English about 
		which field you want to search by and the criteria of the search. Ie: Average >= 20.<br>
		If you wish to see All of the selected Records displayed then click the Display all Selected objects Button.<br>
		</p>
  		<spring:url value="/Stats/SearchFile" var="FileSortURL" />
  		<form action="${FileSortURL }" id="Servlet" method="get">
  			<input type="text" name="input">
    		<input type="submit" value="Search"/>
		</form>
			<div id="FailedResult">${Result}</div>
</div>
		<div Class="BuildTepfile">
		<h2>There are ${NumHits} entries matching your request</h2>
		<textarea id="message" rows="12" cols="150" readonly>${Message}</textarea>
		<spring:url value="/Stats/buildFinalRecord" var="StoretURL" />
		<form action="${StoretURL }" id="Servlet" method="get">
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
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(".SearchStatement"), function (i) {i.style.display="none";});
	}
}

function loadPopUp(i){
	$(document).ready(function(){
		$("#PopUpModal").modal().on('shown.bs.modal', function (e) {loadContent(i);});
		load_page('/WEB-INF/Objectcontent.jsp #container');
	});
	function load_page(url){$('modal-isi-body').load(url) ;}
}
</script>
</body>
</html>