<!DOCTYPE html>
<html>
<head>
<title>Create User</title>
</head>
<body>
	<div style="padding-left:50px;font-family:monospace;">
		<h2>Create User</h2>
		<form action="rest/userInfo" method="POST">
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
</body>
</html>