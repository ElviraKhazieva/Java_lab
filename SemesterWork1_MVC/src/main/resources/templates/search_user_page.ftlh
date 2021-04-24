<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <title><@spring.message 'search_user_page.search_user'/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</head>
    <body>
    <script>
        function search_user(nickname) {
            let data = {
                "nickname": nickname
            };

            $.ajax({
                type: "POST",
                url:"/search/nickname",
                data: JSON.stringify(data),
                success: function (msg) {
                    let text = "<a href=\"/profile/" + msg["id"] + "\">" + msg["firstName"] +  " " + msg["lastName"] + "</a><br>";
                    console.log(text);
                    $("#nickname-search").append(text);
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>

    <h2><a href="?lang=ru">RU</a></h2>
    <h2><a href="?lang=en">EN</a></h2>

            <div class="container">

                <div class="form-group">
                    <label for="nickname"><@spring.message 'search_user_page.search'/></label>
                    <input type="text" class="form-control" id="nickname" placeholder="Nickname"/>
                    <button class="btn btn-primary" onclick="search_user($('#nickname').val())"><@spring.message 'search_user_page.find'/></button>
                </div>

                <div id="nickname-search">
                </div>

            </div>
    </body>
</html>