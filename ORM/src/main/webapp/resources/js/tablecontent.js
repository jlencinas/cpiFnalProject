var url;

function newUser() {
	$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Add User');
	$('#fm').form('clear');
	$("#dlg1").removeAttr("style").hide();
	$("#dlg2").removeAttr("style").hide();
	$("#dlg").show();
	url = 'Register';
}
function editUser() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg1').dialog('open').dialog('center').dialog('setTitle', 'Edit User');
		$('#fm1').form('load', row);
		$("#dlg").removeAttr("style").hide();
		$("#dlg2").removeAttr("style").hide();
		$("#dlg1").show();
		url = 'Edit';
	}
}
function destroyUser() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg2').dialog('open').dialog('center').dialog('setTitle', 'Edit User');
		$('#fm2').form('load', row);
		$("#dlg").removeAttr("style").hide();
		$("#dlg1").removeAttr("style").hide();
		$("#dlg2").show();
		url = 'Disable';
	}
}

function saveUser() {
	console.log("SAVEUSER");
	$('#fm').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function editRole() {
	console.log("EDITROLE");
	$('#fm1').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg1').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});

}
function changeStatus() {
	console.log("Change Status");
	$('#fm2').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg2').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});

}



//   <!-- =============== Product ================ -->

var url;
function newProduct() {
	$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Add Product');
	$('#fm').form('clear');
	url = 'NewProduct';
}
function editProduct() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg1').dialog('open').dialog('center').dialog('setTitle', 'Edit Product');
		$('#fm1').form('load', row);
		url = 'updateProduct';
	}
}
function saveProduct() {
	$('#fm').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function saveEditProducts() {
	$('#fm1').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg1').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function destroyProduct() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', 'Are you sure you want to remove this product?', function(r) {
			if (r) {
				$.post('destroy_user.java', { id: row.id }, function(result) {
					if (result.success) {
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				}, 'json');
			}
		});
	}
}

//   <!-- =============== Orders ================ -->

var url;

function editOrder() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Edit Product');
		$('#fm').form('load', row);
		url = 'updateOrders';
	}
}
function saveEditOrder() {
	$('#fm').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function destroyOrder() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', 'Are you sure you want to remove this product?', function(r) {
			if (r) {
				$.post('destroy_user.java', { id: row.id }, function(result) {
					if (result.success) {
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				}, 'json');
			}
		});
	}
}
//   <!-- =============== Production ================ -->

function editProduction() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Edit Product');
		$('#fm').form('load', row);
		url = 'updateProductionsOrder';
	}
}

function saveProduction() {
	$('#fm').form('submit', {
		url: url,
		onSubmit: function() {
			return $(this).form('validate');
		},
		success: function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function destroyProduction() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', 'Are you sure you want to remove this product?', function(r) {
			if (r) {
				$.post('destroy_user.java', { id: row.id }, function(result) {
					if (result.success) {
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				}, 'json');
			}
		});
	}
}

function checkInput() {
	$("#save-button-user").click(function() {
		console.log("Pressed");
	});
}


function filterAM(items, rows) {
	console.log(items.toString());
	console.log(rows.toString());
	var pages = Math.ceil(parseInt(items) / parseInt(rows));
	console.log(pages);
	var data = $("#filterAM").val()
	console.log(data);
	console.log("Proceed to filter to only AM Schedules");
	$.ajax({
		url: contextPath + "ordersToday",
		method: "POST",
		data: {
			filter: data,
			page: pages,
			rows: rows
		},
		success: function() {
			console.log("refresh the table")
		}
	});
}

function filterPM(items, rows) {
	console.log(items.toString());
	console.log(rows.toString());
	var pages = Math.ceil(parseInt(items) / parseInt(rows));
	console.log(pages);
	var data = $("#filterPM").val()
	console.log(data);
	console.log("Proceed to filter to only PM Schedules");
	$.ajax({
		url: contextPath + "ordersToday",
		method: "POST",
		data: {
			filter: data,
			page: pages,
			rows: rows
		},
		success: function() {
			console.log("refresh the table")
		}
	});
}

function filterReset() {
	var data = $("#filterReset").val()
	console.log(data);
	console.log("Proceed to reset filter Schedules");
	/*	$.ajax({
			url: contextPath + "ordersToday",
			method: "POST",
			data: {
				filter: data
			}
			success: function() {
				window.location.href = contextPath + "goToLogin";
			}
		});*/
}


