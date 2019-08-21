<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Records</title>
<style type="text/css">
	#content {width: 50%;}
	#piecanvas {width: 50%;}
	
	table {
  		font-family: arial, sans-serif;
  		border: 1;
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
<jsp:include page="Objectcontent.jsp" />
</head>
<body>
<div id="modal-isi-body"></div>
<div id="divLoad" style="display: none;">${Page}</div>
    <div Class="SearchStatement">
    	<div id="proofofRecord" style="display: none;">${Message}</div>
		<h2>This page Is a temporary database of all the files created and all of the objects in each file:</h2>
		<div id="title1">To Select a file to look at. Or if you want to look at every file, Select one of the options presented.</div>
		<c:set var="hidden" value="0"/>
		<c:set var="count" value="0"/>
		<table border="1">
		<c:forEach items="${Record}" var="block" varStatus="row">
			<c:if test="${not empty block}">
				<c:set var="place" value="${block[0].getreportId().indexOf('-')}"/>
  					<tr>
    				<th>
    					<input type="radio" name="Record" value="${block[0].getreportId().substring(0, place)}" form="Servlet">
						<c:out value="${block[0].getreportId().substring(0, place)}"></c:out>
					</th>
				<c:forEach items="${block}" var="file">
					<c:if test="${not empty file}">
						<td>
							<a href="#" onclick="loadModal('${count}')">
							<c:out value="${file.getreportId()}"/></a>
						</td>
						<c:set var="count" value="${count+1}"/>
					</c:if>
				</c:forEach>
				<c:set var="hidden" value="${hidden+1}"/>
				</tr>
			</c:if>
		</c:forEach>
		</table>
		<c:choose>
    		<c:when test="${hidden > '1'}">
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
		<input type="submit" value="Display all objects" form="Servlet"/>
		
   		<p id="tital2">If you DO NOT wish to Display the graph data for every Object stored in the database you can perform A Basic Search to <br>
   				select only the Records you want Displayed to be Collected.  You can search of an object that has any of the following Characteristics: <br>
				Average     |grater than(>)<br>
				Mean        |less than(<)<br>
				median      |Equals or equal to(=)<br>
				mode        |Dose not equal(!=)<br>
				name        |contains for comparing words to values <br>
				record name |of (for use with mode value sorting "find all records with a mode of 5")<br>
				report id   |grater than or equal to(>=)<br>
				primary key |less than or equal to(<=)<br>
				assemble these words to form a sentence that describes the objects from the table you wish to search for. For an example<br>
				Average >= 20</p>
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

function loadModal(i){
	$(document).ready(function(){
		$("#PopUpModal").modal().on('shown.bs.modal', function (e) {loadMessage(i);});
		load_page('/WEB-INF/Objectcontent.jsp #container');
	});
	function load_page(url){$('modal-isi-body').load(url) ;}
}
</script>
</body>
</html>