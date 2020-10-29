<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <h1>Регистрация</h1>
    <div class="card">
        <div class="card-body">
            <form action="http://localhost:8080/Web_App_war/registration" method="post">

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

                <div class=" form-group row">
                    <label for="age" class="col-sm-2 col-form-label">Age</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="age" name="age"
                               placeholder="Enter your age">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="email" name="email"
                               placeholder="Enter Address">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter Password">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="groupNumber" class="col-sm-2 col-form-label">Group Number</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="groupNumber" name="groupNumber"
                               placeholder="Enter your group number">
                    </div>
                </div>

                <input type="submit" value="Зарегистрироваться" class="btn btn-primary">
            </form>
        </div>
    </div>
</div>

</body>

</html>