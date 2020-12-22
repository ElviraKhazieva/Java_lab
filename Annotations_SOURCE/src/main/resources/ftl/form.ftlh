<html lang="en">
    <head>
        <title>annotation_form</title>
    </head>
    <body>
        <form action="${form.action}" method="${form.method}"/>
            <#list inputs as input>
                <input type="${input.type}" name="${input.name} " placeholder="${input.placeholder}"/>
            </#list>
        </form>
    </body>
</html>