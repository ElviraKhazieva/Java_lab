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


    @Override
    public UserDto getById(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        return userOptional.map(UserDto::from).orElse(null);
    }

    @Override
    public UserDto getByNickname(String nickname) {
        return UserDto.from(usersRepository.findByNickname(nickname));
    }

    @Override
    public User getByConfirmCode(String confirmCode) {
        return usersRepository.findByConfirmCode(confirmCode);
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

//    @Override
//    public void subscribe(User userFrom, User userTo) {
//        usersRepository.subscribe(userFrom, userTo);
//    }
//
//    @Override
//    public void unsubscribe(User userFrom, User userTo) {
//        usersRepository.unsubscribe(userFrom, userTo);
//    }

    @Override
    public UserDto confirmMail(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode);
        user.setEmailState(User.EmailState.CONFIRMED);
        usersRepository.save(user);
        return UserDto.from(user);
    }

}
