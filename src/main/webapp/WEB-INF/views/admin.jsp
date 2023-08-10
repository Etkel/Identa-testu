<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Admin Page</title>
</head>
<body>

<table border="1">
    <td>Id</td>
    <td>Date</td>
    <td>Status</td>
    <td>Reject</td>
    <td>Accept</td>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.created}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td>
                <c:url value="/admin/approve/${order.id}" var="approveOrderURL"/>
                <form action="${approveOrderURL}" method="POST">
                    <button type="submit">Approve an order</button>
                </form>
            </td>
            <td>
                <c:url value="/admin/reject/${order.id}" var="rejectOrderURL"/>
                <form action="${rejectOrderURL}" method="POST">
                    <button type="submit">Reject an order</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
