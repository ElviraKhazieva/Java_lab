package ru.itis.rest.services;

import org.springframework.stereotype.Service;
import ru.itis.rest.dto.SimpleUserDto;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.models.User;
import java.util.List;

public interface UsersService {

    UserDto getById(Long id);

    UserDto getByUsername(String nickname);

    User getByConfirmCode(String confirmCode);

    List<UserDto> getAllUsers();

    List<SimpleUserDto> getAllFollowers(User user);

    List<SimpleUserDto> getAllSubscriptions(User user);

    UserDto confirmMail(String confirmCode);

    void banAll();

    void subscribe(Long userFrom, Long userTo);

    void unsubscribe(Long userFrom, Long userTo);

    UserDto updateUser(Long userId, UserDto user);

    void deleteUser(Long userId);

    void blockUser(Long userId);
}
