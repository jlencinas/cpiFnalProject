<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordering</title>
</head>
<body>
	<form action="NewOrder" method="post">
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="t1"></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="t2"></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="number" name="t3"></td>
			</tr>
			<tr>
				<td>Expected Delivery Date</td>
				<td><input type="date" name="dates" pattern="MM/DD/YYYY"></td>
			</tr>
			<!-- <tr>
				<td>Expected Delivery Schedule</td>
				<td><input type="time" name="times"></td>
			</tr> -->
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Clear"></td>
			</tr>
		</table>
	</form>
</body>
</html>