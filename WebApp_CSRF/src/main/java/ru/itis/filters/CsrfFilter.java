package ru.itis.filters;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static ru.itis.filters.ResponseUtil.sendForbidden;

public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            HashSet<String> sessionCsrf = (HashSet<String>) request.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.contains(requestCsrf)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                sendForbidden(request, response);
                return;
            }
        }

        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            Set<String> csrfTokens = (HashSet<String>) request.getSession().getAttribute("_csrf_token");
            if (csrfTokens != null) {
                csrfTokens.add(csrf);
            } else {
                csrfTokens = new HashSet<>();
                csrfTokens.add(csrf);
                request.getSession().setAttribute("_csrf_token", csrfTokens);
            }
            request.setAttribute("_csrf_token", csrf);
        }
        
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
