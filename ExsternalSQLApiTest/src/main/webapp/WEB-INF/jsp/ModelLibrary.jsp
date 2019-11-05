<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library For Models</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="PopUpModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div id="DisplayValue" style="display: none;">${Arrays.toString(PopUp)}</div>
				<textarea id="Display" rows="12" cols="60" readonly></textarea>
        </div>
      </div>
    </div>
  </div>
  </div>
  
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
			<form action="SetNameServlet" method="post">
				<p>Please Type in the New Name of the file you wish to create</p>
				<label for="name">Name:</label>
    			<input type="text" name="FileInput" id=name/>
				<input type="hidden" id="DynamicVariabule" name="flagValue"/><br>
    			<input type="submit" value="Submit File"/>
			</form>
        </div>
        <div class="modal-footer">
        	<button type="button" class="btn btn-default" id="close-modal" style= "float: right;" data-dismiss="modal">Close</button>
          <form action="SetNameServlet" method="get">
				<p class="control-label" id="Exit-text" style= "float: left;">Save Data and Close out of the program:</p>
    			<input type="submit" id="Exit-btn" value="Close" style= "float: right;"/>
			</form>
        </div>
      </div>
      
    </div>
  </div>
  </div>
  
  <div class="container"></div>
<script>
document.getElementById("Exit-text").style.display="none";
document.getElementById("Exit-btn").style.display="none";
document.getElementById("close-modal").style.display="none";

	function setValues(input){
		if(input==1){
			document.getElementById('DynamicVariabule').value=input;
			document.getElementById("close-modal").style.display="block";
	}else{
		document.getElementById('DynamicVariabule').value=input;
		document.getElementById("Exit-text").style.display="block";
			document.getElementById("Exit-btn").style.display="block";
	}
	}
	
	function loadContent(input) {
		document.getElementById("Display").value = "";
		var JsArray = document.getElementById('DisplayValue').innerHTML;
		var block = JsArray.substring(1, JsArray.length-1);
		var string = block.split("break");
		if(input > 0){
			var quickfix=string[input].substring(2, string[input].length);
			document.getElementById('Display').value=quickfix;
		}else{document.getElementById('Display').value=string[input];}
  	}
 </script>  
  
</body>
</body>
</html>