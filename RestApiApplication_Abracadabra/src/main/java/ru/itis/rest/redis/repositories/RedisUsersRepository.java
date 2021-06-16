package ru.itis.rest.redis.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.rest.redis.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
