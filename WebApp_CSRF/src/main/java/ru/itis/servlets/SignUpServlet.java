package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService service;

    @Override
    public void init (ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        service = springContext.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/signUpPage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPassword = service.getHashPassword(password);

        User user = User.builder()
                .email(email)
                .hashPassword(hashPassword)
                .build();

        service.signUp(user);
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}
