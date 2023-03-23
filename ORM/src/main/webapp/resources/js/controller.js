function initTestButton(contextPath, productID) {
	$("#testOutputButton").click(function() {
		testButton(contextPath);
	});

	$("#a-shop").click(function() {
		redirectShop(contextPath);
	});

	$("#a-about").click(function() {
		redirectAbout(contextPath);
	});

	$("#a-contact").click(function() {
		redirectContact(contextPath);
	});
}

function initLoginButton(contextPath) {
	$("#span-login").click(function() {
		loginButton(contextPath);
	});
}


function loginButton(contextPath) {
	console.log("Proceeding to Login Page");
	$.ajax({
		url: contextPath + "goToLogin",
		method: "POST",
		success: function() {
			window.location.href = contextPath + "goToLogin";
		}
	});
}


function redirectHome(contextPath) {
	console.log("Proceeding to Home");
	$.ajax({
		url: contextPath + "logincontroller",
		method: "POST",
		success: function() {
			window.location.href = contextPath + "index.jsp";
		}
	});
}

function redirectShop(contextPath) {
	console.log("Proceeding to Shop");
	$.ajax({
		url: contextPath + "pages/DisplayProduct",
		method: "POST",
		success: function(result) {
			history.pushState(null, "", "/ORM/pages/DisplayProduct");
			$("#myMain").html(result);
		}
	});
}

function openOrderDesc(contextPath) {
	console.log("Proceeding to Order Details");
	$.ajax({
		url: contextPath + "pages/DisplayOrderSummary",
		method: "POST",
		success: function(result) {
			$("#card-container1").html(result);
		}
	});
}


function redirectAbout(contextPath) {
	console.log("Proceeding to About");
	console.log(contextPath + "/About");
}

function redirectContact(contextPath) {
	console.log("Proceeding to Contact");
	console.log(contextPath + "/Contact");
}


function addCart(contextPath, productID, productPrice, productDesc, boolCart) {
	let idValue = "#product-quantity-" + productID.toString();
	var productQuantity = $(idValue).val();
	var productTotalPrice = 0;
	console.log(contextPath);
	console.log(productID);
	console.log(productPrice);
	console.log(productDesc);
	console.log(productQuantity);
	productTotalPrice = parseFloat(productQuantity) * parseFloat(productPrice);
	addProduct(productQuantity, productTotalPrice);
	$.ajax({
		url: contextPath + "pages/AddOrderDetails",
		method: "POST",
		data: {
			quantity: productQuantity,
			itemnum: productID,
			price: productTotalPrice
		},
		success: function() {
			boolCart = "true";
			checkCartSession(boolCart);
			$(idValue).val(1);
		}
	});
}

function removeCart(productID) {
	closePopup();
	$.ajax({
		url: contextPath + "pages/deleteItem",
		method: "POST",
		data: {
			itemid: productID,
		},
		success: function() {
			openPopup();
		}
	});
	console.log("Total Price: " + totalPrice);
	console.log("Quantity: " + quantity);
	console.log("Bool Cart: " + boolCart);
}

function editCart(contextPath, productID){
	let idValue = "#order-quantity-" + orderItemID.toString();
	var productQuantity = formatNumberEdit($(idValue).val());
	
	let idValue2 = "#order-product-price-" + orderItemID.toString();
	var productQuantity = formatNumberEdit($(idValue2).val());
	$.ajax({
		url: contextPath + "editQuantity",
		method: "POST",
		data: {
			productid: productID,
			productprice: productPrice,
			newquantity: productQuantity,
		},
		success: function() {
			console.log("changed");
		}
	});
}

function formatNumberEdit(number) {
	var total = Number.parseFloat(number.replace(/[₱, ]/g, ''))
	return total;
}

function checkOut() {
	var fName = $("#firstName").val().toString();
	var lName = $("#lastName").val().toString();
	var deliveryDate = $("#inputDate").val().toString();
	var deliveryTime = $("#inputTime").val().toString();
	var contactNum = $("#telField").val().toString();
	var checkValidityTime = document.getElementById("inputTime");
	var checkValidityContactNum = document.getElementById("telField");
	var productinfo_array = [];

	console.log(checkValidityTime.value);
	console.log(checkValidityContactNum.value);
	console.log(checkValidityTime.checkValidity());
	console.log(checkValidityContactNum.checkValidity());
	
	
	
	if ((fName == "") || (lName == "") || (deliveryDate == "") || 
	(deliveryTime == "") || (contactNum == "")){
		alert("Please fill up the Field and necessary details!");
	}
	else{
		if ( (checkValidityTime.checkValidity() == true) && (checkValidityContactNum.checkValidity() == true)) {
			$("tr#row-order-info").each(function(index) {
				productinfo_array.push({
					"itemId": $(this).find("#itemId").val().toString(),
					"oldQuantity": $(this).find("#old-quantity").val().toString(),
					"quantity": $(this).find(".order-quantity").val().toString()
				});
			});
			
			closeOrderDetails();
			$.ajax({
				type: "POST",
				url: "NewOrder",
				data: {
					t1: fName,
					t2: lName,
					t3: contactNum,
					dates: deliveryDate,
					times: deliveryTime,
					allParams: productinfo_array
				},
				success: function(response) {
					closePopup();
					totalPrice = 0;
					quantity = 0;
					console.log(totalPrice);
					console.log(quantity);
					checkQuan(quantity, totalPrice);
					openConfirmationPopup()
				}
		
			});
		}
		else{
			alert("Please check the validity of your input information!");
		}
	}
	

	



}


function openOrderConfirmationNum(contextPath) {
	console.log("Showing Confirmation Number");
	$.ajax({
		url: contextPath + "ConfOrder",
		method: "POST",
		success: function(result) {
			$("#card-container1").html(result);
		}
	});
}

function openTrackOrderInput(contextPath){
	console.log("Showing Input Track Order");
	$.ajax({
		url: contextPath + "PopTrackOrder",
		method: "POST",
		success: function(result) {
			$("#card-container1").html(result);
		}
	});
}


function enterTrackingOrder(){
	var trackingCode = $("#input-tracking-code").val();
	$.ajax({
		url: contextPath + "orderDetails",
		method: "POST",
		data: {
			confirmationNum: trackingCode
		},
		success: function(result) {
			closeOrderDetails();
			$("#card-container1").html(result);
		}
	});
}

function forgotPassword(){
	var uName = $("#username-input").val();
	var emailAdd = $("#email-input").val();
	console.log("Proceeding forgot password")
	$.ajax({
		url: contextPath + "Forgot",
		method: "POST",
		data: {
			username:uName,
			email:emailAdd
		},
		success: function(result) {
			alert("Password Reseted Successfully! Please check your E-mail");
			window.location.href = contextPath + "login.jsp";
		},
		error :function(){
			alert("Error! Password Reset Fail!");
		}
	});
}




/*$('#button-increase-order-quantity').click(function() {
	var orderDetails = [];
	var fName = $("#firstName").val();
	var lName = $("#firstName").val();
	var totalPrice = $("#total-order-price").val();
    
	$('input[type=number]').each(function() {
		var quantity = $(this).val();
		var oldQuantity = $(this).siblings('input[name^=oldQuantity]').val();
		var itemId = $(this).siblings('input[name^=itemId]').val();
		var orderDetail = { quantity: quantity, oldQuantity: oldQuantity, itemId: itemId };
		orderDetails.push(orderDetail);
	});

	$.ajax({
		type: "POST",
		url: "pages/NewOrder",
		data: { orderDetails: orderDetails },
		success: function(response) {
			console.log(response);
		}
	});
}*/

