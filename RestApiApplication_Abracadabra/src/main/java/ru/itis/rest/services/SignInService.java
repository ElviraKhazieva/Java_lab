package ru.itis.rest.services;

import ru.itis.rest.models.User;

public interface SignInService {

    boolean authenticated(String email, String password);

}
