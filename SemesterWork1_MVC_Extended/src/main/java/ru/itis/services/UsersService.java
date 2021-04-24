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

    UserDto getByNickname(String nickname);

    User getByConfirmCode(String confirmCode);

    List<UserDto> getAllUsers();

    List<SimpleUserDto> getAllFollowers(User user);

    List<SimpleUserDto> getAllSubscriptions(User user);
//
//    void subscribe(User userFrom, User userTo);
//
//    void unsubscribe(User userFrom, User userTo);

    UserDto confirmMail(String confirmCode);
}
