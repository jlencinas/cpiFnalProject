<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>
<%
HttpSession sesh2 = request.getSession();
Users seshinfo = (Users) sesh2.getAttribute("userAccount");
String usern = seshinfo.getUsername();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CPI Bakery</title>
<!-- ======= Styles ====== -->
<link rel="stylesheet" href="/ORM/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/tablecontent.js"/>"></script>
<script type="text/javascript">
function updateEmail() {
	var username = '<%=usern%>';
		var newemail = $('input[name="newemail"]').val();

		$.ajax({
			type : "POST",
			url : "UpdateEmail",
			data : {
				username : username,
				newemail : newemail
			},
			success : function(data) {
				// Handle the server response if necessary
				$('#dg').edatagrid('reload');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("Error: " + errorThrown);
			}
		});
	}

	$('#emailUpdateForm').submit(function(event) {
		event.preventDefault(); // Prevent form submission
		updateEmail(); // Call the updateEmail function
	});

	function updatePassword() {
		var username = '<%=usern%>';
			var password = $('input[name="password"]').val();
			var newpass = $('input[name="newpass"]').val();
			var conpass = $('input[name="conpass"]').val();

			$.ajax({
				type : "POST",
				url : "UpdatePassword",
				data : {
					username : username,
					password : password,
					newpass : newpass,
					conpass : conpass
				},
				success : function(data) {
					// Handle the server response if necessary
					$('#dg').edatagrid('reload');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error: " + errorThrown);
				}
			});
		}

		$('#passwordUpdateForm').submit(function(event) {
			event.preventDefault(); // Prevent form submission
			updatePassword(); // Call the updateEmail function
		});	
