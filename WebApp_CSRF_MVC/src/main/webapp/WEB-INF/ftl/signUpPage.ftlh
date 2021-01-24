<#ftl encoding="UTF-8"/>
<html lang="en">
    <head>
        <title>SignUp</title>
    </head>
    <body>
        <form action="/signUp" method="post">
            <input type="hidden" value="${_csrf_token}" name="_csrf_token">
            <input type="email" name="email">
            <input type="password" name="password">
            <input type="submit" value="signUp">
        </form>
    </body>
</html>