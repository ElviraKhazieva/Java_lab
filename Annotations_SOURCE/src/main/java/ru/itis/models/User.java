package ru.itis.models;

import ru.itis.annotations.HtmlForm;
import ru.itis.annotations.HtmlInput;

@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(name = "nickname", placeholder = "Ваш ник")
    private String nickname;
    @HtmlInput(name = "email",type = "email", placeholder = "Ваша почта")
    private String email;
    @HtmlInput(name = "password", type = "password", placeholder = "Пароль")
    private String password;
}