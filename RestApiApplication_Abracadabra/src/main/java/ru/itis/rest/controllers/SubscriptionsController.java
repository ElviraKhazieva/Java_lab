package ru.itis.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rest.security.details.UserDetailsImpl;
import ru.itis.rest.services.UsersService;


@RestController
public class SubscriptionsController {

    @Autowired
    private UsersService userService;

    @PostMapping("/profile/subscribe")
    public ResponseEntity<?> subscribe(@RequestParam Long userTo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.subscribe(userDetails.getUser().getId(), userTo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestParam Long userTo, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.unsubscribe(userDetails.getUser().getId(), userTo);
        return ResponseEntity.ok().build();
    }


}
