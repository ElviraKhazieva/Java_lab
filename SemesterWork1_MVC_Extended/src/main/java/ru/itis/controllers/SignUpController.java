package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;
import ru.itis.services.SignUpService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController{

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
     public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserSignUpForm());
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserSignUpForm form, BindingResult bindingResult, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
            });
            model.addAttribute("userForm", form);
            return "sign_up_page";
        }
        User user = signUpService.signUp(form);
        
        Cookie cookie = new Cookie("Auth", user.getConfirmCode());
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("User", user);

        return "redirect:/profile/" + user.getId();
    }



}
