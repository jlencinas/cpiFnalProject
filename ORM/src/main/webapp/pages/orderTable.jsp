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
				<h1>ORDER LIST</h1>
				<br><br>
			</div>

			<center>
				<table id="dg" class="easyui-datagrid" url="listOfOrders" toolbar="#toolbar" pagination="true" fitColumns="true" singleSelect="true" style="width: 95%; height: 425px;">
					<thead>
						<tr>
							<th field="order_id" width="25%">Order Number</th>
							<th field="order_status" width="25%">Order Status</th>
							<th field="delivery_date" width="25%">Expected Delivery Date</th>
							<th field="payment_status" width="26%">Payment Status</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar" style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">Edit Order</a>
				</div>
				<div id="dlg" class="easyui-dialog" style="width: 450px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Edit Order</h3>
						<div style="margin-bottom: 10px">
						<input name="order_id" class="easyui-textbox" readonly label="Order Id:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="orderStatus" required="true" label="Order Status:" labelPosition="top" style="width: 100%;">
								<option value="1" selected>Pending</option>
								<option value="2">Ready for Pick Up</option>
								<option value="3">Completed</option>
								<option value="50">Cancelled</option>
								<option value="90">Rejected</option>
							</select>
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="paymentStatus" required="true" label="Payment Status:" labelPosition="top" style="width: 100%;">
								<option value="1" selected>Not Paid</option>
								<option value="2">Paid</option>
							</select>
						</div>
						<div style="margin-bottom: 10px">
						<input name="remarks" class="easyui-textbox" multiline="true" label="Remarks:" style="width: 100%; height: 120px">
						</div>
					</form>
					<div id="dlg-buttons">
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveEditOrder()" style="width: 90px">Save</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">Cancel</a>
					</div>
				</div>
			<br><br>
			<!-- ================ Table2 ================= -->
			<div style="text-align: center;">
				<br><br>
				<h1>ORDER SUMMARY</h1>
				<br><br>
			</div>
			<center>
				<table class="easyui-datagrid" url="ShowSummary" pagination="true" fitColumns="true" singleSelect="true" style="width: 95%; height: 400px;">
					<thead>
						<tr>
							<th field="prod_id" width="22%">Product ID</th>
							<th field="delivery_date" width="20%">Delivery Date</th>
							<th field="payment_status" width="20%">Payment Status</th>
							<th field="quantity" width="20%">Quantity</th>
							<th field="price" width="20%">Total Price</th>
						</tr>
					</thead>
				</table>
			<br><br>
			<script src="/ORM/resources/js/admin.js"></script>
			<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>	
</body>
</html>
