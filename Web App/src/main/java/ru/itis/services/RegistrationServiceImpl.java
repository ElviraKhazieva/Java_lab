package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.CookieValue;
import ru.itis.models.User;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.UsersRepository;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    public String register(User user) {
        usersRepository.save(user);
        String uuid = UUID.randomUUID().toString();
        CookieValue cookieValue = CookieValue.builder()
                .name("Auth")
                .value(uuid)
                .user(user)
                .build();
        cookieValuesRepository.save(cookieValue);
        return uuid;
    }

    public String getHashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}