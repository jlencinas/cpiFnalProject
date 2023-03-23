<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CPI Bakery</title>
<!-- ======= Styles ====== -->
<c:set var="seshinfo" value="${sessionScope.userAccount}" />
	<c:if test="${not empty seshinfo}">
		<c:set var="uid" value="${seshinfo.userId}" />
	</c:if>
<c:set var = "usern" value = "${seshinfo.username}"/>
<c:set var = "roleID" value = "${seshinfo.roleId}"/>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/tablecontent.js"/>"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- =============== Navigation ================ -->
	<div class="container">
		<div class="navigation">
			<ul>
				<li>
					<a href="#"> <span class="icon"> <img class = "logo-image" src="/ORM/resources/images/Logo-01.png">
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
				<li>
					<a href="auditTable.jsp"> <span class="icon"> <ion-icon name="newspaper-outline"></ion-icon>
					</span> <span class="title">Auditing</span>
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
						<button onclick="myFunction()" class="dropbtn">${usern}</button>
						<div id="myDropdown" class="dropdown-content">
							<a href="#" onclick="openEmailForm()"><ion-icon name="mail-outline"></ion-icon>Change Email Address</a> 
							<a href="#" onclick="openPasswordForm()"><ion-icon name="lock-open-outline"></ion-icon>Change Password</a> 
							<a href="Logout"><ion-icon name="log-out-outline"></ion-icon>Logout</a>
						</div>
					</div>
					<div class="modal-overlay"></div>
					<div action = "POST" id="emailForm" class="form-popup">
						<form class="form-container" id="emailUpdateForm"> 
							<input type="hidden" value="${usern}" name="username" id = "usernameAcc">
							<h2>Change Email Address</h2>
							<label for="email"><b>New Email Address</b></label> 
							<input type="text" placeholder="Enter New Email Address" name="newemail" required>
							<button class="btn" id="updateEmailBtn" onclick="updateEmail()">Save Changes</button>
							<br>
							<button type="button" class="btn cancel" onclick="closeEmailForm()">Close</button>
						</form> 
					</div>
					<div id="passwordForm" class="form-popup">
						<form class="form-container" id="passwordUpdateForm">
							<input type="hidden" value="${usern}" name="username">
							<h2>Change Password</h2>
							<label for="current_password"><b>Current Password</b></label> 
							<input type="password" placeholder="Enter Current Password" name="password" required> <label for="new_password"><b>New Password</b></label> <input type="password" placeholder="Enter New Password" name="newpass" required>
							<label for="confirm_password"><b>Confirm New Password</b></label>
							<input type="password" placeholder="Confirm New Password" name="conpass" required>
							<button class="btn" id="updatePasswordBtn" onclick="updatePassword()">Save Changes</button>
							<br>
							<button type="button" class="btn cancel" onclick="closePasswordForm()">Close</button>
						</form>
					</div>
				</div>
			</div>
			<!-- ================ Table ================= -->
			<div style="text-align: center;">
				<br> <br>
				<h1>USER LIST</h1>
				<br> <br>
			</div>
			<center>
				<table id="dg" class="easyui-datagrid" url="ShowUsers"
					toolbar="#toolbar" pagination="true" fitColumns="true"
					singleSelect="true" style="width: 96%; height: 425px;">
					<thead>
						<tr>
							<th field="role_id" width="25%">Role Id</th>
							<th field="username" width="25%">Username</th>
							<th field="email" width="25%">Email</th>
							<th field="status" width="27%">Status</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar" style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">Register User</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Change Role</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Change Access</a>
				</div>
				<div id="dlg" class="easyui-dialog" style="width: 450px"
					data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Add User</h3>
						<div style="margin-bottom: 10px">
							<input name="username" class="easyui-textbox" label="Username:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input name="email" class="easyui-textbox" validType="email"label="Email:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input name="password" class="easyui-textbox" validType="password" label="Password:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="roleid" label="Role:"
								labelPosition="top" style="width: 100%; height: 50px;">
								<option value="1">Administrator</option>
								<option value="2">Producer</option>
								<option value="3">Order Taker</option>
								<option value="4">Auditor</option>
							</select>
						</div>
					</form>
					<div id="dlg-buttons">
						<a class="easyui-linkbutton c6" iconCls="icon-ok" onclick="changeStatus()" style="width: 90px">Yes</a> <a
							class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')" style="width: 90px">Cancel</a>
					</div>
				</div>
				<div id="dlg1" class="easyui-dialog" style="width: 450px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm1" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Edit User</h3>
						<div style="margin-bottom: 10px">
							<input name="username" class="easyui-textbox" readonly label="Username:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<select class="easyui-combobox" name="roleid" required="true" label="Role:" labelPosition="top" style="width: 100%;">
								<option value="1">Administrator</option>
								<option value="2">Producer</option>
								<option value="3">Order Taker</option>
								<option value="4">Auditor</option>
							</select>
						</div>
					</form>
					<div id="dlg-buttons">
						<a class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser();  javascript:$('#dlg').dialog('close'); javascript$('#dg').datagrid('reload');" style="width: 90px">Register</a> 
						<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">Cancel</a>
					</div>
				</div>
				<div id="dlg2" class="easyui-dialog" style="width: 450px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
					<form id="fm2" method="post" novalidate style="margin: 0; padding: 20px 50px">
						<h3>Change Access</h3>
						<p>Are you sure ?</p>
						<div style="margin-bottom: 10px">
							<input name="username" class="easyui-textbox" readonly label="Username:" style="width: 100%">
						</div>
						<div style="margin-bottom: 10px">
							<input name="status" class="easyui-textbox" readonly label="Status:" style="width: 100%">
						</div>
					</form>
					<div id="dlg-buttons">
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="editRole()" style="width: 90px">Edit</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')" style="width: 90px">Cancel</a>
					</div>
				</div>
				<script src="/ORM/resources/js/admin.js"></script>
				<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
				<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
