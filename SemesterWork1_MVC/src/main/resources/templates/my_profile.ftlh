<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'profile.my_profile'/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    <script src="/static/js/posts_loader.js"></script>
</head>
<body>
<div class="container">
    <nav class=" navbar navbar-light bg-light">
        <a class="navbar-brand" href="/feed/${user.id}">
            <img src="/static/images/abracadabra.jpg" width="30" height="30"  class="rounded-circle">
            <@spring.message 'profile.nav.abracadabra'/>
        </a>
        <div><a href="?lang=ru">RU</a>/<a href="?lang=en">EN</a></div>
        <div>
            <a href="/logout">
                <@spring.message 'profile.logout'/>
            </a>
        </div>
    </nav>
</div>

<div style="margin-top: 1%" class="container">
    <div class="row">
        <div class=" col-sm-4">
            <nav class="nav flex-column">
                <a href="/profile/${user.id}" class="nav-link"><@spring.message 'profile.my_profile'/></a>
                <a href="/feed/${user.id}" class="nav-link"><@spring.message 'profile.feed'/></a>
                <a href="/users" class="nav-link"><@spring.message 'profile.all_users'/></a>
                <a href="/search/nickname" class="nav-link"><@spring.message 'profile.search_user'/></a>
                <a href="/explore" class="nav-link"><@spring.message 'profile.explore'/></a>
                <a href="/followers/${user.id}" class="nav-link"><@spring.message 'profile.followers'/></a>
                <a href="/subscriptions/${user.id}" class="nav-link"><@spring.message 'profile.subscriptions'/></a>
            </nav>
            <a class="btn btn-primary" href="/createPost" role="button"><@spring.message 'profile.create.post'/></a>
        </div>
        <div class="col-sm-8">
            <div class="row">
                <div class="col-sm-4">
                    <img src="/static/images/user.png" class="rounded-circle" width="160" height="160" alt="user">
                </div>
                <div class="col-sm-8">
                    <h3>${user.firstName} ${user.lastName}</h3>
                    <p>${user.nickname}</p>
                    <p>${user.email}</p>
                    <div class="row">
                        <div class="col-sm-4">
                            <span><@spring.message 'profile.posts'/></span>
                            <span>${postsCount}</span>
                        </div>
                        <div class="col-sm-4">
                            <span><@spring.message 'profile.followers'/></span>
                            <span>${user.followers?size}</span>
                        </div>
                        <div  class="col-sm-4">
                            <span><@spring.message 'profile.subscriptions'/></span>
                            <span>${user.subscriptions?size}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <h3>Posts</h3>
                    <div>
                        <#list posts as post>
                            <div class="post">
                                <div class="post-content">
                                    <div class="post-meta">
                                        <span class="author">${post.author.nickname}</span>
                                    </div>
                                    <p>${post.text}</p>
    <#--                                <#if post.fileNames??>-->
    <#--                                    <#list post.fileNames as filename>-->
    <#--                                        <img src="/mytemp/${filename}" alt="image"/>-->
    <#--                                    </#list>-->
    <#--                                </#if>-->
                                <#--загрузить фотки-->
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<script>-->
<#--    path = "/profile";-->
<#--    updated = ${now.time};-->
<#--    addScrollListener();-->
<#--</script>-->

</body>
</html>