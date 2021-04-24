package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SubscribeUnsubscribeController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/subscribe/{user-id}")
    public void subscribeUser(@PathVariable("user-id") Long userId, HttpServletRequest request) {
        User userFrom = (User)request.getSession(false).getAttribute("User");
        Optional<UserDto> userToOptional = usersService.getUserById(userId);
        if (userToOptional.isPresent()) {
            UserDto userTo = userToOptional.get();
            usersService.subscribe(userFrom, User.builder().id(userTo.getId()).build());
        }
    }

    @PostMapping("/unsubscribe/{user-id}")
    public void unsubscribeUser(@PathVariable("user-id") Long userId, HttpServletRequest request) {
        User userFrom = (User)request.getSession(false).getAttribute("User");
        Optional<UserDto> userToOptional = usersService.getUserById(userId);
        if (userToOptional.isPresent()) {
            UserDto userTo = userToOptional.get();
            usersService.unsubscribe(userFrom, User.builder().id(userTo.getId()).build());
        }
    }


}
