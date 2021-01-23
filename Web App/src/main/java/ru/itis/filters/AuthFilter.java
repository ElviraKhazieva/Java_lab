package ru.itis.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter implements Filter {

    private ServletContext servletContext;
    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().startsWith(request.getContextPath() + "/login") || (request.getRequestURI().startsWith(request.getContextPath() + "/registration"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Cookie [] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ((cookie.getName().equals("Auth")) && (usersService.authenticateCookie(cookie.getValue()))) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
