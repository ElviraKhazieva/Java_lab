package ru.itis.services;

import ru.itis.models.CookieValue;
import ru.itis.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UsersService {
    void addUser(User user);
    List<User> getAllUsers();
    List<User> getUsersWithAge(int age);
    User getUserByLogin(String login);
    void update(User user);
    CookieValue getCookieValue(Long id, String name);
    boolean isAuthenticated(String cookie);
    String authorize(String login, String password);
    //void addUserCookie(CookieValue cookieValue);
    boolean checkPassword(String password, String hashPassword);
    String getHashPassword(String password);
    String register(User user);
    User getUserByUuid(String uuid);
}
