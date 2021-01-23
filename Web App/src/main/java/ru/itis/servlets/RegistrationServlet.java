package ru.itis.servlets;


import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.RegistrationService;
import ru.itis.services.UsersService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        registrationService = applicationContext.getBean(RegistrationService.class);;
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
        String hashPassword = registrationService.getHashPassword(password);
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(Integer.parseInt(age))
                .groupNumber(groupNumber)
                .email(email)
                .hashPassword(hashPassword)
                .build();
        String uuid = registrationService.register(user);
        Cookie cookie = new Cookie("Auth", uuid);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("User", user);
        response.sendRedirect( request.getContextPath() + "/profile");
    }
}
