<#ftl encoding="UTF-8"/>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <title><@spring.message 'create_post.title'/></title>
</head>
<body>

    <h2><a href="?lang=ru">RU</a></h2>
    <h2><a href="?lang=en">EN</a></h2>

    <div id="createPost">

        <h1><@spring.message 'create_post.title'/></h1>
        <form action="/createPost" method="post" enctype="multipart/form-data">
            <textarea style="width: 300px; height: 150px" name="text" class="postText"></textarea>
            <div><input type="file" name="files" multiple></div>
            <div><input type="submit" value=<@spring.message 'create_post.submit'/>></div>
        </form>

    </div>
</body>
</html>