package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;
import java.util.Optional;

@Controller
public class FollowersAndSubscriptionsController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/followers/{user-id}")
    public String getFollowersPage(@PathVariable("user-id") Long userId, Model model) {
        UserDto user = usersService.getById(userId);
        if (user != null) {
            model.addAttribute("followers", user.getFollowers());
            return "user_followers";
        }
        return "sign_in_page";
    }

    @GetMapping("/subscriptions/{user-id}")
    public String getSubscriptionsPage(@PathVariable("user-id") Long userId, Model model) {
        UserDto user = usersService.getById(userId);
        if (user != null) {
            model.addAttribute("subscriptions", user.getSubscriptions());
            return "user_subscriptions";
        }
        return "sign_in_page";
    }
}
