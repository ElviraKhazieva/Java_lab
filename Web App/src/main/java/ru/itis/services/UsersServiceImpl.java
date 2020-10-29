package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.models.CookieValue;
import ru.itis.models.User;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.UsersRepository;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private CookieValuesRepository cookieValuesRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getUsersWithAge(int age) {
        return usersRepository.findAllByAge(age);
    }

    @Override
    public User getUserByLogin(String login) {
        return usersRepository.find(login);
    }

    @Override
    public void update(User user) {
        usersRepository.update(user);
    }

//    @Override
//    public void addUserCookie(CookieValue cookieValue) {
//        cookieValuesRepository.save(cookieValue);
//    }

    @Override
    public CookieValue getCookieValue(Long id, String name) {
        return cookieValuesRepository.findCookieByIdAndName(id, name);
    }

    @Override
    public String getHashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean checkPassword(String password, String hashPassword) {
        return passwordEncoder.matches(password, hashPassword);
    }

    /*public String authorize(String login, String password) {
        User user = usersRepository.find(login);
        if ((user != null) && (checkPassword(password, user.getPassword()))) {
           String uuid = UUID.randomUUID().toString();
           CookieValue cookieValue = CookieValue.builder()
                   .name("Auth")
                   .value(uuid)
                   .user(user)
                   .build();
           cookieValuesRepository.save(cookieValue);
           return uuid;
        } else {
            return null;
        }
    }*/

    public String authorize(String login, String password) {
        User user = usersRepository.find(login);
        if ((user != null) && (checkPassword(password, user.getPassword()))) {
           String uuid = UUID.randomUUID().toString();
           CookieValue cookieValue = CookieValue.builder()
                   .name("Auth")
                   .value(uuid)
                   .user(user)
                   .build();
           cookieValuesRepository.update(cookieValue);
           return uuid;
        } else {
            return null;
        }
    }


    public String register(User user) {
        String uuid = UUID.randomUUID().toString();
        CookieValue cookieValue = CookieValue.builder()
                .name("Auth")
                .value(uuid)
                .user(user)
                .build();
        cookieValuesRepository.save(cookieValue);
        return uuid;
    }



    public boolean isAuthenticated(String cookie) {
        return cookieValuesRepository.cookieExist(cookie);
    }

    public User getUserByUuid(String uuid) {
        return usersRepository.findUserByUuid(uuid);
    }
}
