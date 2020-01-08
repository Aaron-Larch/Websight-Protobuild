<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Line Chart</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<style>
	<%@include file="../../resources/css/common.css"%>
		/* Style the footer */
		footer2{	
    		width: 98%;
    		background-color: Gray;
 			padding: 15px;
    		float: left;
    		border: solid 1px black;
    		text-align: center;
		}
		
		.container button{display: inline-block;}
		#btn1{ float:left;}
		#btn3{ float:right;}
		
	</style>
	
</head>
<body>
<!-- Report Headers Object to tell the viewer which object is being viewed   -->
	 <div id="header" style="text-align:center">
	  <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2> ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
	
		<h1>Graphs Generated for the object: ${Label}</h1>
	</div>
		<div id="divName" style="display: none;">${Label}</div>

<!-- main object for charts organization  -->
	<div class="main-display">
		<!--Main report information, Bell curve Chart and Histogram Chart object  -->
		<div class="column">
			<textarea class="item" id="message">${Message}</textarea>
			<canvas class="item" id="BellcurveChart"></canvas>
			<canvas class="item" id="HistogramChart"></canvas>
		</div>
		<!-- High Line Graph, Low Line Graph, Box and Whisker Chart object -->
		<div class="column">
			<canvas class="item" id="HighLineGraph"></canvas>
			<canvas class="item" id="LowLineGraph"></canvas>
			<div class="item" id="BoxandWhisker"></div>
		</div>
	</div>
<script>
	/*Collect all the requierd variabuls*/
	var xAxsis= ${Arrays.toString(Xaxis)};
	var bellData= ${Arrays.toString(BellCurveGraph)};
	var highData= ${Arrays.toString(YaxisHigh)};
	var lowData= ${Arrays.toString(YaxisLow)};
	var boxData= ${Arrays.toString(BoxAndWhiskersGraph)};
	var barXAxis = ${Arrays.deepToString(BarGraph)};
	var HistogramData= ${Arrays.toString(Histogram)};
	
	/*Chart program layouts as found on https://www.chartjs.org/samples/latest/*/
	new Chart(document.getElementById("BellcurveChart"), {
		  type: 'line',
		  data: {
		    labels: xAxsis,
		    datasets: [{ 
		        data: bellData,
		        label: document.getElementById("divName").innerHTML,
		        borderColor: "#3e95cd",
		        fill: false
		      }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: 'Bell Curve Graph'
		    }
		  }
		});
	
	new Chart(document.getElementById("HighLineGraph"), {
		  type: 'line',
		  data: {
		    labels: xAxsis,
		    datasets: [{ 
		        data: highData,
		        label: document.getElementById("divName").innerHTML,
		        borderColor: "#3e95cd",
		        fill: false
		      }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: 'Data Graph from high to low'
		    }
		  }
		});
	
	new Chart(document.getElementById("LowLineGraph"), {
		  type: 'line',
		  data: {
		    labels: xAxsis,
		    datasets: [{ 
		        data: lowData,
		        label: document.getElementById("divName").innerHTML,
		        borderColor: "#3e95cd",
		        fill: false
		      }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: 'Data Graph from low to high'
		    }
		  }
		});
	
	new Chart(document.getElementById("HistogramChart"), {
		  type: 'bar',
		  data: {
		    labels: barXAxis,
		    datasets: [{ 
		        data: HistogramData,
		        label: document.getElementById("divName").innerHTML,
		        borderColor: "#3e95cd",
		        fill: false
		      }]
		  },
		  options: {
		    title: {
		      display: true,
		      text: 'Histogram Chart'
		    }
		  }
		});
	
	var chart = new CanvasJS.Chart("BoxandWhisker", {
		title:{
			text: "Box And Whisker Plot"
		},
		axisY: {
			interval: 40
		},
		data: [{
			type: "boxAndWhisker",
			upperBoxColor: "#FFC28D",
			lowerBoxColor: "#9ECCB8",
			color: "black",
			dataPoints: [
				
				{ label: document.getElementById("divName").innerHTML, y: boxData }
			]
		}]
	});
	chart.render();
</script>
</body>
<!-- Links to other objects Chart pages -->
<footer2>
	<spring:url value="/Stats/chart" var="ScrollURL" />
	<form id="Servlet" method="GET" action="${ScrollURL }" >
		<button type="submit" id="btn1" name="action" value="-1">Back</button>
		<button type="submit" id="btn3" name="action" value="1">Next</button>
	</form>
	<!-- Save information and close the current project-->
	<form action="FinalPagePrintFile" id="Servlet2" method="get">
		<input type="submit" id="btn2" name="action" value="Print File"/>
	</form>
</footer2>
</html>