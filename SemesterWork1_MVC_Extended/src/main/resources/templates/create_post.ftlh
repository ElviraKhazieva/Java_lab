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
            <div>
                <#assign postTextPlaceholder><@spring.message 'create_post.text.placeholder'/></#assign>
                <textarea style="width: 300px; height: 150px" name="text" class="postText"></textarea>
            </div>
            <div>
                <#assign postFilesPlaceholder><@spring.message 'create_post.files.placeholder'/></#assign>
                <input type="file" name="files" multiple>
            </div>
            <div>
                <#assign postTagPlaceholder><@spring.message 'create_post.tag.placeholder'/></#assign>
                <input type="text" name="tag">
            </div>
            <div><input type="submit" value=<@spring.message 'create_post.submit'/>></div>
        </form>

    </div>
</body>
</html>