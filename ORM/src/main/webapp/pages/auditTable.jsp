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
<link rel="stylesheet" href="/ORM/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/tablecontent.js"/>"></script>
<script> var contextPath = '${pageContext.request.contextPath}' + '/';</script>
<script type="text/javascript">redirectProperSessionAuditor(${roleID})</script>
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
				<c:if test="${seshinfo.roleId eq 1}">
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
				</c:if>
				<c:if test="${seshinfo.roleId eq 3}">
				<li>
					<a href="orderTable.jsp"> <span class="icon"> <ion-icon name="cart-outline"></ion-icon>
					</span> <span class="title">Orders</span>
					</a>
				</li>
				</c:if>
				<c:if test="${seshinfo.roleId eq 2}">
				<li>
					<a href="productionTable.jsp"> <span class="icon"><ion-icon name="cash-outline"></ion-icon>
					</span> <span class="title">Production</span>
					</a>
				</li>
				</c:if>
				<c:if test="${seshinfo.roleId eq 1}">
				<li>
					<a href="reportTable.jsp"> <span class="icon"> <ion-icon name="newspaper-outline"></ion-icon>
					</span> <span class="title">Reporting</span>
					</a>
				</li>
				</c:if>
				<c:if test="${seshinfo.roleId eq 4}">
				<li>
					<a href="auditTable.jsp"> <span class="icon"> <ion-icon name="newspaper-outline"></ion-icon>
					</span> <span class="title">Auditing</span>
					</a>
				</li>
				</c:if>
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
					<div id="emailForm" class="form-popup">
						<form class="form-container" id="emailUpdateForm">
							<input type="hidden" value="${usern}" name="username">
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
							<input type="hidden" value="${usern}" name="username">
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
                <h1>AUDIT ORDER REPORT</h1>
                <br><br>
            </div>

            <center>
                <table id="dg" class="easyui-datagrid" url="listOfAuditOrder" toolbar="#toolbar" pagination="true" fitColumns="true" singleSelect="true" style="width: 95%; height: 425px;">
                    <thead>
                        <tr>
                            <th field="order_id" width="20%">Order ID</th>
                            <th field="fieldchanged" width="20%">Field Changed</th>
                            <th field="oldval" width="20%">Old Value</th>
                            <th field="newval" width="20%">New Value</th>
                            <th field="username" width="22%">Changed By</th>
                        </tr>
                    </thead>
                </table>
            <br><br>
            <!-- ================ Table2 ================= -->
            <div style="text-align: center;">
                <br><br>
                <h1>AUDIT ORDER DETAILS REPORT</h1>
                <br><br>
            </div>
            <center>
                <table class="easyui-datagrid" url="listOfAuditOrderDetails" pagination="true" fitColumns="true" singleSelect="true" style="width: 95%; height: 400px;">
                    <thead>
                        <tr>
                            <th field="order_id" width="21%">Order ID</th>
                            <th field="item_id" width="20%">Item Changed</th>
                            <th field="oldval" width="20%">Old Quantity</th>
                            <th field="newval" width="20%">New Quantity</th>
                            <th field="username" width="20%">Changed By</th>
                        </tr>
                    </thead>
                </table>
			<br><br>
			<script src="/ORM/resources/js/admin.js"></script>
			<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>	
</body>
</html>