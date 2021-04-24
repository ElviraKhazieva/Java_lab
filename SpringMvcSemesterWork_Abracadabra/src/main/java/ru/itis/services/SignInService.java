package ru.itis.services;

import ru.itis.models.User;

public interface SignInService {

    boolean authenticated(String email, String password);

}
