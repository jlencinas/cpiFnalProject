 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders Today</title>
</head>
<body>
    <form action="ordersToday" method="post">
        <label>Filter: </label> <select name="filter">
            <option value="all">All</option>
            <option value="AM">AM</option>
            <option value="PM">PM</option>
        </select> <input type="submit" value="Filter">
    </form>
    <table>
        <tr>
            <th>Customer Name</th>
            <th>Delivery Date</th>
            <th>Status</th>
            <th>Payment Status</th>
        </tr>
        <c:forEach items="${ordersToday}" var="orders">
            <tr>
                <td>${orders.customerFn} ${orders.customerLn}</td>
                <td>${orders.deliveryDate}</td>
                <td>${orders.orderStatus}</td>
                <td>${orders.paymentStatus}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>