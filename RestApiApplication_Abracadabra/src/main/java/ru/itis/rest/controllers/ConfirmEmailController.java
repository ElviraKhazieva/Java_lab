package ru.itis.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.services.UsersService;

@RestController
public class ConfirmEmailController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/confirm/{confirm_code}")
    public ResponseEntity<UserDto> confirmMail(@PathVariable("confirm_code") String confirmCode, Model model) {
        return ResponseEntity.ok(usersService.confirmMail(confirmCode));
    }

}
