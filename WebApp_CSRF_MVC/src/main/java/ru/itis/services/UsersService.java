package ru.itis.services;

import ru.itis.models.User;
import java.util.Optional;

public interface UsersService {

    Optional<User> getUserById(Long id);
    void deleteUserById(long userId);

}
