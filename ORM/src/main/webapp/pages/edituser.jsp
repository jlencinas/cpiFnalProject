<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cpi.model.Product"%>
<%@ page import="com.cpi.dao.AddProduct"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display</title>
</head>
<body>
	<main>
		<%
		List<Product> productList = AddProduct.getProducts();
		for (Product product : productList) {
		%>
		<div class="product-card">
			<div class="product-image">
				<%-- <img src="data:image/png;base64,<%=product.convertImage()%>"
					alt="<%=product.getProdname()%>"> --%>
			</div>
			<div class="product-info">
				<h2><%=product.getProductName()%></h2>
				<p><%=product.getProductDescription()%></p>
				<h3>$<%=product.getProductPrice()%></h3>
				<form action="addToCart" method="post">
					<button class="buy-button" name="itemnum" value="<%=product.getProductID()%>">Add To Cart!</button>
				</form>
			</div>
		</div>
		<%
		}
		%>
	</main>
</body>
</html>
<style>
main {
	position: absolute;
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	top: 60px;
	left: 0px;
	margin-top: 15px;
	width: 100%;
	height: auto;
	background-position: center;
	background-size: cover;
	background-color: aquamarine;
}

.product-card {
	display: inline-block;
	margin: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	width: 300px;
}

.product-image img {
	width: 100%;
	height: 200px;
	object-fit: cover;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.product-info {
	padding: 20px;
}

.product-info h2 {
	margin-top: 0;
}

.product-info h3 {
	margin-bottom: 0;
}

.buy-button {
	background-color: #4CAF50;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100%;
}

.buy-button:hover {
	background-color: #45a049;
}
</style>