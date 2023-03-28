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
	$.ajax({
		url: contextPath + "Login",
		method: "POST",
		data: {
			username: username,
			password: password
		},
		success: function(output, status, xhr){
			if (xhr.getResponseHeader('REQUIRES_AUTH') == 2) {
				window.location.href = contextPath + "WrongUsernameOrPass";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == 3) {
				window.location.href = contextPath + "DisabledAccount";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == 4) {
				window.location.href = contextPath + "AccountDoesNotExist";
			}
			else if (xhr.getResponseHeader('REQUIRES_AUTH') == 1) {
				window.location.href = contextPath + "roleSelect";
				$.ajax({
					url: contextPath + "roleSelect",
					method: "POST",
					success: function(output, status, xhr) {
						console.log(xhr.getResponseHeader('USER_ACCOUNT'));
						if (xhr.getResponseHeader('USER_ACCOUNT') == 1) {
							window.location.href = contextPath + "adminAccount";
							conssole.log("Admin here");
						}
						else if (xhr.getResponseHeader('USER_ACCOUNT') == 2) {
							window.location.href = contextPath + "producerAccount";
							conssole.log("Producer here");
						}
						else if (xhr.getResponseHeader('USER_ACCOUNT') == 3) {
							window.location.href = contextPath + "orderTakerAccount";
							conssole.log("Order here");
						}
						else if (xhr.getResponseHeader('USER_ACCOUNT') == 4) {
							window.location.href = contextPath + "auditorAccount";
							conssole.log("Auditor here");
						}
					}
				});
			}
		},
		error: function(xhr, status, error) {
			// handle error response
			console.log("First AJAX request failed: " + error);
		}
	});
}

function forgotPass() {
	$.ajax({
		url: contextPath + "goToForgot",
		method: "POST",
		success: function() {
			window.location.href = contextPath + "pages/forgotpassword.jsp";
		}
	});
}