</script>
</head>
<body>
	<!-- =============== Navigation ================ -->
	<div class="container">
		<div class="navigation">
			<ul>
				<li>
					<a href="#"> <span class="icon"> <img src="/ORM/resources/images/Logo-01.svg">
					</span> <span class="title">CPI Bakery</span>
					</a>
				</li>
				<li>
					<a href="adminTable.jsp"> <span class="icon"> <ion-icon name="person-circle-outline"></ion-icon>
					</span> <span class="title">Admin</span>
					</a>
				</li>
				<li>
					<a href="productTable.jsp"> <span class="icon"><ion-icon name="cube-outline"></ion-icon>
					</span> <span class="title">Products</span>
					</a>
				</li>
				<li>
					<a href="orderTable.jsp"> <span class="icon"> <ion-icon name="cart-outline"></ion-icon>
					</span> <span class="title">Orders</span>
					</a>
				</li>
				<li>
					<a href="productionTable.jsp"> <span class="icon"><ion-icon name="cash-outline"></ion-icon>
					</span> <span class="title">Production</span>
					</a>
				</li>
				<li>
					<a href="reportTable.jsp"> <span class="icon"> <ion-icon name="newspaper-outline"></ion-icon>
					</span> <span class="title">Reporting</span>
					</a>
				</li>
			</ul>
		</div>
		<!-- ========================= Main ==================== -->
		<div class="main">
			<div class="topbar">
				<div class="toggle">
					<ion-icon name="menu-outline"></ion-icon>
				</div>
				<div class="titles"></div>
				<div class="user">
					<div class="dropdown">
						<button onclick="myFunction()" class="dropbtn"><%=usern%></button>
						<div id="myDropdown" class="dropdown-content">
							<a href="#" onclick="openEmailForm()"><ion-icon name="mail-outline"></ion-icon>Change Email Address</a> 
							<a href="#" onclick="openPasswordForm()"><ion-icon name="lock-open-outline"></ion-icon>Change Password</a> 
							<a href="#"><ion-icon name="log-out-outline"></ion-icon>Logout</a>
						</div>
					</div>
					<div class="modal-overlay"></div>
					<div id="emailForm" class="form-popup">
						<form class="form-container" id="emailUpdateForm">
							<input type="hidden" value="<%=usern%>" name="username">
							<h2>Change Email Address</h2>
							<label for="email"><b>New Email Address</b></label> 
							<input type="text" placeholder="Enter New Email Address" name="newemail" required>
							<button type="submit" class="btn" id="updateEmailBtn" onclick="updateEmail()">Save Changes</button>
							<br>
							<button type="button" class="btn cancel" onclick="closeEmailForm()">Close</button>
						</form>
					</div>
					<div id="passwordForm" class="form-popup">
						<form class="form-container" id="passwordUpdateForm">
							<input type="hidden" value="<%=usern%>" name="username">
							<h2>Change Password</h2>
							<label for="current_password"><b>Current Password</b></label> 
							<input type="password" placeholder="Enter Current Password" name="password" required> <label for="new_password"><b>New Password</b></label> <input type="password" placeholder="Enter New Password" name="newpass" required>
							<label for="confirm_password"><b>Confirm New Password</b></label>
							<input type="password" placeholder="Confirm New Password" name="conpass" required>
							<button type="submit" class="btn" id="updatePasswordBtn" onclick="updatePassword()">Save Changes</button>
							<br>
							<button type="button" class="btn cancel" onclick="closePasswordForm()">Close</button>
						</form>
					</div>
				</div>
			</div>
			<!-- ================ Table ================= -->
			<div style="text-align: center;">
				<br><br>
				<h1>PRODUCT LIST</h1>
				<br><br>
			</div>
			<center>
				<table id="dg" class="easyui-datagrid" url="listOfProducts"
					toolbar="#toolbar" collapsible="true" pagination="true"
					fitColumns="true" singleSelect="true"
					style="width: 95%; height: 425px;">
					<thead>
						<tr>
							<th field="product_name" width="15%">Product Name</th>
							<th field="product_id" width="10%">Product Code</th>
							<th field="product_description" width="30%">Product Description</th>
							<th field="product_picture" width="25%">Product Picture</th>
							<th field="product_status" width="11%">Status</th>
							<th field="product_price" width="11%">Price</th>
						</tr>
					</thead>
				</table>
				
				<div id="toolbar" style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newProduct()">New Product</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editProduct()">Edit Product</a>
				</div>
				<div id="dlg" class="easyui-dialog" style="width: 450px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Add Product</h3>
						<div style="margin-bottom: 10px">
							<input name="productName" class="easyui-textbox" required="true" label="Product Name:" labelPosition="top" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input class="easyui-textbox" name="description" required="true" label="Description:" labelPosition="top" multiline="true" style="width: 100%; height: 120px">
						</div>
						<div style="margin-bottom: 10px">
							<input name="url" class="easyui-textbox" required="true" label="Product Picture:" labelPosition="top" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="status" required="true" label="status:" labelPosition="top" style="width: 100%;">
								<option value="0">Disabled</option>
								<option value="1">Available</option>
								<option value="2">Removed</option>
							</select>
						</div>
						<div style="margin-bottom: 10px">
							<input name="price" class="easyui-textbox" required="true" label="Price:" style="width: 100%">
						</div>
					</form>
					<div id="dlg-buttons">
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveEditProducts()" style="width: 90px">Edit</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">Cancel</a>
						
					</div>
				</div>
				<div id="dlg1" class="easyui-dialog" style="width: 450px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm1" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Edit Product</h3>
						<div style="margin-bottom: 10px">
							<input name="product_id" class="easyui-textbox" readonly label="Product ID:" labelPosition="top" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input name="productName" class="easyui-textbox" required="true" label="Product Name:" labelPosition="top" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input class="easyui-textbox" name="description" required="true" label="Description:" labelPosition="top" multiline="true" style="width: 100%; height: 120px">
						</div>
						<div style="margin-bottom: 10px">
							<input name="url" class="easyui-textbox" required="true" label="Product Picture:" labelPosition="top" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="status" required="true" label="status:" labelPosition="top" style="width: 100%;">
								<option value="1">Available</option>
								<option value="0">Disabled</option>
								<option value="2">Removed</option>
							</select>
						</div>
						<div style="margin-bottom: 10px">
							<input name="price" class="easyui-textbox" required="true" label="Price:" style="width: 100%">
						</div>
					</form>
					<div id="dlg-buttons">
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveProduct()" style="width: 90px">Add Product</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">Cancel</a>	
					</div>
				</div>
			<script src="/ORM/resources/js/admin.js"></script>
			<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
