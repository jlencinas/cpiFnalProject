
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
<script type="text/javascript" src="<c:url value="/resources/js/product-page.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/order-detail.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/controller.js"/>"></script>
<script>
        $(document).ready(function () {
            checkDeliveryDate();
            checkTimeInput();
            boolDate();
        });
    </script>
    
    
</head>

<div class="title">
	<h1>Order Details</h1>
</div>
<div class="inputFieldContainer">
	<div class="inputField">
		<div class="inputFieldLabel">
			<h2>Name</h2>
		</div>
		<div class="inputFieldInput">
			<div class="inputFieldName">
				<input type="text" id = "firstName" placeholder="Ex: Jane" required>
				<p>First Name</p>
			</div>
			<div class="inputFieldName">
				<input type="text" id = "lastName" placeholder="Ex: Doe" required>
				<p>Last Name</p>
			</div>
		</div>
	</div><c:set var="seshinfo" value="${cart}" />
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
	<c:set var="confNum" value="${confirmationNum}"/>
	<script>var paramOne ='${confNum}'</script>
	<script>var quantity = '${quantitySesh}'</script>
	<script>var totalPrice = '${totalpriceSesh}'</script>
	<script>
		checkCartSession(boolCart);
		initFunction();
	</script>
	<div class="inputField">
		<div class="inputFieldName">
			<input id="telField" class="telField" type="tel"
				placeholder="Ex: 09123456789" maxlength="11" minlength="11"
				pattern="[0-9]{11}" oninput="checkTelInput()"
				onchange="checkTelInput()" required> <span
				class="tel-validity tel-validity-hidden" id="telValidity"></span>
			<p>Mobile Number</p>
		</div>
	</div>

	<p class="deliver-date-warning" id="delivery-date-warning"></p>
	<div class="deliveryDate">
		<div class="inputDateField">
			<input type="date" id="inputDate" name="inputdate"
				onchange="checkDeliveryDate(); boolDate();">
			<p>Deliver Date</p>
		</div>

		<div class="inputTimeField">
			<input type="time" class="timeField" id="inputTime" name="inputTime"
				onchange="checkTimeInput()" required disabled> <span
				class="time-validity time-validity-hidden" id="timeValidity"></span>
			<p>Deliver Time</p>
		</div>
	</div>

	<div class="orderSummary">
		<div class="inputFieldLabel">
			<h2>Summary of Order:</h2>
		</div>
		<div class = "listOfOrder">
				<table class="table-list-order">
					<tr class = "table-header-row">
						<th>Product Name</th>
						<th>Price</th>
						<th>Quantity</th>
					</tr>
					<c:forEach var="order" varStatus="loop" items="${orderDetails}">
					<script>displayOrderFormattedPrice(${order.orderItemID}, ${order.orderPrice}); priceQuantityProduct(${order.orderItemID},  ${order.orderPrice});</script>
						<tr id = "row-order-info">
							<td><h4 class = "label-product-name" id = "order-product-name">${order.orderName}</h4></td>
							<td><h4 class = "label-price" id = "order-product-price-${order.orderItemID}"></h4></td>
							<td>
								<div class="quantity-input">
                                    <button type="button" class="button-decrease-order-quantity" 
                                    id="button-decrease-order-quantity" 
                                    onclick = "decreaseOrderQuan(${order.orderItemID}); priceQuantityProduct(${order.orderItemID}, ${order.orderPrice});
                                    updateTotalPrice(); ">-</button>
                                    <input type="text" name="quantity${loop.index}" class = "order-quantity"
                                    id="order-quantity-${order.orderItemID}" value="${order.orderquantity}">
                                    <button type="button" class="button-increase-order-quantity"
                                    id="button-increase-order-quantity" 
                                    onclick = "increaseOrderQuan(${order.orderItemID}); priceQuantityProduct(${order.orderItemID}, ${order.orderPrice});
                                    updateTotalPrice(); " >+</button>
                                </div>
							<input id ="itemId" type="hidden" name="itemId${loop.index}" value="${order.orderItemID}">
							<input type="hidden" id = "old-quantity" name="oldQuantity${loop.index}" value="${order.orderquantity}">
							</td>
						</tr>
					</c:forEach>
				</table>
		</div>
	</div>
	<div class="total-Price">
		<h1>Total Price</h1>
		<h1 id = "total-order-price"></h1>
	</div>
	<div class="buttonDiv">
		<button class="cancelButton" onclick="closePopup()">Cancel</button>
		<button class="orderButton" onclick = "checkOut(${order})">Order</button>
	</div>

</div>
<script>
	$(document).ready(function() {
		checkCartSession(boolCart);
		initFunction();
		initOrderDetails();
		updateTotalPrice();
	});
</script>
</html>