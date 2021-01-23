package ru.itis.services;

import ru.itis.models.User;
import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersWithAge(int age);

    Optional<User> getUserByEmail(String email);

    boolean authenticateCookie(String cookie);

    User getUserByUuid(String uuid);

}
