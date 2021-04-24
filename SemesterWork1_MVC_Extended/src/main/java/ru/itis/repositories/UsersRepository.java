package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Post;
import ru.itis.models.User;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByNickname(String nickname);

    User findByConfirmCode(String confirmCode);

    List<User> findAllBySubscriptionsContains(User user);//подписчики юзера

    List<User> findAllByFollowersContains(User user);//подписки юзера






}
