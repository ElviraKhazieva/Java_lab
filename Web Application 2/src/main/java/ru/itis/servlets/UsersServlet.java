package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAllUsers();
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);
//        PrintWriter writer = response.getWriter();
//
//        writer.println("<table>");
//        writer.println("    <tr>");
//        writer.println("        <th>First Name</th>");
//        writer.println("        <th>Last Name</th>");
//        writer.println("    </tr>");
//        for (User user : users) {
//            writer.println(" <tr>");
//            writer.println("    <td>" + user.getFirstName() + "</td>");
//            writer.println("    <td>" + user.getLastName() + "</td>");
//            writer.println(" </tr>");
//        }
//        writer.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}

