package ru.itis.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(String email, String password) {
        User user = usersRepository.findByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getHashPassword())) {
                return user;
            }
        }
        return null;
    }
}
