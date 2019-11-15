<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library For Models</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  <style type="text/css">
  textarea.window {
    display: block;
    border: none;
    margin-left: auto;
    margin-right: auto;
}
  </style>
</head>
<body><!-- A jsp page to contain all the required Modal's for the build reports program -->

<!-- apply a sort filter to raw data and create a data table to work with -->
<div class="container">
<!-- Modal -->
<div id="AssineParamiters" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
         <h4 class="modal-title" align="left">Collect Data for Further reports</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <spring:url value="/Shipping/switchup" var="NewControllerURL" />
  		<h2>Article</h2>
  		<form id="GatherData" method="post" action="${NewControllerURL }">
    		<label>Column Report Name</label>
    		<input type="text" name="cn" value="insert name here">
        	<!-- list of sortabule feilds could this be made dynamicly? -->
        	<p><input type="radio" name="Product" value="employeeid" onchange="document.getElementById('CheckTest').disabled = !this.checked;"> Employee id</input></p>
    		<p><input type="radio" name="Product" value="shipVia" onchange="document.getElementById('CheckTest').disabled = !this.checked;"> Product Code</input></p> 
    		<p><input type="radio" name="Product" value="freight" onchange="document.getElementById('CheckTest').disabled = !this.checked;"> prophets</input></p>
    		
   		<button type="submit" class="btn btn-primary" id="CheckTest" disabled="disabled" >Save</button>
  		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</div>

<!-- Create new object array or exit the array and make a table of data -->
  <div class="container">
  <!-- Modal -->
  <div class="modal fade" id="CreateNewRecord" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div Class="modal-headder">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h1 id="reset-page">Product Stock Record will be Closed after your next operation is selected</h1>
        <p>to exit back to the Stock profile you were already working with click off the window</p>
        <p> or click the close(x) icon in the upper right of the window.</p>
        </div>
        <div class="modal-body">
	
  		 
        </div>
        <div class="modal-footer">
        	<div id="saveandnew">
				<p style= "float: left;">Save Data And Create new Record</p>
				<spring:url value="/Stats/createnewrec" var="NewRecordURL" />
  				<a class="btn btn-primary" href="${NewRecordURL }" role="button" style= "float: right;">Save & New</a>
  				<br>
  			</div>
  			<div id="saveandclose">
				<p style= "float: left;">Save Data And Build Report Folder</p>
				<spring:url value="/Stats/closerecords" var="BuildreportURL" />
  				<a class="btn btn-primary" href="${BuildreportURL }" role="button" style= "float: right;">Save & Quit</a>
  				<br>
  			</div>
  			
  			<button type="button" class="btn btn-default" id="close-modal" style= "float: right;" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  </div>
  
 <!-- pop up window method -->
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="PopUpModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
      <div Class="modal-headder">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
        <div class="modal-body">
        <!-- Hidden array of strings to search through -->
        	<div id="DisplayValue" style="display: none;">${PopUp}</div>
				<textarea class="window" id="Display" rows="12" cols="55" readonly></textarea>
        </div>
      </div>
    </div>
  </div>
  </div>
<script>
//hide unused modals
function loadValues(flag){
	if(flag=="1"){
		[].forEach.call(document.querySelectorAll(".PopUpModal"), function (i) {i.style.display="none";});
	}else if(flag=="2"){
		[].forEach.call(document.querySelectorAll(".CreateNewRecord"), function (i) {i.style.display="none";});
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
</script>  
  
</body>
</body>
</html>