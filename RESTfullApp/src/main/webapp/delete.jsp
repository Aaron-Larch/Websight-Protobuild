<!DOCTYPE html>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="js/rest.js"></script>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
<title>Delete User</title>
</head>
<body>
	<div style="padding-left:50px;font-family:monospace;">
		<h2>Delete User</h2>
		<form id="deleteForm">
			<div style="width: 100px; text-align:left;">
				<div style="padding:15px;">
					User ID: <input name="id" />
				</div>
				<div style="padding:20px;text-align:center">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	 $("#deleteForm").on("submit", function(){
			$.ajax({
			    url: 'rest/userInfo',
			    type: 'DELETE',
			    dataType: "xml",
			    data:$("#deleteForm").serialize(),
			    success: function(xml) {
			    	console.log(xml);
			    	$(xml).find('User').each(function(){
		                $(this).find("id").each(function(){
		                    var id = $(this).text();
		                    console.log(id);
		                    alert("Deleted the user with id "+id);
		                });
		            });
			    	
			    }
			});
		   return true;
		 })
	</script>
</body>
</html>