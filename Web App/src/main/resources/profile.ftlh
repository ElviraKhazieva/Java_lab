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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">StudNet</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="#">News</a>
        </div>
    </div>
</nav>
        <div class="container-fluid">
            <div class="row">
                <div class="list-group col-sm-4">
                    <a href="#" class="list-group-item list-group-item-action active">
                       Profile
                    </a>
                    <a href="/Web_App_war/users" class="list-group-item list-group-item-action">Users</a>
                    <a href="#" class="list-group-item list-group-item-action">Messages</a>
                    <a href="#" class="list-group-item list-group-item-action">Communities</a>
                    <a href="#" class="list-group-item list-group-item-action">Photos</a>
                    <a href="#" class="list-group-item list-group-item-action">Music</a>
                    <a href="#" class="list-group-item list-group-item-action">Videos</a>
                    <a href="#" class="list-group-item list-group-item-action">Settings</a>
                </div>
                <div class="col-sm-8">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>${user.firstName} ${user.lastName}</h2>
                            <img src="user.png" class="rounded-circle" width="200" height="200" alt="user">
                        </div>
                        <div class="col-sm-6">
                                <h2>Information:</h2>
                                <h3>Age: ${user.age}</h3>
                                <h3>Group number: ${user.groupNumber}</h3>
                                <h3>Email: ${user.email}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
