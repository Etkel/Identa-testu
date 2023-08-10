<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>Order</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<br>
<div align="center">
    <c:if test="${isAdmin == null}">
        <c:url value="/login" var="loginUrl"/>
        <p><a href="${loginUrl}">Log in</a></p>
    </c:if>

    <c:if test="${isAdmin}">
        <c:url value="/admin" var="adminUrl"/>
        <p><a href="${adminUrl}">Click</a> for admin page</p>
    </c:if>

    <c:url value="/" var="orderURL"/>
    <form id="orderForm" action="${orderURL}" method="POST">
        <p>Make an order!</p>
        <button type="button" id="makeOrderButton">Make an order</button>
    </form>

    <c:if test="${isAdmin != null}">
        <c:url value="/logout" var="logoutURL"/>
        <p><a href="${logoutURL}">Log out</a></p>
    </c:if>
</div>

<script>
    $(document).ready(function () {
        $("#makeOrderButton").click(function () {
            $.ajax({
                type: "POST",
                url: $("#orderForm").attr("action"),
                success: function () {
                    location.reload();
                }
            });
        });
    });
</script>

</body>
</html>