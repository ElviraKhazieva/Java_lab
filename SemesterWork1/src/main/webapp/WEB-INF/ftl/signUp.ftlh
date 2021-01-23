<#ftl encoding="UTF-8"/>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <h1>Регистрация</h1>
    <div class="card">
        <div class="card-body">
            <form action="/signUp" method="post">

                <div class="form-group row">
                    <label for="firstName" class="col-sm-2 col-form-label">First
                        Name</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               placeholder="Enter first name">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="lastName" class="col-sm-2 col-form-label">Last
                        Name</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id = "lastName" name="lastName"
                               placeholder="Enter last name">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="email" name="email"
                               placeholder="Enter Email">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" id="hashPassword" name="hashPassword"
                               placeholder="Enter Password">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="nickname" class="col-sm-2 col-form-label">Nickname</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="nickname" name="nickname"
                               placeholder="Enter your nickname">
                    </div>
                </div>
                <input type="submit" value="Зарегистрироваться" class="btn btn-primary">
            </form>
        </div>
    </div>
</div>

</body>

</html>