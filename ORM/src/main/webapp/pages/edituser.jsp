<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>
<%@ page import="com.cpi.dao.GetUserDetails"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employees</title> 
</head>
<body>
	<%-- <%
		HttpSession sesh2 = request.getSession(); 
		Users seshcont = (Users)sesh2.getAttribute("userAccount"); 
		int uid = seshcont.getUserId();
	%> --%>
	<h1>Employee Information</h1>
	
	<table>
		<tr>
			<th>Role</th>
			<th>Username</th>
			<th>Email</th>
			<th>Status</th>
			<th colspan = "2">Actions</th>
		</tr>
		<%-- <%
				List<Users> userList = GetUserDetails.getUsers();
				for (Users acct : userList) {
		%>
		<tr>
			<td>
				<% 
					int rid = acct.getRoleId();
					if(rid == 1){
						out.println("ADMINISTRATOR");
					} 
					else if(rid == 2){
						out.println("PRODUCER");
					}
					else if(rid == 3){
						out.println("ORDER TAKER");
					}
					else if(rid == 4){
						out.println("AUDITOR");
					}
				%>
			</td>
			<td><%= acct.getUsername() %></td> 
			
			
			<td><%= acct.getEmail() %></td>
			<td><%= acct.getStatus() %></td>
			<td>
			
				</form>
			</td>
			<td>
				<form action = "Edit"  method = "post">
					<input type="hidden" name = "uid" value = "<%= acct.getUserId()%>">
					<select name="roleid">
						<% 
						if(rid == 1){
							out.println("<option value='0'></option>");
							out.println("<option value='2'>PRODUCER</option>");
							out.println("<option value='3'>ORDER TAKER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						} 
						else if(rid == 2){
							out.println("<option value='0'></option>");
							out.println("<option value='1'>ADMINISTRATOR</option>");
							out.println("<option value='3'>ORDER TAKER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						} 
						else if(rid == 3){
							out.println("<option value='0'></option>");
							out.println("<option value='1'>ADMINISTRATOR</option>");
							out.println("<option value='2'>PRODUCER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						}
						else if(rid == 4){
							out.println("<option value='0'></option>");
							out.println("<option value='1'>ADMINISTRATOR</option>");
							out.println("<option value='2'>PRODUCER</option>");
							out.println("<option value='3'>ORDER TAKER</option>");
						}	
						%>
					
					</select>
					
					<input type="submit" value = "Change Role">
				
		
				</form>
			</td>
		</tr>
		<% } %> --%>
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