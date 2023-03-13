<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>
<%@ page import="com.cpi.dao.DisplayUsers"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employees</title> 
</head>
<body>
	<% 
		HttpSession sesh2 = request.getSession(); 
		Users seshcont = (Users)sesh2.getAttribute("userAccount"); 
		int uid = seshcont.getUserId();
	%>
	<h1>Employee Information</h1>
	
	<table>
		<tr>
			<th>ROLE</th>
			<th>Username</th>
			<th>Email</th>
			<th>Status</th>
			<th colspan = "2">Actions</th>
		</tr>
		<%
		List<Users> userList = DisplayUsers.getUsers(uid);
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
				<form action = "Disable" method = "post">
					<input type="hidden" name = "uid" value = "<%= acct.getUserId()%>">
					<input type="hidden" name = "stat" value = "<%= acct.getStatus()%>">
					<%
						String status = acct.getStatus();
						if(status.equals("ENABLED")){
							out.println("<input type ='submit' value = 'DISABLE'>");
						}
						else if(status.equals("DISABLED")){
							out.println("<input type ='submit' value = 'ENABLE'>");
						}
					%>
				</form>
			</td>
			<td>
				<form action = "Edit"  method = "post">
					<input type="hidden" name = "uid" value = "<%= acct.getUserId()%>">
					<select name="roleid">
						<% 
						if(rid == 1){
							out.println("<option value='0'>------------</option>");
							out.println("<option value='2'>PRODUCER</option>");
							out.println("<option value='3'>ORDER TAKER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						} 
						else if(rid == 2){
							out.println("<option value='0'>------------</option>");
							out.println("<option value='1'>ADMINISTRATOR</option>");
							out.println("<option value='3'>ORDER TAKER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						} 
						else if(rid == 3){
							out.println("<option value='0'>------------</option>");
							out.println("<option value='1'>ADMINISTRATOR</option>");
							out.println("<option value='2'>PRODUCER</option>");
							out.println("<option value='4'>AUDITOR</option>");
						}
						else if(rid == 2){
							out.println("<option value='0'>------------</option>");
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
		<% } %>
	</table>
	<button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>
</html>