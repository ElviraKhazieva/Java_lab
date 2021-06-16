package ru.itis.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.dto.EmailPasswordDto;
import ru.itis.rest.dto.TokenDto;
import ru.itis.rest.dto.UserSignUpForm;
import ru.itis.rest.services.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Авторизация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно авторизован", response = TokenDto.class)})
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody EmailPasswordDto emailPassword) {
        return ResponseEntity.ok(loginService.login(emailPassword));
    }

}
