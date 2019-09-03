<!DOCTYPE html>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="js/rest.js"></script>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
<title>Update User</title>
</head>
<body>
	<div style="padding-left:50px;font-family:monospace;">
		<h2>Update User</h2>
		<form id="updateForm">
			<div style="width: 100px; text-align:left;">
				<div style="padding:15px;">
					User ID: <input name="id" />
				</div>
				<div style="padding:10px;">
					Name: <input name="name" />
				</div>
				<div style="padding:10px;">
					Age: <input name="age" />
				</div>
				<div style="padding:20px;text-align:center">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
$("#updateForm").on("submit", function(){
	
	$.ajax({
	    url: 'rest/userInfo',
	    type: 'PUT',
	    dataType: "xml",
	    data:$("#updateForm").serialize(),
	    success: function(xml) {
	    	console.log(xml);
	    	var user="";
	    	$(xml).find('User').each(function(){
                $(this).find("id").each(function(){
                    var id = $(this).text();
                    console.log(id);
                    user=user+"ID: "+id;
                });
                $(this).find("name").each(function(){
                    var name = $(this).text();
                    console.log(name);
                    user=user+" Name: "+name;
                });
                $(this).find("age").each(function(){
                    var age = $(this).text();
                    console.log(age);
                    user=user+" Age: "+age;
                });
            });
	    	alert(user);
	    }
	});
   return true;
 })
</script>
</body>
</html>