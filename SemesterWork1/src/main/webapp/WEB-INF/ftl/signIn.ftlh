<#ftl encoding="UTF-8"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8080/css/style.css">
    <title>Log In to Abracadabra</title>
</head>
<body>
<div class="section">
    <div class="content">
        <img src="http://localhost:8080/images/abracadabra.jpg" class="logo">
    </div>
    <div class="heading">
        <h1>
            Log In to Abracadabra
        </h1>
    </div>
    <div class="main">
        <div class="form">
            <form action="/signIn" method="post">
                <input type="email"  name="email" class="email" placeholder="Email"><br>
                <input type="password" name="hashPassword" class="password" placeholder="Password"><br>
                <div class="btn">
                    <button type="submit">
                        <b>Log In</b>
                    </button>
                </div>
            </form>
        </div>
        <div class="paragraphs">
            <div class="signUp">
                <p><a href="/signUp">Sign Up</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>