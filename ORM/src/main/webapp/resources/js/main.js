

function testButton(contextPath){
  	console.log(contextPath);
  	console.log("TesT");
}



/*function checkSession() {
	console.log("hello");
	console.log(contextPath);
	$.ajax({
		url: contextPath + "checksessioncontroller",
		method: "POST",
		success: function(result) {
			$("#mainDiv").html(result);
		}
	});
}*/




function containsUppercase(str) {
  return /[A-Z]/.test(str);
}
function constainsLowercase(str) {
  return /[a-z]/.test(str);
}
function containsNumber(str) {
  return /\d/.test(str);
}