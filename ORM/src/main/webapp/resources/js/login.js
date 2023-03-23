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

	$("#forgot-pass-button").click(function() {
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
		success: function(output, status, xhr) {
			console.log(output);
			console.log(status);
			console.log(xhr.getResponseHeader('REQUIRES_AUTH'));
			if (xhr.getResponseHeader('REQUIRES_AUTH') == 2){
				console.log("Wrong Username or Password");
				window.location.href = contextPath + "WrongUsernameOrPass";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == 3){
				console.log("Account Disabled");
				window.location.href = contextPath + "DisabledAccount";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == 4){
				console.log("Account Does Not Exist");
				window.location.href = contextPath + "AccountDoesNotExist";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == null){
				console.log("Access Granted");
				window.location.href = contextPath + "pages/adminTable.jsp";
			}
		}
	});
}

function forgotPass() {
	console.log("proceeding to forgot pass");
	$.ajax({
		url: contextPath + "goToForgot",
		method: "POST",
		success: function() {
			window.location.href = contextPath + "pages/forgotpassword.jsp";
		}
	});
}





