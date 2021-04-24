package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersServiceImpl;

@Controller
public class ConfirmEmailController {

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/confirm/{confirm_code}")
    public String confirmMail(@PathVariable("confirm_code") String confirmCode, Model model) {
        UserDto user = usersService.confirmMail(confirmCode);
        model.addAttribute("user", user);
        return "email_confirmation_page";
    }

}
