function initButtons() {
	$("#loginButton").click(function() {
		var username = $('#username').val();
		var password = $('#password').val();
		if (username == '' || username == null) {
			alert('Please enter Username');
		} else if (password == '' || password == null) {
			alert('Please enter Password');
		} else {
			login(username, password);
		}
	});
	
	
	$("#forgot-pass-button").click(function()  {
		forgotPass();
	});
}

function login(username, password) {
	console.log(username);
	console.log(password);
	console.log(contextPath + "pages/Login");
	$.ajax({
		url: contextPath + "Login",
		method: "POST",
		data: {
			username: username,
			password: password
		},
		success: function() {
			window.location.href = contextPath +  "pages/dashboard.jsp";
		}
	});
}

function forgotPass(){
	console.log("proceeding to forgot pass");
	$.ajax({
		url: contextPath + "pages/Forgot",
		method: "POST",
		success: function() {
			window.location.href = contextPath +  "pages/forgotpassword.jsp";
		}
	});
}