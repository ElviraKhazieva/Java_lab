package ru.itis.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.dto.TokenDto;
import ru.itis.rest.services.LoginService;

@RestController
public class RefreshController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(String refreshToken) {
        return  ResponseEntity.ok(loginService.login(refreshToken));
    }

}
