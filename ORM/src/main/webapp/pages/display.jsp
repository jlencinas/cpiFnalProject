<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cpi.model.Product"%>
<%@ page import="com.cpi.dao.AddProduct"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<title>Product Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script> var contextPath = '${pageContext.request.contextPath}' + '/'; </script>
<script type="text/javascript" src="<c:url value="/resources/js/controller.js"/>"></script>
</head>

<div class="container-product">
	<c:forEach items="${products}" var="product">
		<div class="product-card">
			<div class="card-container">
				<div class="card-left-container">
					<div class="card-image">
						<img src="resources/images/bread1.jpg" alt="products">
					</div>
				</div>
				<div class="card-right-container">
					<div class="card-title">
						<h3>${product.productName}</h3>
					</div>

					<form action="pages/AddOrderDetails" method="post">
					<div class="quantity-input">
						<div class="card-desc">
							<h4>$${product.productPrice}</h4>
							<button class="button-decrease-quantity"
								id="button-decrease-quantity" onclick="minusQuantityButton()">-</button>
							<input  name=quantity id="product-quantity" type="number" value="1" min="1">
							<button class="button-increase-quantity"
								id="button-increase-quantity" onclick="addQuantityButton()">+</button>
						</div>
					</div>

					<div class="button-selection">

						<input id="cart-value" type="hidden" value="${product.productID}">
						<button class="button-addToCart" name="itemnum" id="button-addToCart"
							value="${product.productID}">Add To Cart</button>
					</div>
					</form>
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
</script>
</html>

