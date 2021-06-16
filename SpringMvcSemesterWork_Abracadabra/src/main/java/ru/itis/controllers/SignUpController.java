package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.aspects.MethodCounting;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;
import ru.itis.services.SignUpService;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PermitAll
    @MethodCounting
    @GetMapping("/signUp")
     public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserSignUpForm());
        return "sign_up_page";
    }

    @PermitAll
    @MethodCounting
    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("userForm") UserSignUpForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
            });
            model.addAttribute("userForm", form);
            return "sign_up_page";
        }
        signUpService.signUp(form);
        return "redirect:/profile";
    }


}
