package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Optional<User> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public void deleteUserById(long userId) {
        usersRepository.findById(userId)
                .ifPresent(
                        user -> {
                            user.setIsDeleted(true);
                            usersRepository.update(user);
                        }
                );
    }
}
