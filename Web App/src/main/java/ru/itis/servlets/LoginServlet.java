package ru.itis.servlets;


import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String login = request.getParameter("login");
       String password = request.getParameter("password");
//       User user = usersService.getUserByLoginAndPassword(login, password);
//       if (user != null) {
//           String uuid = UUID.randomUUID().toString();
//           CookieValue cookieValue = CookieValue.builder()
//                   .name("Auth")
//                   .value(uuid)
//                   .user(user)
//                   .build();
//           usersService.addUserCookie(cookieValue);
        String uuid = usersService.authorize(login, password);
        if (uuid != null) {
            Cookie cookie = new Cookie("Auth", uuid);
            cookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            User user = usersService.getUserByLogin(login);
            session.setAttribute("User", user);
            response.sendRedirect( request.getContextPath() + "/profile");
        } else {
            response.sendRedirect( request.getContextPath() + "/registration");
        }
    }
}
