package ru.itis.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.UsersService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;
    private Template template;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
        //this.usersService = (UsersService) servletContext.getAttribute("usersService");
        Configuration configuration = applicationContext.getBean(freemarker.template.Configuration.class);
        try {
            template = configuration.getTemplate("users.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(usersService.getAllUsers());
//        System.out.println(usersService.getUsersWithAge(19));
        List<User> users = usersService.getAllUsers();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("users", users);
//      FileWriter fileWriter = new FileWriter("users.txt");
        FileWriter fileWriter = new FileWriter("C:/Users/user/Documents/Java_lab/Web App/src/main/webapp/WEB-INF/users.html");
        template.process(attributes, fileWriter);
        request.getRequestDispatcher("/WEB-INF/users.html").forward(request, response);
        // request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
