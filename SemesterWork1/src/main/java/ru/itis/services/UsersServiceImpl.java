package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.SimpleUserDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SignInService signInService;


    private UserDto convertToUserDto(User user) {
        return UserDto.from(user, usersRepository.findAllFollowers(user), usersRepository.findAllSubscriptions(user));
    }

    private List<UserDto> convertToListUsersDto(List<User> users) {
        return users.stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        return userOptional.map(this::convertToUserDto);
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        Optional<User> userOptional = usersRepository.findByEmail(email);
        return userOptional.map(this::convertToUserDto);
    }

    @Override
    public Optional<UserDto> getUserByNickname(String nickname) {
        return usersRepository.findByNickname(nickname).map(this::convertToUserDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return convertToListUsersDto(usersRepository.findAll());
    }

    @Override
    public List<SimpleUserDto> getAllFollowers(User user) {
        return SimpleUserDto.from(usersRepository.findAllFollowers(user));
    }

    @Override
    public List<SimpleUserDto> getAllSubscriptions(User user) {
        return SimpleUserDto.from(usersRepository.findAllSubscriptions(user));
    }

    @Override
    public void subscribe(User userFrom, User userTo) {
        usersRepository.subscribe(userFrom, userTo);
    }

    @Override
    public void unsubscribe(User userFrom, User userTo) {
        usersRepository.unsubscribe(userFrom, userTo);
    }

    public Optional<User> getUserByUuid(String uuid) {
        return usersRepository.findUserByUuid(uuid);
    }

}
