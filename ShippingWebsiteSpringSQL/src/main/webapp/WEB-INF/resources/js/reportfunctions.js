/*!
 * JavaScript Methods (https://github.com/Aaron-Larch/Websight-Protobuild)
 * Copyright 2020-2025 GCE, Inc.
 * Created by Aaron Larch
 */

$(document).ready(function () {
	// Get a modal
	this.loadModal = function(input){
		$("#AssineParamiters").modal().on('shown.bs.modal', function (e) {loadValues(".PopUpModal", ".CreateNewRecord", input)});
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	};
	
	this.getModal = function(){
		$("#CreateNewRecord").modal().on('shown.bs.modal');
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	};
	
	//function to call popup window
	this.loadPopUp =function (input){
		$("#PopUpModal").modal().on('shown.bs.modal', function (e) {loadContent(input);});
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	};
	
	//Switch states button
	this.SwichLoadout= function(a){
		if(a==1){
			[].forEach.call(document.querySelectorAll(".Populate"), function (i) {i.style.display="block";});
			[].forEach.call(document.querySelectorAll(".display"), function (i) {i.style.display="none";});
		}else{
			[].forEach.call(document.querySelectorAll(".Populate"), function (i) {i.style.display="none";});
			[].forEach.call(document.querySelectorAll('.display'), function (i) {i.style.display="block";});
		}
	};

	//Check all function
	this.checkAll=function(source) {
		  checkboxes = document.getElementsByName('operation');
		  for(var i=0; i<checkboxes.length; i++) {
		    checkboxes[i].checked = source.checked;
		  }
		};
	
	this.renderChart();
})	

function load_page(url){$('modal-isi-body').load(url) ;}

//Display a combination of classes based of a given variabule
function loadValues(class1, class2, flag){
	if(flag=="page1"){
		[].forEach.call(document.querySelectorAll(class1), function (i) {i.style.display="block";});
		[].forEach.call(document.querySelectorAll(class2), function (i) {i.style.display="none";});
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(class2), function (i) {i.style.display="block";});
		[].forEach.call(document.querySelectorAll(class1), function (i) {i.style.display="none";});
		}
}

//Select a string from an array of strings based on user input 
function loadContent(input) {
	document.getElementById("Display").value = "";
	//convert string into an array of strings
	var JsArray = document.getElementById('DisplayValue').innerHTML;
	var block = JsArray.substring(1, JsArray.length-1);
	var string = block.split("break");
	if(input > 0){ //Search array
		var quickfix=string[input].substring(2, string[input].length);
		document.getElementById('Display').value=quickfix;
	}else{document.getElementById('Display').value=string[input];}
}


function loadChannel(cl1, cl2, cl3, cl4, flag){
	if(flag=="Step1"){
		[].forEach.call(document.querySelectorAll(cl1), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl2), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl3), function (i) {i.style.display="none";});
	}else if(flag=="Step2"){
		[].forEach.call(document.querySelectorAll(cl4), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl2), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl3), function (i) {i.style.display="none";});
	}else if(flag=="Step3"){
		[].forEach.call(document.querySelectorAll(cl4), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl1), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl2), function (i) {i.style.display="none";});
	}else{
		[].forEach.call(document.querySelectorAll(cl4), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl1), function (i) {i.style.display="none";});
		[].forEach.call(document.querySelectorAll(cl3), function (i) {i.style.display="none";});
	}
}

function buildReport(
		xAxsis,
		bellData,
		highData,
		lowData,
		boxData,
		barXAxis,
		HistogramData) {
	/*Chart program layouts as found on https://www.chartjs.org/samples/latest/*/
	 new Chart("BellcurveChart", {
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
	 
	 new Chart("HighLineGraph", {
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
	 
	 new Chart("LowLineGraph", {
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
	 
	 new Chart("HistogramChart", {
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
}

function renderChart(input) {
	/*Chart program layouts as found on https://www.chartjs.org/samples/latest/*/
	var chart = new Chart("chartContainer", { 
		  type: 'line',
			  data: {
			    labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
			    datasets: [{ 
			        data: input,
			        label: 'My first dataset',
			        borderColor: "#3e95cd",
			        fill: false
			     }]
			  },
			  options: {
			    title: {
			      display: true,
			      text: 'This site can also track Large volumes of data over time and place them into graphs using CharJS software'
	                    }
	                }
			});
	chart.render();
}
