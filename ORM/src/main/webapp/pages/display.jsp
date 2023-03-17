<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cpi.model.Product"%>
<%@ page import="com.cpi.dao.AddProduct"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/display.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/css/product-page.css"/>">
<title>Product Page</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>
</head>

<div class="container-product">
	<c:forEach items="${products}" var="product">
		<script>var prodDesc = '${product.productDescription}'</script>
		<div class="product-card" id="${product.productID}">
			<div class="card-container">
				<div class="card-left-container">
					<div class="card-image">
						<img src="<c:url value="/resources/images/bread1.jpg"/>"
							alt="products">
					</div>
				</div>
				<div class="card-right-container">
					<div class="card-title">
						<h3>${product.productName}</h3>
					</div>
					<%-- 					<form class = "card-form" action="Test" method="post">
						<div class="card-desc" >
								<h4>$${product.productPrice}</h4>
							<div class="quantity-input">
								<button type = "button" class="button-decrease-quantity" id="button-decrease-quantity">-</button>
								<input  name=quantity id="product-quantity" type="number" value="1" min="1">
								<button type="button" class="button-increase-quantity" id="button-increase-quantity">+</button>
							</div>
						</div>
	
						<div class="button-selection">
							<input id="cart-value" type="hidden" value="${product.productID}">
							<button type = "submit" class="button-addToCart" name="itemnum" id="button-addToCart"
								value="${product.productID}">Add To Cart</button>
						</div>
					</form> --%>


					<div class="card-form" action="Test" method="post">
						<div class="card-desc">
							<h4>$${product.productPrice}</h4>
							<div class="quantity-input">
								<button type="button" class="button-decrease-quantity" id="button-decrease-quantity">-</button>
								<input name=quantity id="product-quantity-${product.productID}" type="number" value="1" min="1">
								<button type="button" class="button-increase-quantity" id="button-increase-quantity">+</button>
							</div>
						</div>

						<div class="button-selection">
							<input id="cart-value" type="hidden" value="${product.productID}">
							<button type="submit" class="button-addToCart" name="itemnum" id="button-addToCart" 
								onclick = "addCart(contextPath, ${product.productID}, ${product.productPrice}, '${product.productDescription}')">
									Add To Cart
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<script>
	var popup = document.getElementById("card-container1");
	var header = document.getElementById("myHeader");
	var main = document.getElementById("myMain");
	function openPopup() {
		openOrderDetails();
		popup.classList.add("open-popup");
		header.style.filter = "blur(8px)";
		main.style.filter = "blur(8px)";
		header.style.pointerEvents = "none";
		main.style.pointerEvents = "none";
	}

	function closePopup() {
		popup.classList.remove("open-popup");
		header.style.filter = "none";
		main.style.filter = "none";
		header.style.pointerEvents = "all";
		main.style.pointerEvents = "all";
		closeOrderDetails();
	}
	
	$(document).ready(function() {
		initTestButton(contextPath);
	});
</script>
</html>

