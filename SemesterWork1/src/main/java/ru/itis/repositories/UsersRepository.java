package ru.itis.repositories;

import ru.itis.models.User;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    List<User> findAllFollowers(User user);

    List<User> findAllSubscriptions(User user);

    void subscribe(User userFrom, User userTo);

    void unsubscribe(User userFrom, User userTo);

    boolean isSubscribed(User userFrom, User userTo);

    void updatePassword(User user);

    Optional<User> findUserByUuid(String uuid);

}
