<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.sql.*" %>


<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>
	DATA HELD: ${user.getUsername()}
	<form>
	<label>Current Password</label><input type="password" placeholder="current password">
	<label>New Password</label><input type="password" placeholder="">
	<label>Current Email</label><input type="email" placeholder="email">
	<label>New Password</label><input type="email" placeholder="">
	<input type="submit" value = "Submit"><input type="reset" value ="Clear">
	</form>
</body>
