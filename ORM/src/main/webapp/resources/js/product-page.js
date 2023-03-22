function closeOrderDetails() {
	document.getElementById("card-container1").innerHTML = ``;
}


function initButton() {
	$(".product-quantity").on('keypress', function(event) {
		var regex = new RegExp("^[0-9,]$");
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		if (!regex.test(key)) {
			event.preventDefault();
			return false;
		}
	});
	
	$('.product-quantity').on('keyup', function() {
		updateTextView($(this));
	});
	
	
}


function updateTextView(_obj) {
	var num = getNumber(_obj.val());
	if (num == 0) {
		_obj.val(1);
	} else {
		_obj.val(num.toLocaleString());
	}
}
function getNumber(_str) {
	var arr = _str.split('');
	var out = new Array();
	for (var cnt = 0; cnt < arr.length; cnt++) {
		if (isNaN(arr[cnt]) == false) {
			out.push(arr[cnt]);
		}
	}
	return Number(out.join(''));
}

function decreaseQuan(productID) {
	console.log("Decreased");
	let idValue = "#product-quantity-" + productID.toString();
	var productQuantity = $(idValue).val().toString();
	var convertedProductQuan = parseInt(productQuantity.replaceAll(',', ''));
	if (convertedProductQuan < 2) {
		$(idValue).val(1);
	}
	else {
		convertedProductQuan--;
		$(idValue).val(formatNumber(convertedProductQuan).toString());
	}
}

function increaseQuan(productID) {
	let idValue = "#product-quantity-" + productID.toString();
	var productQuantity = $(idValue).val().toString();
	var convertedProductQuan = parseInt(productQuantity.replaceAll(',', ''));
	convertedProductQuan++;
	$(idValue).val(formatNumber(convertedProductQuan).toString());
}


function displayFormattedPrice(productID, productPrice) {
	let idValue = "#product-price-" + productID.toString();
	var floatVal = Number.parseFloat(productPrice).toFixed(2);
	$(idValue).append("₱ " + formatNumberPrice(floatVal));
}

function formatNumber(number) {
	const num = number.toLocaleString("en-US");
	return num;
}

function formatNumberPrice(num){
	var options = { 
	  minimumFractionDigits: 2,
	  maximumFractionDigits: 2 
	};
	const numOutput = Number(num).toLocaleString("en-US", options);
	return numOutput;
}



function initFunction(){
    checkQuan(quantity, totalPrice);
}

function addProduct(quanItem, productTotalPrice){
    
	var numQuan = Number(quantity); //Convert into Number
	numQuan+= Number(quanItem); // Increase Total Item Quantity
	quantity = numQuan; //Set the new Total Quantity
	
	var numPrice = parseFloat(totalPrice);
	numPrice+= parseFloat(productTotalPrice);
	totalPrice = numPrice;
    checkQuan(quantity, totalPrice);
}

function removeProduct(){
    var totalPrice = 0;
    quantity--;
    itemPrice = itemPrice * quantity;
    totalPrice -= itemPrice;
    checkQuan(quantity, totalPrice);
}


function checkQuan(quantity, totalPrice){
    var quanDiv = $("#basket-logo-quantity");
    var priceDiv = $("#basket-logo-totalprice");
    var containerDiv = $("#basket-logo-container");
    var quanText = $("#basket-logo-quantity-text");
    var priceText = $("#basket-logo-totalprice-text");
    if (quantity != 0){
        containerDiv.removeClass("disabled-container");
        quanDiv.removeClass("disabled-logo-quantity");
        priceDiv.removeClass("disabled-logo-totalprice");
        quanText.text(formatNumber(quantity));
        priceText.text("₱" + formatNumberPrice(totalPrice));
    }
    else{
        containerDiv.addClass("disabled-container");
        quanDiv.addClass("disabled-logo-quantity");
        priceDiv.addClass("disabled-logo-totalprice");
        quanText.text(0);
        priceText.text("₱" + 0.00);
        console.log("no item");
        console.log("Quantity: " + quantity);
        console.log("Total Price: " + totalPrice);
    }
}
