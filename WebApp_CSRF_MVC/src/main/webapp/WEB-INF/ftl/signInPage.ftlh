<#ftl encoding="UTF-8"/>
<html lang="en">
    <head>
        <title>SignIn</title>
    </head>
    <body>
    <form action="/signIn" method="post">
        <input name="email">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="password" name="password">
        <input type="submit" value="SignIn">
    </form>
    <a href="/signUp"> <button type="submit">Регистрация</button> </a>
    </body>
</html>