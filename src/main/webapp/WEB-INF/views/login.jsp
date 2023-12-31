<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <c:url value="/j_spring_security_check" var="loginUrl"/>

    <form action="${loginUrl}" method="POST">
        Email:<br/><input type="text" name="j_login"><br/>
        Password:<br/><input type="password" name="j_password"><br/>
        <input type="submit"/>

        <c:url value="/" var="indexURL"/>
        <p><a href="${indexURL}">Back to make an order</a></p>

        <c:if test="${param.error ne null}">
            <p>Wrong login or password!</p>
        </c:if>

        <c:if test="${param.logout ne null}">
            <p>Chao!</p>
        </c:if>
    </form>
    <c:url value="/" var="indexUrl"/>
    <p><a href="${indexUrl}">Back to main page</a></p>
</div>


</body>
</html>
