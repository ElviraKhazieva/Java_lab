<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.01.2021
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/signUp" method="post">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input type="email" name="email">
    <input type="password" name="password">
    <input type="submit" value="signUp">
</form>
</body>
</html>
