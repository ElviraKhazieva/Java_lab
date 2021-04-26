package ru.itis.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ReaderEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.dto.UserSignUpForm;
import ru.itis.rest.models.User;
import ru.itis.rest.services.UsersService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("JWT-TOKEN") String token) {
        return ResponseEntity.ok(usersService.getAllUsers());
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{user-id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto user) {
        return ResponseEntity.ok(usersService.updateUser(userId, user));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/banAll")
//    public ResponseEntity<List<UserDto>> bunUsers() {
//        usersService.banAll();
//        return ResponseEntity.ok(usersService.getAllUsers());
//    }
}
