package ru.itis.filters;

import ru.itis.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter implements Filter {

    private ServletContext context;
    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
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

                    if ((cookie.getName().equals("Auth")) && (usersService.isAuthenticated(cookie.getValue()))) {
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
