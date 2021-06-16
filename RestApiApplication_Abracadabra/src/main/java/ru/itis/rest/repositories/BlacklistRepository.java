package ru.itis.rest.repositories;

public interface BlacklistRepository {

    void save(String token);

    boolean exists(String token);

}