package ru.itis.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import java.io.PrintWriter;
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
        Configuration configuration = applicationContext.getBean(freemarker.template.Configuration.class);
        try {
            template = configuration.getTemplate("users.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAllUsers();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("users", users);
        PrintWriter writer = response.getWriter();
        try {
            template.process(attributes, writer);
        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
