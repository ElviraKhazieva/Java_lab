package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.PostsService;
import ru.itis.services.UsersService;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        return  "profile_page";
//        UserDto user = usersService.getById(userId);
//        int postsCount = postsService.getAllByAuthor(user).size();
//        model.addAttribute("postsCount", postsCount);
//        model.addAttribute("user", user);
//        model.addAttribute("posts", postsService.getAllByAuthor(user));
//        return "profile_page";

    }

}
