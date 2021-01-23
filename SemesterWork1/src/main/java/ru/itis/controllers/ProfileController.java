package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile/{user-id}")
    public String getProfilePage(@PathVariable("user-id") Long userId, Model model, HttpServletRequest request) {
        Optional<UserDto> userDtoOptional = usersService.getUserById(userId);
        if (userDtoOptional.isPresent()) {
            model.addAttribute("user", userDtoOptional.get());
            return "profile";
        } else {
            return "redirect:/signIn";
        }
    }
}
