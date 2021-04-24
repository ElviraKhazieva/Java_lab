package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import java.util.List;
import java.util.Optional;


@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "all_users_page";
    }

}
