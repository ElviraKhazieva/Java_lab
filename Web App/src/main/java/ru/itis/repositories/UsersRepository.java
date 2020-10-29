package ru.itis.repositories;

import ru.itis.models.CookieValue;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findAllByAge(int age);
    User find(String login);
    User findUserByUuid(String uuid);
}
