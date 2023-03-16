<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Product</title>
</head>
<body>
	<form action="NewProduct" method="post">
		<table>
			<tr>
				<td>PRODUCT NAME</td>
				<td><input type="text" name="productName"></td>
			</tr>
			<tr>
				<td>DESCRIPTION</td>
				<td><input type="text" name="description"></td>
			</tr>
			<tr>
				<td>PICTURE URL</td>
				<td><input type="text" name="url"></td>
			</tr>
			<tr>
				<td>STATUS</td>
				<td><select name="status">
						<option value="0">DISABLED</option>
						<option value="1">AVAILABLE</option>
						<option value="2">REMOVED</option>
				</select></td>
			</tr>
			<tr>
				<td>PRICE</td>
				<td><input type="number" name="price"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Clear"></td>
<<<<<<< HEAD
			</tr>
		</table>
	</form>
=======
				<td></td>
			</tr>
		</table>
	</form>
	<button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
>>>>>>> refs/remotes/origin/master
</body>
</html>