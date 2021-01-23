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
        Optional<UserDto> userOptional = usersService.getUserById(userId);
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            model.addAttribute("followers", user.getFollowers());
            return "userFollowers";
        } else {
            return "redirect:/signIn";
        }
    }

    @GetMapping("/subscriptions/{user-id}")
    public String getSubscriptionsPage(@PathVariable("user-id") Long userId, Model model) {
        Optional<UserDto> userOptional = usersService.getUserById(userId);
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            model.addAttribute("subscriptions", user.getSubscriptions());
            return "userSubscriptions";
        } else {
            return "redirect:/signIn";
        }
    }
}
