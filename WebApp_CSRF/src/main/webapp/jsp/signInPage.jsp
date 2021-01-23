<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
    <body>
    <form action="${pageContext.request.contextPath}/signIn" method="post">
        <input name="email">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="password" name="password">
        <input type="submit" value="SignIn">
    </form>
    <a href="/signUp"> <button type="submit">Регистрация</button> </a>
    </body>
</html>

