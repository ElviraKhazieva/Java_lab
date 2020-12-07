package ru.itis.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    public UsersService usersService;

    public Template template;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
        Configuration configuration = applicationContext.getBean(freemarker.template.Configuration.class);
        try {
            template = configuration.getTemplate("profile.ftlh");
        } catch (IOException e) {
           throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("user", user);
        PrintWriter fileWriter = response.getWriter();
        template.process(attributes, fileWriter);
        fileWriter.close();

        /* другой вариант - не очень
        //FileWriter fileWriter = new FileWriter("output.txt");
        FileWriter fileWriter = new FileWriter("C:/Users/user/Documents/Java_lab/Web App/src/main/webapp/WEB-INF/html/profile.html");
        template.process(attributes, fileWriter);
        request.getRequestDispatcher("/WEB-INF/html/profile.html").forward(request, response);
        */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
