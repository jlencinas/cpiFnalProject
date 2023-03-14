<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Products</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Product Code</th>
				<th>Description</th>
				<th>Picture URL</th>
				<th>Status</th>
				<th>Edit</th>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.productName}</td>
					<td>${product.productID}</td>
					<td>${product.productDescription}</td>
					<td>${product.productPicture}</td>
					<td>${product.productStatus}</td>
					<td><a href="updateProduct.jsp?productID=${product.productID}">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>