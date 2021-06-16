package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    UserDto getById(Long id);

    UserDto getByUsername(String nickname);

    User getByConfirmCode(String confirmCode);

    List<UserDto> getAllUsers();

    List<SimpleUserDto> getAllFollowers(User user);

    List<SimpleUserDto> getAllSubscriptions(User user);

    UserDto confirmMail(String confirmCode);

    void subscribe(Long userFrom, Long userTo);

    void unsubscribe(Long userFrom, Long userTo);

    void banAll();
}
