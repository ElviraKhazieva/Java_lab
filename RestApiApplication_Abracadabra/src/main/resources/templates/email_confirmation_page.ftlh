<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <title><@spring.message 'email_confirmation.title'/></title>
</head>
<body>
    <h2><a href="?lang=ru">RU</a></h2>
    <h2><a href="?lang=en">EN</a></h2>

    <h1><@spring.message 'email_confirmation_page.successful'/></h1>
    <p><a href="/profile/${user.id}"><@spring.message 'email_confirmation.return_to_profile'/></a></p>

</body>
</html>