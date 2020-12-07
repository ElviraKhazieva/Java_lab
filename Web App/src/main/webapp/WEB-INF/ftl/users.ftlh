<html lang="en">
<head>
    <title>My Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<#if users??>
    <h3>USERS</h3>
<#else>
    <h3>Users are undefined</h3>
</#if>
<table class="table" style="margin-top: 1%">
    <thead>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Group number</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
        <#list users as listUser>
            <tr>
                <td>${listUser.firstName}</td>
                <td>${listUser.lastName}</td>
                <td>${listUser.age}</td>
                <td>${listUser.groupNumber}</td>
                <td>${listUser.email}</td>
            </tr>
        </#list>
    </tbody>
</table>
</div>
</body>
</html>
