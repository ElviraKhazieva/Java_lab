package ru.itis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.PostDto;
import ru.itis.models.User;
import ru.itis.services.FilesService;
import ru.itis.services.PostsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CreatePostController {

    @Autowired
    private FilesService filesService;

    @Autowired
    private PostsService postsService;

    @GetMapping("/createPost")
    public String getCreatePostPage() {
        return "create_post";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setBindEmptyMultipartFiles(false);
    }

    @PostMapping("/createPost")
    public String createPost(PostDto postDto, HttpServletRequest request) {
        MultipartFile [] files = postDto.getFiles();
        List<String> fileNames = null;
        if (files != null && files.length > 0) {
            fileNames = filesService.saveFiles(files);
            postDto.setFileNames(fileNames);
        }
        HttpSession session = request.getSession(false);
        User author = (User) session.getAttribute("User");
        postsService.addPost(postDto, author);
        return "redirect:/profile/" + author.getId();
    }
}
