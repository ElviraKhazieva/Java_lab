<%@ page import="ru.itis.models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h1 style="color: ${cookie.get("color").value}">USERS</h1>
    <form action="http://localhost:8080/Web_Application_2_war/users" method="post">
        <select name="color">
            <option value="red">RED</option>
            <option value="green">GREEN</option>
            <option value="blue">BLUE</option>
        </select>
        <input type="submit" value="OK">
    </form>
</div>
<div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <%
            List<User>  users = (List<User>) request.getAttribute("usersForJsp");
            for (User user : users) {
        %>
        <tr>
            <td><%=user.getFirstName()%></>
            <td><%=user.getLastName()%></>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
