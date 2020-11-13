package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

 @WebServlet("/json/users")
 public class UsersJsonServlet extends HttpServlet {

     private UsersService usersService;
     private ObjectMapper objectMapper;

     @Override
     public void init(ServletConfig config) throws ServletException {
         ServletContext servletContext = config.getServletContext();
         this.usersService = (UsersService) servletContext.getAttribute("usersService");
         this.objectMapper = (ObjectMapper) servletContext.getAttribute("objectMapper");
     }

     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String page = req.getParameter("page");
         String size = req.getParameter("size");
         List<UserDto> users = usersService.getAllUser(Integer.parseInt(page),
                 Integer.parseInt(size));
         String response = objectMapper.writeValueAsString(users);
         resp.getWriter().write(response);
         resp.setContentType("application/json");
     }

     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Reader reader = req.getReader();
         UserDto json = objectMapper.readValue(reader, UserDto.class);
         usersService.addUser(json);
     }
 }
