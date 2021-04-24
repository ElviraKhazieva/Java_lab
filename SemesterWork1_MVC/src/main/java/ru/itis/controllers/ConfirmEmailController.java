package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.models.User;
import ru.itis.services.UsersServiceImpl;
import java.util.Optional;

@Controller
public class ConfirmEmailController {

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/confirm/{confirm_code}")
    public String confirmMail(@PathVariable("confirm_code") String confirmCode, Model model) {
        Optional<User> userOptional = usersService.getUserByConfirmCode(confirmCode);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            usersService.confirmMail(user);
            model.addAttribute("userId", user.getId());
            return "email_confirmation_page";
        } else {
            return "sign_in_page";
        }
    }

}
