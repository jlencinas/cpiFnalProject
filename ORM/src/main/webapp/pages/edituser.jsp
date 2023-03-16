<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employees</title> 
</head>
<body>
	<h1>Employee Information</h1>
	
	<table>
		<tr>
			<th>Role</th>
			<th>Username</th>
			<th>Email</th>
			<th>Status</th>
			<th colspan = "2">Actions</th>
		</tr>

		<c:forEach items="${userprofile}" var="user">
			<tr>
				<td>
					<c:choose>
					 	<c:when test = "${user.roleId == 1}">
					        ADMINISTRATOR
					    </c:when>
					    <c:when test = "${user.roleId == 2}">
					        PRODUCER
					    </c:when>
					    <c:when test = "${user.roleId == 3}">
					        ORDER TAKER
					    </c:when>
					    <c:when test = "${user.roleId == 4}">
					        AUDITOR
					    </c:when>
				  	</c:choose>
			  	</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.status}</td>
				<td>
					<form action="Disable" method = "post">
						<input type="hidden" name = "uid" value ="${user.userId}" >
						<input type="hidden" name = "stat" value = "${user.status}">
						<c:choose>
							<c:when test = "${user.status == 'ENABLED'}">
							<input type ="submit" value = "DISABLE">
							</c:when>
							<c:when test = "${user.status == 'DISABLED'}">
							<input type ="submit" value = "ENABLE">
							</c:when>
						</c:choose>
					</form>
					</td>
					<td>
					<form action="Edit" method = "post">
						<input type="hidden" name = "uid" value ="${user.userId}" >
						<select name = "roleid">
						<c:choose>
							<c:when test = "${user.roleId == 1}">
								<option value='0'>PLACEHOLDER</option>
								<option value='2'>PRODUCER</option>
								<option value='3'>ORDER TAKER</option>
								<option value='4'>AUDITOR</option>
							</c:when>
							<c:when test = "${user.roleId == 2}">
								<option value='0'>PLACEHOLDER</option>
								<option value='1'>ADMINISTRATOR</option>
								<option value='3'>ORDER TAKER</option>
								<option value='4'>AUDITOR</option>
							</c:when>
							<c:when test = "${user.roleId == 3}">
								<option value='0'>PLACEHOLDER</option>
								<option value='1'>ADMINISTRATOR</option>
								<option value='2'>PRODUCER</option>
								<option value='4'>AUDITOR</option>
							</c:when>
							<c:when test = "${user.roleId == 4}">
								<option value='0'>PLACEHOLDER</option>
								<option value='1'>ADMINISTRATOR</option>
								<option value='2'>PRODUCER</option>
								<option value='3'>ORDER TAKER</option>
							</c:when>
						</c:choose>
						</select>
						<input type="submit" value = "Change Role">
					</form>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>


</html>