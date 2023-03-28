function initOrderDetails() {

	$(".order-quantity").on('keypress', function(event) {
		var regex = new RegExp("^[0-9,]$");
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		if (!regex.test(key)) {
			event.preventDefault();
			return false;
		}
	});

	$('.order-quantity').on('keyup', function() {
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

function decreaseOrderQuan(orderItemID) {
	let idValue = "#order-quantity-" + orderItemID.toString();
	var productQuantity = $(idValue).val().toString();
	var convertedProductQuan = parseInt(productQuantity.replaceAll(',', ''));
	if (convertedProductQuan <= 1) {
		$(idValue).val(0);
	}
	else {
		convertedProductQuan--;
		$(idValue).val(formatNumber(convertedProductQuan).toString());
	}
}

function increaseOrderQuan(orderItemID) {
	let idValue = "#order-quantity-" + orderItemID.toString();
	var productQuantity = $(idValue).val().toString();
	var convertedProductQuan = parseInt(productQuantity.replaceAll(',', ''));
	convertedProductQuan++;
	$(idValue).val(formatNumber(convertedProductQuan).toString());
}


function priceQuantityProduct(orderItemID, orderPrice) {
	let idValue = "#order-quantity-" + orderItemID.toString();
	let priceIdValue = "#order-product-price-" + orderItemID.toString();
	var productQuantity = $(idValue).val().toString();
	var convertedProductQuan = parseInt(productQuantity.replaceAll(',', ''));
	var totalQuotientPrice;

	$(priceIdValue).addClass("label-price");
	$(priceIdValue).css("cursor", "none");
	$(priceIdValue).css("text-align", "left");
	$(priceIdValue).css("color", "black");
	let functionString = `removeCart(${orderItemID})`;
	if (convertedProductQuan == 0) {
		$(priceIdValue).text("Removed");
		$(priceIdValue).attr('onclick', functionString);
		$(priceIdValue).css("cursor", "pointer");
		$(priceIdValue).css("text-align", "center");
		$(priceIdValue).css("color", "#f44336");
		$(priceIdValue).removeClass("label-price");
	}
	else {
		$(priceIdValue).removeAttr('onclick', 'stopMoving()');
		totalQuotientPrice = convertedProductQuan * orderPrice;
		$(priceIdValue).text("₱ " + formatNumberPrice(Number.parseFloat(totalQuotientPrice).toFixed(2)));
	}
}

function formatNumber(number) {
	const num = number.toLocaleString("en-US");
	return num;
}

function formatNumberPrice(num) {
	var options = {
		minimumFractionDigits: 2,
		maximumFractionDigits: 2
	};
	const numOutput = Number(num).toLocaleString("en-US", options);
	return numOutput;
}

function displayOrderFormattedPrice(productID, productPrice) {
	let idValue = "#order-product-price-" + productID.toString();
	var floatVal = Number.parseFloat(productPrice).toFixed(2);
	$(idValue).text("₱ " + formatNumberPrice(floatVal));
}


function updateTotalPrice() {
	var total = 0;
	$('.label-price').each(function() {
		total += parseFloat($(this).text().replace(/[₱, ]/g, ''))
	})
	var options = {
		minimumFractionDigits: 2,
		maximumFractionDigits: 2
	};
	const numOutput = Number(total).toLocaleString("en-US", options);
	$('#total-order-price').text("₱ " + numOutput);
}


function checkDeliveryDate() {
	//Date Constraintns
	var dtToday = new Date();
	var hour = dtToday.getHours();
	var minutes = dtToday.getMinutes();
	var month = dtToday.getMonth() + 1;
	var day = dtToday.getDate();
	var year = dtToday.getFullYear();
	var hourString =
		hour < 10
			? (hourString = "0" + hour.toString())
			: (hourString = hour.toString());
	var minString =
		minutes < 10
			? (minString = "0" + minutes.toString())
			: (minString = minutes.toString());
	var timeString = hourString + ":" + minString;

	if ((hour >= 18 && minutes >= 55) || hour > 18) {
		day = dtToday.getDate() + 1;
	}
	if (month < 10) month = "0" + month.toString();
	if (day < 10) day = "0" + day.toString();

	var maxDate = year + "-" + month + "-" + day;
	var dtRevised = new Date(maxDate);
	$("#inputDate").attr("min", maxDate);

	//Time Contraints
	const sourceDate = document.getElementById("inputDate");
	const sourceTime = document.getElementById("inputTime");
	if (sourceDate.value != "") {
		const inputHandler = function(e) {
			var dtInput = new Date(sourceDate.value);
			var dtNow = new Date();
			// if (dtInput.getDay() == dtRevised.getDay()){
			//   $("#inputTime").attr("min", "09:00");
			//   $("#inputTime").attr("max", "18:55");
			// }
			if (dtInput > dtRevised) {
				$("#inputTime").attr("min", "09:00");
				$("#inputTime").attr("max", "18:55");
			} else {
				if (((hour >= 18 && minutes >= 55) || hour > 18)) {
					$("#inputTime").attr("min", "09:00");
					$("#inputTime").attr("max", "18:55");
				} else if ((hour >= 10 && minutes >= 55) || hour > 10) {

					if (hour >= 12 && (hour <= 18 && minutes <= 55)) {
						$("#inputTime").attr("min", timeString);
						$("#inputTime").attr("max", "18:55");
					} else {
						$("#inputTime").attr("min", "12:00");
						$("#inputTime").attr("max", "18:55");
					}
				} else {
					$("#inputTime").attr("min", "09:00");
					$("#inputTime").attr("max", "18:55");
				}
			}
		};
		sourceTime.addEventListener("input", inputHandler);
		sourceTime.addEventListener("propertychange", inputHandler);
	} else {
	}
	// for IE8
}

function checkTelInput() {
	const source = document.getElementById("telField");
	const result = document.getElementById("telValidity");

	const inputHandler = function(e) {
		if (e.target.value == null || e.target.value == "") {
			result.classList.add("tel-validity-hidden");
		} else {
			result.classList.remove("tel-validity-hidden");
		}
	};
	source.addEventListener("input", inputHandler);
	source.addEventListener("propertychange", inputHandler); // for IE8
}


function checkTimeInput() {
	const source = document.getElementById("inputTime");
	const result = document.getElementById("timeValidity");
	const inputHandler = function(e) {
		if (e.target.value == null || e.target.value == "") {
			result.classList.add("time-validity-hidden");
		} else {
			result.classList.remove("time-validity-hidden");
		}
	};
	source.addEventListener("input", inputHandler);
	source.addEventListener("propertychange", inputHandler); // for IE8
}

function boolDate() {
	var sourceDate = document.getElementById("inputDate");
	var sourceTime = document.getElementById("inputTime");
	var timeval = document.getElementById("timeValidity");
	if (sourceDate.value == null || sourceDate.value == "") {
		sourceTime.value = "";
		sourceTime.setAttribute('disabled', '');
		document.getElementById("delivery-date-warning").innerHTML = `Please select a delivery date first! Before selecting delivery time.`;
	} else {
		document.getElementById("delivery-date-warning").innerHTML = ` `;
		sourceTime.value = "";
		timeval.classList.add("time-validity-hidden");
		sourceTime.removeAttribute('disabled');
	}
}

