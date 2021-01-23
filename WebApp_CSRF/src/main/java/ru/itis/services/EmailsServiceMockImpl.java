package ru.itis.services;

import org.springframework.stereotype.Service;

@Service
public class EmailsServiceMockImpl implements EmailsService {
    @Override
    public void sendMail(String subject, String text, String email) {
        System.err.println("Сообщение: <" + text + "> было отправлено на <" + email + ">");
    }
}
