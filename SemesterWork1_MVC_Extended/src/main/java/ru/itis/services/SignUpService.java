package ru.itis.services;

import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;

public interface SignUpService {

    User signUp(UserSignUpForm userForm);

}
