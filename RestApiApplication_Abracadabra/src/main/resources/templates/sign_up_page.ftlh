<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0">
    <title><@spring.message 'sign_up_page.registration.title'/></title>
    <link rel="stylesheet" href="/css/style.css">
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
        <img src="/images/abracadabra.jpg" class="logo">
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
            <form action="/signUp" method="post" class="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <#assign firstNamePlaceholder><@spring.message 'firstName.placeholder'/></#assign>
                <@spring.formInput "userForm.firstName" "placeholder=" + firstNamePlaceholder/>
                <@spring.showErrors "<br>" "error"/>

                <#assign lastNamePlaceholder><@spring.message 'lastName.placeholder'/></#assign>
                <@spring.formInput "userForm.lastName" "placeholder=" + lastNamePlaceholder/>
                <@spring.showErrors "<br>" "error"/>

                <#assign usernamePlaceholder><@spring.message 'username.placeholder'/></#assign>
                <@spring.formInput "userForm.username" "placeholder=" + usernamePlaceholder/>
                <@spring.showErrors "<br>" "error"/>

                <#assign emailPlaceholder><@spring.message 'email.placeholder'/></#assign>
                <@spring.formInput "userForm.email" "placeholder=" + emailPlaceholder/>
                <@spring.showErrors "<br>" "error"/>


                <#assign passwordPlaceholder><@spring.message 'password.placeholder'/></#assign>
                <@spring.formPasswordInput "userForm.password" "placeholder=" + passwordPlaceholder/>
                <@spring.showErrors "<br>" "error"/>
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