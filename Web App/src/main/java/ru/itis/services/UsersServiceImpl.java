package ru.itis.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.models.CookieValue;
import ru.itis.models.User;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
 // @Qualifier(value = "usersRepositoryJdbcTemplateImpl") - уточнить, какой именно бин нужен из тех, что реализуют UsersRepository)
    private UsersRepository usersRepository;
    @Autowired
    private CookieValuesRepository cookieValuesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    public UsersServiceImpl(UsersRepository usersRepository, CookieValuesRepository cookieValuesRepository, PasswordEncoder passwordEncoder) {
//        this.usersRepository = usersRepository;
//        this.cookieValuesRepository = cookieValuesRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @Override
//    public void addUser(User user) {
//        usersRepository.save(user);
//    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getUsersWithAge(int age) {
        return usersRepository.findAllByAge(age);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
            return usersRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        usersRepository.update(user);
    }

//    @Override
//    public void addUserCookie(CookieValue cookieValue) {
//        cookieValuesRepository.save(cookieValue);
//    }
//    @Override
//    public CookieValue getCookieValue(Long id, String name) {
//        return cookieValuesRepository.findCookieByIdAndName(id, name);
//    }

    @Override
    public String getHashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean checkPassword(String password, String hashPassword) {
        return passwordEncoder.matches(password, hashPassword);
    }

    public String authorize(String login, String password) {
        Optional<User> optionalUser = usersRepository.findByEmail(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (checkPassword(password, user.getHashPassword())) {
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

    public boolean authenticateCookie(String cookie) {
        return cookieValuesRepository.cookieExist(cookie);
    }

    public User getUserByUuid(String uuid) {
        return usersRepository.findUserByUuid(uuid);
    }
}
