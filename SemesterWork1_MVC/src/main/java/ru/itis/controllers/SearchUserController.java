package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.services.UsersService;

import java.util.Optional;

@Controller
public class SearchUserController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/search/nickname")
    public String getSearchUserPage() {
        return "search_user_page";
    }

    @RequestMapping(value = "/search/nickname", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto addUserFromJson(@RequestBody SimpleUserDto user) {
        Optional<UserDto> userDtoOptional = usersService.getUserByNickname(user.getNickname());
        return userDtoOptional.orElse(null);
    }
}
