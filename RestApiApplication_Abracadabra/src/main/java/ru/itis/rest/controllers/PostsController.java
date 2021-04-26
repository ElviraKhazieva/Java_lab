package ru.itis.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.rest.dto.PostDto;
import ru.itis.rest.dto.PostForm;
import ru.itis.rest.models.User;
import ru.itis.rest.security.details.UserDetailsImpl;
import ru.itis.rest.services.PostsService;
import ru.itis.rest.services.UsersService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@Controller
public class PostsController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @GetMapping("/users/{user-id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(postsService.getAllByAuthorId(userId));
    }

    @ApiOperation(value = "Добавление поста")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Пост успешно добавлен", response = PostDto.class)})
    @PostMapping("/compose/post")
    public ResponseEntity<PostDto> addPost(@RequestBody PostForm post, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(PostDto.from(postsService.addPost(post, userDetails.getUser())));
    }
}
