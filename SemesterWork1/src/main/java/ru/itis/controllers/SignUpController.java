package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.SignUpService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage() {
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String addUser(User signUpUser, HttpServletRequest request, HttpServletResponse response) {
        User user = signUpService.signUp(signUpUser);
        Cookie cookie = new Cookie("Auth", user.getConfirmCode());
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("User", user);
        System.out.println(session.getAttribute("User"));
        return "redirect:/profile/" + user.getId();
    }



}
