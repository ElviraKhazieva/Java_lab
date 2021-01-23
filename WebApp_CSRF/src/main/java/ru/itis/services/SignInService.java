package ru.itis.services;


public interface SignInService {

    boolean authenticate(String email, String password);

}
