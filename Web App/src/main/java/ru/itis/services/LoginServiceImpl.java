package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.CookieValue;
import ru.itis.models.User;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.UsersRepository;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    public String authorize(String login, String password) {
        Optional<User> optionalUser = usersRepository.findByEmail(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getHashPassword())) {
                String uuid = UUID.randomUUID().toString();
                CookieValue cookieValue = CookieValue.builder()
                        .name("Auth")
                        .value(uuid)
                        .user(user)
                        .build();
                cookieValuesRepository.update(cookieValue);
                return uuid;
            }
        }
        return null;
    }
}
