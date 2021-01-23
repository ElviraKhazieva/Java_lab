package ru.itis.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.config.ApplicationConfig;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UsersService;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


public class SecurityFilter implements Filter {

    //private ServletContext servletContext;
    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        servletContext = filterConfig.getServletContext();
//        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String context = request.getContextPath();
        if (!isProtected(context, request.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (request.getSession(false) != null) {
                User user = (User) request.getSession(false).getAttribute("User");
                if (user != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                } else {
                    if (checkCookie(request)){
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            } else {
                if (checkCookie(request)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    private boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(Arrays.toString(cookies));
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth")) {
                    Optional<User> userOptional =  usersService.getUserByUuid(cookie.getValue());
                    if (userOptional.isPresent()) {
                        request.getSession().setAttribute("User", userOptional.get());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isProtected(String contextPath, String path) {
        return !path.startsWith(contextPath + "/signIn") && !path.startsWith(contextPath + "/signUp") && !path.startsWith(contextPath + "/favicon.ico")
                && !path.startsWith(contextPath + "/css") && !path.startsWith(contextPath + "/images") ;
    }


    @Override
    public void destroy() {

    }

}
