package ru.itis.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.models.User;
import ru.itis.rest.services.UsersService;

import java.util.List;

@RestController
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/json")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }



}
