package ru.itis.services;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return  usersRepository.findAll();
    }

    @Override
    public List<User> getUsersWithAge(int age) {
        return usersRepository.findAllByAge(age);
    }
}
