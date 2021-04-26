package ru.itis.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.dto.UserSignUpForm;
import ru.itis.rest.models.User;
import ru.itis.rest.services.SignUpService;
import ru.itis.rest.services.UsersService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Регистрация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно зарегистрирован", response = UserSignUpForm.class)})
    @PostMapping("/signUp")
    public void signUp(@RequestBody UserSignUpForm user, HttpServletResponse response) {
        signUpService.signUp(user);
        response.setStatus(200);
    }

}
