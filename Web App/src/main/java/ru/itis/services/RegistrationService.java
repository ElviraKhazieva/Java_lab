package ru.itis.services;

import ru.itis.models.User;

public interface RegistrationService {

    String register(User user);

    String getHashPassword(String password);

}
