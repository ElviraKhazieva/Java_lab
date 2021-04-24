package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import javax.transaction.Transactional;

@Controller
@Transactional
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("usersList", SimpleUserDto.fromDto(usersService.getAllUsers()));
        return "users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/banAll")
    public String bunUsers() {
        usersService.banAll();
        return "redirect:/users";
    }
}
