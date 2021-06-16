package ru.itis.services;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @Override
    public UserDto getById(Long id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDto.from(user);
    }

    @Override
    public UserDto getByUsername(String username) {
       User user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       return UserDto.from(user);
    }

    @Override
    public User getByConfirmCode(String confirmCode) {
        return usersRepository.findByConfirmCode(confirmCode).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
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
    public void subscribe(Long userFrom, Long userTo) {
        User from = usersRepository.getOne(userFrom);
        User to = usersRepository.getOne(userTo);
        if (!isSubscribed(from, to)) {
            from.getSubscriptions().add(to);
            to.getFollowers().add(from);
            usersRepository.save(from);
        }

    }

    private boolean isSubscribed(User from, User to) {
        return from.getSubscriptions().contains(to);
    }

    @Override
    public void unsubscribe(Long userFrom, Long userTo) {
        User from = usersRepository.getOne(userFrom);
        User to = usersRepository.getOne(userTo);
        if (isSubscribed(from, to)) {
            from.getSubscriptions().remove(to);
            to.getFollowers().remove(from);
            usersRepository.save(from);
        }
    }

    @Override
    public UserDto confirmMail(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEmailState(User.EmailState.CONFIRMED);
        usersRepository.save(user);
        return UserDto.from(user);
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

}
