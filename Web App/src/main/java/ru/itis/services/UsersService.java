package ru.itis.services;

import ru.itis.models.CookieValue;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    //void addUser(User user);
    List<User> getAllUsers();
    List<User> getUsersWithAge(int age);
    Optional<User> getUserByEmail(String email);
    void update(User user);
    //CookieValue getCookieByStudentIdAndName(Long id, String name);
    boolean authenticateCookie(String cookie);
    String authorize(String login, String password);
    //void addUserCookie(CookieValue cookieValue);
    boolean checkPassword(String password, String hashPassword);
    String getHashPassword(String password);
    String register(User user);
    User getUserByUuid(String uuid);
}
