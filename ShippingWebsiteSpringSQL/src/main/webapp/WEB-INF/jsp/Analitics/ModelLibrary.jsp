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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style><%@include file="../../resources/css/common.css"%></style>
  <script><%@include file="../../resources/js/reportfunctions.js"%></script>
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
         <h4 class="modal-title">Collect Data for Further reports</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <div class="PopUpModal" style="display: none;">
        <spring:url value="/Shipping/switchup" var="NewControllerURL" />
  		<form id="GatherData" method="get" action="${NewControllerURL }" class="form-signin">
    		<label>Column Report Name</label>
    		<input type="text" name="cn" placeholder="insert name here" >
        	<!-- list of sortabule feilds could this be made dynamicly? -->
        	<p><input type="radio" name="Product" value="employeeid" 
        	onchange="document.getElementById('SearchParamiters').disabled = !this.checked;"> Employee id</input></p>
    		<p><input type="radio" name="Product" value="shipVia" 
    		onchange="document.getElementById('SearchParamiters').disabled = !this.checked;"> Product Code</input></p> 
    		<p><input type="radio" name="Product" value="freight" 
    		onchange="document.getElementById('SearchParamiters').disabled = !this.checked;"> prophets</input></p>
    		
   		<button type="submit" class="btn btn-primary" id="SearchParamiters" disabled="disabled" >Save</button>
  		</form>
  		</div>
  		<div class="CreateNewRecord" style="display: none;">
  		<p>this is a test to see if this works</p>
  		</div>
      </div>
      <div class="modal-footer">
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
        <h1 id="reset-page">The Record will Now be Closed</h1>
        <p>You will be unabel to add any more objects to this Record if you click any of the buttons below. 
        To exit back to the Stock profile you were already working with click off the window or click 
        the close(x) icon in the upper right of the window.</p>
        </div>
        <div class="modal-body">
	        	<div id="saveandnew">
				<p style= "float: left;">Save Data And Create new Record	
				<spring:url value="/Stats/createnewrec" var="NewRecordURL" />
  				<a class="btn btn-primary" href="${NewRecordURL }" role="button" >Save & New</a>
  				</p>
  			<br>
  			<br>
  			<h4 id="spacer">or</h4>
  			<br>
				<p style= "float: left;">Save Data And Create Graph Folder	
				<spring:url value="/Stats/closerecords" var="BuildreportURL" />
  				<a class="btn btn-primary" href="${BuildreportURL }" role="button">Save & Quit</a>
  				</p>
  			</div>
        </div>
        <div class="modal-footer">

  			
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
        <div class="modal-body">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
        <!-- Hidden array of strings to search through -->
        	<div id="DisplayValue" style="display: none;">${PopUp}</div>
				<textarea class="window" id="Display" rows="12" cols="55" readonly></textarea>
        </div>
      </div>
    </div>
  </div>
  </div> 
</body>
</body>
</html>