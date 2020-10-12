package ru.itis.services;

import ru.itis.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    List<User> getUsersWithAge(int age);
}
