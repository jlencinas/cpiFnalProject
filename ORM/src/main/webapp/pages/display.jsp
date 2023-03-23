<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cpi.model.Product"%>
<%@ page import="com.cpi.dao.AddProduct"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="<c:url value="/resources/css/display.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/product-page.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/product-page.js"/>"></script>
	<title>Product Page</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script> var contextPath = '${pageContext.request.contextPath}' + '/';</script>
	<c:set var="seshinfo" value="${cart}" />
	<c:set var="seshinfoPrice" value="${price_cart}" />
	<c:set var="seshcart" value="${not empty seshinfo ? 'true' : 'false'}"/>
	<script>var boolCart = '${seshcart}'</script>
	<c:set var="quantitySesh" value="${0}"/>
	<c:set var="totalpriceSesh" value="${0}"/>
	<c:forEach items="${seshinfo}" var="sesh">
		<c:set var="quantitySesh" value="${sesh.value + quantitySesh}"/>
	</c:forEach>
	<c:forEach items="${seshinfoPrice}" var="sesh2">
		<c:set var="totalpriceSesh" value="${sesh2.value + totalpriceSesh}"/>
	</c:forEach>
	<script>var quantity = '${quantitySesh}'</script>
	<script>var totalPrice = '${totalpriceSesh}'</script>
	
</head>

<div class="container-product">
	<c:forEach items="${products}" var="product">
		<script>var prodPrice = '${product.productPrice}'</script>
		<script>var prodID = '${product.productID}'</script>
		<c:set var = "prodStat" value = "${product.productStatus}"/>
		<c:if test = "${prodStat == 2}">
			<!-- Removed Product Will Not Display -->
		</c:if>
		<c:if  test = "${prodStat == 1}">
			<!-- Available Product Display -->
			<script>displayFormattedPrice(prodID, prodPrice);</script>
			<div class="product-card" id="${product.productID}">
				<div class="card-container">
					<div class="card-left-container">
						<div class="card-image">
							<img src="${product.productPicture}"
								alt="products">
						</div>
					</div>
					<div class="card-right-container">
						<div class="card-title">
							<h3>${product.productName}</h3>
						</div>
						<div class="card-form" action="Test" method="post">
							<div class="card-desc">
								<h4 class = "" id = "product-price-${product.productID}"></h4>
								<div class="quantity-input">
									<button type="button" class="button-decrease-quantity"
										onclick = "decreaseQuan(${product.productID})" id="button-decrease-quantity">-</button>
									<input name=quantity class = "product-quantity" id="product-quantity-${product.productID}"
										onkeyup = "checkEmpty(${product.productID})" type="text" value = "1">
									<button type="button" class="button-increase-quantity" 
										onclick = "increaseQuan(${product.productID})" id="button-increase-quantity">+</button>
								</div>
							</div>
	
							<div class="button-selection">
								<input id="cart-value" type="hidden" value="${product.productID}">
								<button type="submit" class="button-addToCart" name="itemnum" id="button-addToCart" 
									onclick = "addCart(contextPath, ${product.productID}, ${product.productPrice}, boolCart);">
										Add To Cart
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test = "${prodStat == 0}">
			<!-- Disabled Products -->
			<script>displayFormattedPrice(prodID, prodPrice);</script>
			<div class="product-card-disabled" id="${product.productID}">
				<h4 class = "message-disabled">Product Unavailable.</h4>
				<div class="card-container disabled-product">
					<div class="card-left-container">
						<div class="card-image">
							<img src= "${product.productPicture}"
								alt="products">
						</div>
					</div>
					<div class="card-right-container">
						<div class="card-title">
							<h3>${product.productName}</h3>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
	

</div>

<script>
	$(document).ready(function() {
		checkCartSession(boolCart);
		initButton();
		initFunction();
	});
</script>
</html>

