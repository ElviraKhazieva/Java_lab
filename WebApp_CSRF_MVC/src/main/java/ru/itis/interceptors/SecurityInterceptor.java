package ru.itis.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.itis.interceptors.ResponseUtil.sendForbidden;

public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("In SecurityInterceptor " + request.getRequestURI() + "?" + request.getQueryString());
        if (!isProtected(request.getRequestURI())) {
            return true;
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Boolean authenticated = (Boolean) session.getAttribute("authenticated");
                if (authenticated != null && authenticated) {
                    return true;
                }
            }
            response.sendRedirect(sendForbidden(request, response));
            return false;
        }
    }

    private boolean isProtected(String path) {
        return !path.startsWith("/signIn") && !path.startsWith("/signUp") && !path.equals("/favicon.ico");
    }

}
