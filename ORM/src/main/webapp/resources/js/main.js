function checkCartSession(boolCart) {
	console.log(boolCart);
	var cartLogo = document.getElementById("basket-logo");
	if (boolCart.toString() === "false") {
		console.log("There's no session!");
		cartLogo.classList.add("disabled-cart");
	}
	else {
		console.log("There's a session!");
		cartLogo.classList.remove("disabled-cart");
	}
}

function initConfNum(confnum) {
	$("#confirmation-num").text(confnum);
}


function containsUppercase(str) {
	return /[A-Z]/.test(str);
}
function constainsLowercase(str) {
	return /[a-z]/.test(str);
}
function containsNumber(str) {
	return /\d/.test(str);
}

var popup = document.getElementById("card-container1");
var header = document.getElementById("myHeader");
var main = document.getElementById("myMain");
var footer = document.getElementById("myFooter");
function openPopup() {
	openOrderDesc(contextPath);
	popup.classList.add("open-popup");
	header.style.filter = "blur(8px)";
	main.style.filter = "blur(8px)";;
	footer.style.filter = "blur(8px)";
	header.style.pointerEvents = "none";
	main.style.pointerEvents = "none";
	footer.style.pointerEvents = "none";
}

function closePopup() {
	popup.classList.remove("open-popup");
	header.style.filter = "none";
	main.style.filter = "none";
	footer.style.filter = "none";
	header.style.pointerEvents = "all";
	main.style.pointerEvents = "all";
	footer.style.pointerEvents = "all";
	closeOrderDetails();
}


function openConfirmationPopup() {
	var popup = document.getElementById("card-container1");
	var header = document.getElementById("myHeader");
	var main = document.getElementById("myMain");
	var footer = document.getElementById("myFooter");
	openOrderConfirmationNum(contextPath);
	popup.classList.add("open-popup");
	header.style.filter = "blur(8px)";
	main.style.filter = "blur(8px)";;
	footer.style.filter = "blur(8px)";
	header.style.pointerEvents = "none";
	main.style.pointerEvents = "none";
	footer.style.pointerEvents = "none";

}

function closeConfirmationPopup() {
	var popup = document.getElementById("card-container1");
	var header = document.getElementById("myHeader");
	var main = document.getElementById("myMain");
	var footer = document.getElementById("myFooter");
	popup.classList.remove("open-popup");
	header.style.filter = "none";
	main.style.filter = "none";
	footer.style.filter = "none";
	header.style.pointerEvents = "all";
	main.style.pointerEvents = "all";
	footer.style.pointerEvents = "all";
	closeOrderDetails();
}

function openTrackOrderPopup() {
	var popup = document.getElementById("card-container1");
	var header = document.getElementById("myHeader");
	var main = document.getElementById("myMain");
	var footer = document.getElementById("myFooter");
	openTrackOrderInput(contextPath);
	popup.classList.add("open-popup");
	header.style.filter = "blur(8px)";
	main.style.filter = "blur(8px)";;
	footer.style.filter = "blur(8px)";
	header.style.pointerEvents = "none";
	main.style.pointerEvents = "none";
	footer.style.pointerEvents = "none";
}

function closeTrackOrderPopup() {
	var popup = document.getElementById("card-container1");
	var header = document.getElementById("myHeader");
	var main = document.getElementById("myMain");
	var footer = document.getElementById("myFooter");
	popup.classList.remove("open-popup");
	header.style.filter = "none";
	main.style.filter = "none";
	footer.style.filter = "none";
	header.style.pointerEvents = "all";
	main.style.pointerEvents = "all";
	footer.style.pointerEvents = "all";
	closeOrderDetails();
}

