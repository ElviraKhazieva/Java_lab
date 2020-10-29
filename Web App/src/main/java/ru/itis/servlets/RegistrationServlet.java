package ru.itis.servlets;


import ru.itis.models.User;
import ru.itis.services.UsersService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        //PasswordEncoder passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String groupNumber = request.getParameter("groupNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPassword = usersService.getHashPassword(password);
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(Integer.parseInt(age))
                .groupNumber(groupNumber)
                .email(email)
                .password(hashPassword)
                .build();
        usersService.addUser(user);
        //////////////////////
        String uuid = usersService.register(user);
        Cookie cookie = new Cookie("Auth", uuid);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        /*!!*/HttpSession session = request.getSession();
        /*!!*/session.setAttribute("User", user);
        response.sendRedirect( request.getContextPath() + "/profile");
        ////////////////////
        //response.sendRedirect(request.getContextPath() + "/login");
    }
}
