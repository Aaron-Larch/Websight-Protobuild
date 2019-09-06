<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</body>
<script>
	function loadContent(input) {
		document.getElementById("Display").value = " ";
		var JsArray = document.getElementById('DisplayValue').innerHTML;
		var block = JsArray.substring(1, JsArray.length-1);
		var string = block.split("break");
		if(input > 0){
			var quickfix=string[input].substring(2, string[input].length);
			document.getElementById('Display').value=quickfix;
		}else{document.getElementById('Display').value=string[input];}
  	}
 </script>
</html>