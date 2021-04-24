package ru.itis.utils;

public interface EmailUtil {

    void sendMail(String to, String subject, String from, String text);

}
