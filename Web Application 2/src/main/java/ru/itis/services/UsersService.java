package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import java.util.List;

public interface UsersService {

    List<User> getAllUsers();
    List<UserDto> getAllUser(int page, int size);
    void addUser(UserDto userDto);

}
