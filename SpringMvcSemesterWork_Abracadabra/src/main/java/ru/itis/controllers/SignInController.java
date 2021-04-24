package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.services.SignInService;
import ru.itis.services.UsersService;
import javax.annotation.security.PermitAll;


@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private UsersService usersService;

    @PermitAll
    @GetMapping("/signIn")
    public String getSignInPage(Model model) {
        return "sign_in_page";
    }

}
