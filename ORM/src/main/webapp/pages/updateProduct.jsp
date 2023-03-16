<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updating Products</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	const urlParams = new URLSearchParams(window.location.search);
	const greetingValue = urlParams.get('productID');
	$(document).ready(function() {
		newFunction();
	});
	function newFunction() {
		var id = document.getElementById("productID");
		id.value = greetingValue;
	}
</script>
<body>
	<form action="updateProduct" method="post">
		<table>
			<tr>
				<td><input type="hidden" id="productID"
					name="productID" /></td>
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