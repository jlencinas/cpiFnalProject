<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updating Products</title>
</head>
<body>
	<form action="UpdateProduct" method="post">
		<table>
			<tr>
				<td>PRODUCT ID</td>
				<td><input type="number" name="productID" required></td>
			</tr>
			<tr>
				<td>PRODUCT NAME</td>
				<td><input type="text" name="productName" required></td>
			</tr>
			<tr>
				<td>DESCRIPTION</td>
				<td><input type="text" name="description" required></td>
			</tr>
			<tr>
				<td>PICTURE URL</td>
				<td><input type="text" name="url" required></td>
			</tr>
			<tr>
				<td>STATUS</td>
				<td><select name="status" required>
						<option value="0">DISABLED</option>
						<option value="1">AVAILABLE</option>
						<option value="2">REMOVED</option>
				</select></td>
			</tr>
			<tr>
				<td>PRICE</td>
				<td><input type="number" name="price" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Clear"></td>
			</tr>
		</table>
	</form>
</body>
</html>