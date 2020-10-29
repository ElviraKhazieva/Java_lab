package ru.itis.filters;

import ru.itis.models.User;
import ru.itis.services.UsersService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*")
public class SessionFilter implements Filter {

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
            if (request.getSession(false) != null) {
                User user = (User) request.getSession(false).getAttribute("User");
                if (user != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            } else {
                Cookie [] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ((cookie.getName().equals("Auth")) && (usersService.isAuthenticated(cookie.getValue()))) {
                            request.getSession().setAttribute("User", usersService.getUserByUuid(cookie.getValue()));
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
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