function updateEmail() {
	var username = $("#usernameAcc").val();
	var newemail = $('input[name="newemail"]').val();

	$.ajax({
		type: "POST",
		url: "UpdateEmail",
		data: {
			username: username,
			newemail: newemail
		},
		success: function(data) {
			// Handle the server response if necessary
			//$('#dg').edatagrid('reload');
			console.log(username);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});
}

$('#emailUpdateForm').submit(function(event) {
	event.preventDefault(); // Prevent form submission
	updateEmail(); // Call the updateEmail function
});

function updatePassword() {
	var username = $("#usernameAcc").val();
	console.log(username);
	var password = $('input[name="password"]').val();
	var newpass = $('input[name="newpass"]').val();
	var conpass = $('input[name="conpass"]').val();

	$.ajax({
		type: "POST",
		url: "UpdatePassword",
		data: {
			username: username,
			password: password,
			newpass: newpass,
			conpass: conpass
		},
		success: function(data) {
			// Handle the server response if necessary
			$('#dg').edatagrid('reload');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Error: " + errorThrown);
		}
	});
}

function extractPageAndRows() {
	var pagerOptions = $('#dg').datagrid('getPager').data("pagination").options;
	var page = pagerOptions.pageNumber;
	var rows = pagerOptions.pageSize;
	return { page: page, rows: rows };
}

$('#passwordUpdateForm').submit(function(event) {
	event.preventDefault(); // Prevent form submission
	updatePassword(); // Call the updateEmail function
});

function downloadCSV() {
	$.ajax({
		url: contextPath + "pages/CSV",
		method: "POST",
		success: function() {
			console.log("Downloaded CSV")
		}
	});
}

function downloadPDF() {
	$.ajax({
		url: contextPath + "pages/PDF",
		method: "POST",
		success: function() {
			console.log("Downloaded PDF")
		}
	});
}

function redirectProperSessionAdmin(roleID) {
	console.log("Role ID: " + roleID);
	if (roleID != 1) {
		$.ajax({
			url: contextPath + "roleSelectSession",
			data: {
				roleID: roleID
			},
			method: "POST",
			success: function(output, status, xhr) {
				console.log(xhr.getResponseHeader('USER_ACCOUNT'));
				if (xhr.getResponseHeader('USER_ACCOUNT') == 2) {
					window.location.href = contextPath + "producerAccount";
					console.log("Producer here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 3) {
					window.location.href = contextPath + "orderTakerAccount";
					console.log("Order here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 4) {
					window.location.href = contextPath + "auditorAccount";
					console.log("Auditor here");
				}
			}
		});
	}
	else {
		console.log("Welcome Admin!");
	}
}

function redirectProperSessionProduction(roleID) {
	if (roleID != 2) {
		$.ajax({
			url: contextPath + "roleSelectSession",
			data: {
				roleID: roleID
			},
			method: "POST",
			success: function(output, status, xhr) {
				console.log(xhr.getResponseHeader('USER_ACCOUNT'));
				if (xhr.getResponseHeader('USER_ACCOUNT') == 1) {
					window.location.href = contextPath + "adminAccount";
					console.log("Admin here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 3) {
					window.location.href = contextPath + "orderTakerAccount";
					console.log("Order here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 4) {
					window.location.href = contextPath + "auditorAccount";
					console.log("Auditor here");
				}
			}
		});
	}
	else {
		console.log("Welcome Production!");
	}
}

function redirectProperSessionOrderTaker(roleID) {
	if (roleID != 3) {
		$.ajax({
			url: contextPath + "roleSelectSession",
			data: {
				roleID: roleID
			},
			method: "POST",
			success: function(output, status, xhr) {
				console.log(xhr.getResponseHeader('USER_ACCOUNT'));
				if (xhr.getResponseHeader('USER_ACCOUNT') == 1) {
					window.location.href = contextPath + "adminAccount";
					console.log("Admin here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 2) {
					window.location.href = contextPath + "producerAccount";
					console.log("Producer here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 4) {
					window.location.href = contextPath + "auditorAccount";
					console.log("Auditor here");
				}
			}
		});
	}
	else {
		console.log("Welcome Order Taker!");
	}
}

function redirectProperSessionAuditor(roleID) {
	if (roleID != 4) {
		$.ajax({
			url: contextPath + "roleSelectSession",
			data: {
				roleID: roleID
			},
			method: "POST",
			success: function(output, status, xhr) {
				console.log(xhr.getResponseHeader('USER_ACCOUNT'));
				if (xhr.getResponseHeader('USER_ACCOUNT') == 1) {
					window.location.href = contextPath + "adminAccount";
					console.log("Admin here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 2) {
					window.location.href = contextPath + "producerAccount";
					console.log("Producer here");
				}
				else if (xhr.getResponseHeader('USER_ACCOUNT') == 3) {
					window.location.href = contextPath + "orderTakerAccount";
					console.log("Order here");
				}
			}
		});
	}
	else {
		console.log("Welcome Audit!");
	}
}
