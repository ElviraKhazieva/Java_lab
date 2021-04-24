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
        Optional<User> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getHashPassword())) {
//                String confirmCode = UUID.randomUUID().toString();
//                user.setConfirmCode(confirmCode);
//                usersRepository.update(user);
                return user;
            }
        }
        return null;
    }
}
