package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.SignInService;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getSignInPage() {
        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(User loginUser, HttpServletRequest request, HttpServletResponse response) {
        User user = signInService.authenticate(loginUser.getEmail(), loginUser.getHashPassword());
        if (user != null) {
            Cookie cookie = new Cookie("Auth", user.getConfirmCode());
            cookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            return "redirect:/profile/" + user.getId();
        } else {
            return "redirect:/signIn";
        }
    }
}
