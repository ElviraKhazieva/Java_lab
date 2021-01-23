package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User signUp(User user) {
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));
        String confirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(confirmCode);
        usersRepository.save(user);
        return user;
    }
}
