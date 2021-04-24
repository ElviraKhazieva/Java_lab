package ru.itis.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String context = request.getContextPath();
        if (!isProtected(context, request.getRequestURI())) {
            return true;
        } else {
            if (request.getSession(false) != null) {
                User user = (User) request.getSession(false).getAttribute("User");
                if (user != null) {
                    return true;
                } else {
                    if (checkCookie(request)){
                        return true;
                    }
                }
            } else {
                if (checkCookie(request)) {
                    return true;
                }
            }
            response.sendRedirect(request.getContextPath() + "/signIn");
            return false;
        }
    }



    private boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(Arrays.toString(cookies));
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth")) {
                    User user=  usersService.getByConfirmCode(cookie.getValue());
                        request.getSession().setAttribute("User", user);
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isProtected(String contextPath, String path) {
        return !path.startsWith(contextPath + "/signIn") && !path.startsWith(contextPath + "/signUp")
                && !path.startsWith(contextPath + "/favicon.ico") && !path.startsWith(contextPath + "/static");
    }

}
