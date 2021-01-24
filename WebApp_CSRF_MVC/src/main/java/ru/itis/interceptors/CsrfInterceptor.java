package ru.itis.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import static ru.itis.interceptors.ResponseUtil.sendForbidden;

public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            HashSet<String> sessionCsrf = (HashSet<String>) request.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.contains(requestCsrf)) {
                return true;
            } else {
                response.sendRedirect(sendForbidden(request, response));
                return false;
            }
        }

        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            HashSet<String> csrfTokens = (HashSet<String>) request.getSession().getAttribute("_csrf_token");
            if (csrfTokens != null) {
                csrfTokens.add(csrf);
            } else {
                csrfTokens = new HashSet<>();
                csrfTokens.add(csrf);
                request.getSession().setAttribute("_csrf_token", csrfTokens);
            }
            request.setAttribute("_csrf_token", csrf);
        }

        return true;
    }

}
