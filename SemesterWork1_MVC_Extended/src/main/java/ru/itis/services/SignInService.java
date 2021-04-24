package ru.itis.services;

import ru.itis.models.User;

public interface SignInService {

    User authenticate(String email, String password);

}
