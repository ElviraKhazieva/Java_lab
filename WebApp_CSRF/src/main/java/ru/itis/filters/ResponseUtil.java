package ru.itis.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// signIn -> profile
// users?userId=2 -> /signIn?redirect=users?userId=2 -> /users?userId=2
// signIn -> signIn

public class ResponseUtil {

    public static void sendForbidden(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder path = new StringBuilder();
        path.append("/signIn");

        if (!request.getRequestURI().equals("/signIn")) {
            path
                    .append("?redirect=")
                    .append(request.getRequestURI());
        }

        if (request.getQueryString() != null) {
            path
                    .append("?")
                    .append(request.getQueryString());
        }

        response.setStatus(403);

        try {
            response.sendRedirect(path.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}
