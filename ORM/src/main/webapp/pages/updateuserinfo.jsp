<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.cpi.model.Users"%>

<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>
	${msg}
    <% 
        HttpSession sesh2 = request.getSession(); 
        Users seshinfo = (Users)sesh2.getAttribute("userAccount"); 
        int uid = seshinfo.getUserId();
        String un = seshinfo.getUsername();
        String pwd = seshinfo.getPassword();
    %>
    <form action="Update" method="post">
        <h6>Only Fill Blanks to be Updated</h6>
        <input type="hidden" value = "<%= un %>" name = "username">
        <label>New Email: </label><input type="email" name = "newemail"><br/>
        <label>Password: </label><input type="password" name = "password"><br/>
        <label>New Password</label><input type="password" name = "newpass"><br/>
        <label>Confirm New Password</label><input type="password" name = "conpass">
        <input type="submit" value = "Submit"><input type="reset" value ="Clear">
    </form>
    <button onClick = "window.location.href='/ORM/pages/dashboard.jsp'">Back</button>
</body>
