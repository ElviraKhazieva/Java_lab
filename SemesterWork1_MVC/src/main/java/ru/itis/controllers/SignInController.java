package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserSignInForm;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;
import ru.itis.services.SignInService;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/signIn")
    public String getSignInPage(Model model) {
        model.addAttribute("userForm", new UserSignInForm());
        return "sign_in_page";
    }

    @PostMapping("/signIn")
    public String signIn(@Valid UserSignInForm form, BindingResult bindingResult, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", form);
            return "sign_in_page";
        }
        User user = signInService.authenticate(form.getEmail(), form.getPassword());
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
