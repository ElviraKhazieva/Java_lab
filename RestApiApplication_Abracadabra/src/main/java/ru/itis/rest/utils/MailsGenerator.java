package ru.itis.rest.utils;

public interface MailsGenerator {

    String getMailForConfirm(String serverUrl, String code);

}

