package ru.itis.rest.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/post")
    public String getCreatePostPage() {
        return "create_post";
    }
}
