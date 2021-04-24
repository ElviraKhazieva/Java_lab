package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.UserSignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.utils.EmailUtil;
import ru.itis.utils.MailsGenerator;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail.subject}")
    private String subject;

    @Override
    public User signUp(UserSignUpForm userForm) {
        User newUser = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .nickname(userForm.getNickname())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.NOT_CONFIRMED)
                .build();

        usersRepository.save(newUser);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
        emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
        return newUser;
    }
}
