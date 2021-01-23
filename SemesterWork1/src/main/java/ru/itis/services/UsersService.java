package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface UsersService {

    Optional<UserDto> getUserById(Long id);

    Optional<UserDto> getUserByEmail(String email);

    Optional<UserDto> getUserByNickname(String nickname);

    List<UserDto> getAllUsers();

    List<SimpleUserDto> getAllFollowers(User user);

    List<SimpleUserDto> getAllSubscriptions(User user);

    void subscribe(User userFrom, User userTo);

    void unsubscribe(User userFrom, User userTo);

    Optional<User> getUserByUuid(String value);

}
