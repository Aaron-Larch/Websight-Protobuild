<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Line Chart</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>

<body>
	<jsp:include page="/PrintFinalData" />
	<h2>If this works a graph should be here</h2>
	<textarea id="message" rows="12" cols="150">${Message}</textarea>
	<canvas id="BellcurveChart" height="250" width="500"></canvas>
	<br>
	<canvas id="HighLineGraph" height="250" width="500"></canvas>
	<canvas id="LowLineGraph" height="250" width="500"></canvas>
	<br>
	<div id="BoxandWhisker" style="height: 250px; width: 100%;"></div>
	
<script>
	var xAxsis= ${Arrays.toString(Xaxis)};
	var bellData= ${Arrays.toString(BellCurveGraph)};
	var highData= ${Arrays.toString(YaxisHigh)};
	var lowData= ${Arrays.toString(YaxisLow)};
	var boxData= ${Arrays.toString(BoxAndWhiskersGraph)};
	
	new Chart(document.getElementById("BellcurveChart"), {
		  type: 'line',
		  data: {
		    labels: xAxsis,
		    datasets: [{ 
		        data: bellData,
		        label: "user input",
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
		        label: "user input",
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
		        label: "user input",
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
	
	var chart = new CanvasJS.Chart("BoxandWhisker", {
		title:{
			text: "Box And Whisker Plot"
		},
		axisY: {
			interval: 10
		},
		data: [{
			type: "boxAndWhisker",
			upperBoxColor: "#FFC28D",
			lowerBoxColor: "#9ECCB8",
			color: "black",
			dataPoints: [
				
				{ label: "user input", y: boxData }
			]
		}]
	});
	chart.render();
</script>
</body>
</html>