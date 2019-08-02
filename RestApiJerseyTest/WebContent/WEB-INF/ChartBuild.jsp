<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Line Chart</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<!-- To center an element vertically, I adopted a solution from the CSS style guide:
	https://www.w3.org/Style/Examples/007/center.en.html -->
	<style>
		.main-display {
			justify-content: center;
		}
		.column {
			display: inline-block;
			margin: 10px 10px 10px 10px;
			width: 47%;	
		}
		.item {
			width: 85%;
			display: block;
  			margin-left: auto;
  			margin-right: auto;
			min-height: 300px;
		}
		#message {
			font-size: 12pt;
		}
		/* Style the footer */
		footer{
    		width: 100%;
    		background-color: #777;
 			padding: 15px;
    		float: left;
    		border: solid 1px blue;
    		text-align: center;
		}
		.container button{
    		display: inline-block;
		}
		#btn1{
   			float:left;
		}
		#btn3{
    		float:right;
		}
	</style>
	
</head>

<body>
	<jsp:include page="${LoadPage}"/>
	<div id="header" style="text-align:center">
		<h1>Graphs Generated for the object: ${Label}</h1>
	</div>
		<div id="divName" style="display: none;">${Label}</div>

	<div class="main-display">
		<div class="column">
			<textarea class="item" id="message">${Message}</textarea>
			<canvas class="item" id="BellcurveChart"></canvas>
			<canvas class="item" id="HistogramChart"></canvas>
		</div><div class="column">
			<canvas class="item" id="HighLineGraph"></canvas>
			<canvas class="item" id="LowLineGraph"></canvas>
			<div class="item" id="BoxandWhisker"></div>
		</div>
	</div>

<script>
	var xAxsis= ${Arrays.toString(Xaxis)};
	var bellData= ${Arrays.toString(BellCurveGraph)};
	var highData= ${Arrays.toString(YaxisHigh)};
	var lowData= ${Arrays.toString(YaxisLow)};
	var boxData= ${Arrays.toString(BoxAndWhiskersGraph)};
	var barXAxis = ${Arrays.deepToString(BarGraph)};
	var HistogramData= ${Arrays.toString(Histogram)};
	
	
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
			interval: 10
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
<footer>
	<form action="PrintFinalData" id="Servlet" method="post">
		<button type="submit" id="btn1" name="action" value="-1">Back</button>
		<button type="submit" id="btn3" name="action" value="1">Next</button>
	</form>
	 <button id="btn2" name="action" value="1">print</button>
</footer>
</html>