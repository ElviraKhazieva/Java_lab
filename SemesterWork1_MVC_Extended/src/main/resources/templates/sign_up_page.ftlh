<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0">
    <title><@spring.message 'sign_up_page.registration.title'/></title>
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
        <h1><@spring.message 'sign_up_page.registration.title'/></h1>
    </div>
    <div class="main">
        <@spring.bind "userForm"/>
        <#if namesErrorMessage??>
            <p style="color: red">${namesErrorMessage}</p>
        </#if>
        <div class="form">
            <form action="/signUp" method="post">
                <div>
                    <#assign firstNamePlaceholder><@spring.message 'firstName.placeholder'/></#assign>
                    <@spring.formInput "userForm.firstName" "placeholder='" + firstNamePlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="text" name="firstName" id="firstName" placeholder="First Name">-->
                </div>
                <div>
                    <#assign lastNamePlaceholder><@spring.message 'lastName.placeholder'/></#assign>
                    <@spring.formInput "userForm.lastName" "placeholder='" + lastNamePlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="text" name="lastName" id="lastName" placeholder="Last Name">-->
                </div>
<#--                <div>-->
<#--                    <input type="date" name="birthDate" id="birthDate" placeholder="Birth Date">-->
<#--                </div>-->
                <div>
                    <#assign nicknamePlaceholder><@spring.message 'nickname.placeholder'/></#assign>
                    <@spring.formInput "userForm.nickname" "placeholder='" + nicknamePlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="text" name="nickname" id="nickname" placeholder="Nickname">-->
                </div>
                <div>
                    <#assign emailPlaceholder><@spring.message 'email.placeholder'/></#assign>
                    <@spring.formInput "userForm.email" "placeholder='" + emailPlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="email"  name="email" class="email" id="email" placeholder="Email">-->
                </div>
                <div>
                    <#assign passwordPlaceholder><@spring.message 'password.placeholder'/></#assign>
                    <@spring.formPasswordInput "userForm.password" "placeholder='" + passwordPlaceholder + "'"/>
                    <@spring.showErrors "<br>" "error"/>
<#--                    <input type="password" name="password" class="password" id="password" placeholder="Password">-->
                </div>
                <div class="btn">
                    <button type="submit">
                        <b><@spring.message 'sign_up_page.registration.submit'/></b>
                    </button>
                </div>
            </form>
            <div class="paragraphs">
                <div class="signIn">
                    <p><a href="/signIn"><@spring.message 'sign_up_page.registration.sign_in'/></a></p>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>