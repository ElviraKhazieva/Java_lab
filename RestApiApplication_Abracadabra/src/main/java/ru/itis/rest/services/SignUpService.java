package ru.itis.rest.services;

import ru.itis.rest.dto.UserSignUpForm;
import ru.itis.rest.models.User;

public interface SignUpService {

    User signUp(UserSignUpForm userForm);

}
