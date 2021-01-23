package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailsService emailsService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String getHashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void signUp(User user) {
        String confirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(confirmCode);
        String url = "https://itdrive.pro/confirm/" + confirmCode;
        usersRepository.save(user);
        emailsService.sendMail("Подтверждение регистрации", url, user.getEmail());

    }
}
