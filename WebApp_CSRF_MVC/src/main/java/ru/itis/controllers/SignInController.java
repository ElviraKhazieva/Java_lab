package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.services.SignInService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static ru.itis.interceptors.ResponseUtil.sendForbidden;

@Controller
public class SignInController {

    @Autowired
    private SignInService service;


    @GetMapping("/signIn")
    public String getSignInPage() {
        return "signInPage";
    }

    @PostMapping("/signIn")
    public String signInUser(@RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value="redirect", required = false) String redirect,
                             HttpServletRequest request, HttpServletResponse response) {
        // /signIn?redirect=/users?userId=2
        if (service.authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            if (redirect == null) {
                return "redirect:/profile";
            } else {
                return "redirect:" + redirect;
            }
        } else {
            return "redirect:" + sendForbidden(request, response);
        }
    }
}
