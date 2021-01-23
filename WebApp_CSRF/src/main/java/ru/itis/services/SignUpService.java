package ru.itis.services;

import ru.itis.models.User;

public interface SignUpService {

    String getHashPassword(String password);
    void signUp(User user);

}
