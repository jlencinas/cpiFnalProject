

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

	/*	$("#button-addToCart" + productID).click(function() {
			addCart(contextPath, productID, productPrice, productDesc);
		});*/
}

function initLoginButton(contextPath) {
	console.log(contextPath);
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
			$("#myMain").html(result);
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


function addCart(contextPath, productID, productPrice, productDesc) {
	let idValue = "#product-quantity-" + productID.toString();
	var productQuantity = $(idValue).val();
	console.log(contextPath);
	console.log(productID);
	console.log(productPrice);
	console.log(productDesc);
	console.log(productQuantity);

	$.ajax({
		url: contextPath + "pages/AddOrderDetails",
		method: "POST",
		data: {
			quantity: productQuantity,
			itemnum: productID
		},
		success: function() {
			console.log("added Cookie");
		}
	});
	
	
/*	let link = contextPath + "pages/AddOrderDetails";
	fetch(link, {
		method: 'POST',
		data: {
			quantity: productQuantity,
			itemnum: productID
		}
	}).then((response) => {
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		console.log('Request successfully completed');
	})
		.catch((error) => {
			console.error('There has been a problem while contacting server:', error);
		});
*/
}

