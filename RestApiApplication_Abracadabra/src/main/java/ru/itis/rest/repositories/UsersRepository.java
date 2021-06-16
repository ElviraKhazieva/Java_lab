package ru.itis.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest.models.User;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByConfirmCode(String confirmCode);

    List<User> findAllBySubscriptionsContains(User user);//подписчики юзера

    List<User> findAllByFollowersContains(User user);//подписки юзера






}
