package ru.itis.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.UsersService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class SessionFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
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
                if (checkCookie(request)){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private boolean checkCookie(HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ((cookie.getName().equals("Auth")) && (usersService.authenticateCookie(cookie.getValue()))) {
                    request.getSession().setAttribute("User", usersService.getUserByUuid(cookie.getValue()));
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isProtected(String contextPath, String path) {
        return !path.startsWith(contextPath + "/login") &&
                !path.startsWith(contextPath + "/registration") &&
                !path.startsWith(contextPath + "/favicon.ico") &&
                !path.startsWith(contextPath + "/images");
    }


    @Override
    public void destroy() {

    }
}

