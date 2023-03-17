

function initTestButton(){
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

function initLoginButton(contextPath){
	console.log(contextPath);
	$("#span-login").click(function() {
		loginButton(contextPath);
	});
}


function loginButton (contextPath){
	console.log("Proceeding to Login Page");
	$.ajax({
		url: contextPath + "goToLogin",
		method: "POST",
		success: function() {
			window.location.href = contextPath + "goToLogin";
		}
	});
}


function redirectHome (contextPath){
	console.log("Proceeding to Home");
	$.ajax({
		url: contextPath + "logincontroller",
		method: "POST",
		success: function() {
			window.location.href = contextPath +  "index.jsp";
		}
	});
}

function redirectShop(contextPath){
	console.log("Proceeding to Shop");
	
	$.ajax({
		url: contextPath + "pages/DisplayProduct",
		method: "POST",
		success: function(result) {
			$("#myMain").html(result);
		}
	});
}

function redirectAbout(contextPath){
	console.log("Proceeding to About");
	console.log(contextPath + "/About");
}

function redirectContact(contextPath){
	console.log("Proceeding to Contact");
	console.log(contextPath + "/Contact");
}

function addCart(contextPath){
	var quan = $('#product-quantity').val();
	var inum = $('#cart-value').val();
	console.log(inum);
	/*$.ajax({
		url: contextPath + "pages/AddOrderDetails",
		method: "POST",
		data: {
			quantity: quan,
			itemnum: inum
		},
		success: function() {
			console.log("added Cookie");
		}
	});*/
}

