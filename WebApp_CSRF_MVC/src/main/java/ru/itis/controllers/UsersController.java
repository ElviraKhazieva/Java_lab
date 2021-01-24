package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/users")
    public String getUsersPage(@RequestParam(value = "userId") Long userId, Model model, HttpServletResponse response) {
        Optional<User> userOptional = service.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "usersPage";
        } else {
            response.setStatus(404);
            return "errorPage";
        }
    }

    @PostMapping("/users")
    public String deleteUser(@RequestParam(value = "action", required = false) String action,
                             @RequestParam(value = "userId") Long userId) {
        if (action != null && action.equals("delete")) {
            service.deleteUserById(userId);
        }
        return "redirect:/profile";
    }


}
