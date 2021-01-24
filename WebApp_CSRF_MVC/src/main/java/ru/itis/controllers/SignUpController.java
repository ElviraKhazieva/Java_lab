package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.User;
import ru.itis.services.SignUpService;


@Controller
public class SignUpController {

    @Autowired
    private SignUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String signUpUser(@RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password) {
        String hashPassword = service.getHashPassword(password);
        User user = User.builder()
                .email(email)
                .hashPassword(hashPassword)
                .build();

        service.signUp(user);
        return "redirect:/profile";
    }
}
