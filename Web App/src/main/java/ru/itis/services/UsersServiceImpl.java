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


@Service
public class UsersServiceImpl implements UsersService {

    // @Qualifier(value = "usersRepositoryJdbcTemplateImpl") - уточнить, какой именно бин нужен из тех, что реализуют UsersRepository)
    @Autowired
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
    public boolean authenticateCookie(String cookie) {
        return cookieValuesRepository.cookieExist(cookie);
    }

    @Override
    public User getUserByUuid(String uuid) {
        return usersRepository.findUserByUuid(uuid);
    }

//    @Override
//    public void addUserCookie(CookieValue cookieValue) {
//        cookieValuesRepository.save(cookieValue);
//    }
//    @Override
//    public CookieValue getCookieValue(Long id, String name) {
//        return cookieValuesRepository.findCookieByIdAndName(id, name);
//    }

}
