package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.PostsService;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @GetMapping("/profile/{user-id}")
    public String getProfilePage(@PathVariable("user-id") Long userId, Model model,
                                 HttpServletRequest request, HttpServletResponse response) {
        UserDto user = usersService.getById(userId);
        if (user != null) {
            int postsCount = postsService.getAllByAuthor(user).size();
            model.addAttribute("postsCount", postsCount);
            model.addAttribute("user", user);
            model.addAttribute("posts", postsService.getAllByAuthor(user));

            User sessionUser = (User)request.getSession(false).getAttribute("User");
            if (sessionUser.getId().equals(userId)) {
                return "my_profile";
            } else {
                model.addAttribute("profile", sessionUser);
                return "user_profile";
            }

        } else {
            return "redirect:/signIn";
        }
    }
}
