package ru.itis.rest.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rest.models.User;
import ru.itis.rest.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticated(String email, String password) {
        Optional<User> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            passwordEncoder.matches(password, userOptional.get().getHashPassword());
            return true;
        }
        return false;
    }
}
