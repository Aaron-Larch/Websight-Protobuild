<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Print the report to a File</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<style>
	#content {width: 50%;}
	#piecanvas {width: 80%;}
	
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
</head>
<body>
<div id="content">
<table>
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
</div>
<div id="piecanvas">
		<canvas id="donutchart" width="400" height="400"></canvas>
</div>
	<form action="FinalPagePrintFile" id="Servlet2" method="post">
		<input type="submit" id="btn2" name="action" value="Exit Program"/>
		<p id="tital2">To print out all stored data from the collected Reports. Write in the file path and click submit</p>
		<input type="text" name="Filepath">
		<input type="submit" id="btn2" name="action" value="Submit"/>
	</form>
<script>
	var ctx = document.getElementById("donutchart");
	var data = {
			datasets: [{
				data: ${values},
				backgroundColor: ${colors}
			}],
			labels: ${keys}
			
	};
	var donutchart = new Chart(ctx, {
		type: 'doughnut',
		data: data,
		options: Chart.defaults.doughnut
	});
</script>
</body>
</html>