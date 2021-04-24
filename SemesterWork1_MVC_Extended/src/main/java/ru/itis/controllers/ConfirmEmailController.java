package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UsersServiceImpl;
import java.util.Optional;

@Controller
public class ConfirmEmailController {

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/confirm/{confirm_code}")
    public String confirmMail(@PathVariable("confirm_code") String confirmCode, Model model) {
        UserDto user = usersService.confirmMail(confirmCode);
        if (user != null) {
            model.addAttribute("user", user);
            return "email_confirmation_page";
        } else {
            return "sign_in_page";
        }
    }

}
