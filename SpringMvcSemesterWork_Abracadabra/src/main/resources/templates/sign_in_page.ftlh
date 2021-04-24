<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0">
    <title><@spring.message 'sign_in_page.authorization.title'/></title>
    <link rel="stylesheet" href="/static/css/style.css">
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="?lang=ru">RU</a></h2>
<h2><a href="?lang=en">EN</a></h2>
<div class="section">
    <div class="content">
        <img src="/static/images/abracadabra.jpg" class="logo">
    </div>
    <div class="heading">
        <h1><@spring.message 'sign_in_page.authorization.title'/></h1>
    </div>
    <div class="main">
        <div class="form">
            <form action="/signIn" method="post" class="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <input type="text"  name="username" class="email form-input" placeholder="<@spring.message 'username.placeholder'/>">

                <input type="password" name="password" class="password form-input" placeholder="<@spring.message 'password.placeholder'/>">
                <div class="btn">
                    <button type="submit">
                        <b><@spring.message 'sign_in_page.authorization.submit'/></b>
                    </button>
                </div>
                <div>
                    <label style="width: 200px"><input type="checkbox" name="remember-me" placeholder="Запомни меня">Запомни меня</label>
                </div>
            </form>
            <div class="paragraphs">
                <div class="signUp">
                    <p><a href="/signUp"><@spring.message 'sign_in_page.authorization.sign_up'/></a></p>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>