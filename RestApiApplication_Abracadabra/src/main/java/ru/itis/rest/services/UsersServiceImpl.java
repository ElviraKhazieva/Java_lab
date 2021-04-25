package ru.itis.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rest.dto.SimpleUserDto;
import ru.itis.rest.dto.UserDto;
import ru.itis.rest.models.User;
import ru.itis.rest.repositories.UsersRepository;
import java.util.List;

import static ru.itis.rest.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDto getById(Long id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return from(user);
    }

    @Override
    public UserDto getByUsername(String username) {
       User user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       return from(user);
    }

    @Override
    public User getByConfirmCode(String confirmCode) {
        return usersRepository.findByConfirmCode(confirmCode).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public List<SimpleUserDto> getAllFollowers(User user) {
        return SimpleUserDto.from(usersRepository.findAllBySubscriptionsContains(user));
    }

    @Override
    public List<SimpleUserDto> getAllSubscriptions(User user) {
        return SimpleUserDto.from(usersRepository.findAllByFollowersContains(user));
    }

    @Override
    public UserDto confirmMail(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEmailState(User.EmailState.CONFIRMED);
        usersRepository.save(user);
        return from(user);
    }

    @Override
    public void banAll() {
        List<User> users = usersRepository.findAll();
        for (User user: users) {
            if (!user.isAdmin()) {
                user.setProfileState(User.ProfileState.BANNED);
                usersRepository.save(user);
            }
        }
    }

    @Override
    public UserDto addUser(UserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        usersRepository.save(newUser);
        return from(newUser);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto user) {
        User userForUpdate = usersRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        usersRepository.save(userForUpdate);
        return from(userForUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = usersRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForDelete.setIsDeleted(true);
        usersRepository.save(userForDelete);
    }

}
