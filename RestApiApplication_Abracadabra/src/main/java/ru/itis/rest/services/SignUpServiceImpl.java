package ru.itis.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rest.dto.UserSignUpForm;
import ru.itis.rest.models.User;
import ru.itis.rest.repositories.UsersRepository;
import ru.itis.rest.utils.EmailUtil;
import ru.itis.rest.utils.MailsGenerator;

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
                .username(userForm.getUsername())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .role(User.Role.USER)
                .profileState(User.ProfileState.ACTIVE)
                .emailState(User.EmailState.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(newUser);
        //String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
        //emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
        return newUser;
    }
}
