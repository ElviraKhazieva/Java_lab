package ru.itis.rest.redis.services;

import ru.itis.rest.models.User;

public interface RedisUsersService {

    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);

}
