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
        <@spring.bind "userForm"/>
        <div class="form">
            <form action="/signIn" method="post">
                <div>
                    <#assign emailPlaceholder><@spring.message 'email.placeholder'/></#assign>
                    <@spring.formInput "userForm.email" "placeholder='" + emailPlaceholder + "'" />
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="email"  name="email" class="email" placeholder="Email">-->
                </div>
                <div>
                    <#assign passwordPlaceholder><@spring.message 'password.placeholder'/></#assign>
                    <@spring.formPasswordInput "userForm.password" "placeholder='" + passwordPlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="password" name="password" class="password" placeholder="Password">-->
                </div>
                <div class="btn">
                    <button type="submit">
                        <b><@spring.message 'sign_in_page.authorization.submit'/></b>
                    </button>
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