package ru.itis.rest.services;

public interface JwtBlacklistService {

    void add(String token);

    boolean exists(String token);

}
