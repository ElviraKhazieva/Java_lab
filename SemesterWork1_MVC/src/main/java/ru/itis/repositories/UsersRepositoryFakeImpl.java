package ru.itis.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

@Profile("dev")
@Repository
public class UsersRepositoryFakeImpl implements UsersRepository {
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllFollowers(User user) {
        return null;
    }

    @Override
    public List<User> findAllSubscriptions(User user) {
        return null;
    }

    @Override
    public void subscribe(User userFrom, User userTo) {

    }

    @Override
    public void unsubscribe(User userFrom, User userTo) {

    }

    @Override
    public boolean isSubscribed(User userFrom, User userTo) {
        return false;
    }

    @Override
    public void updatePassword(User user) {

    }

    @Override
    public Optional<User> findUserByConfirmCode(String confirmCode) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        System.out.println(User.builder()
                .firstName("FAKE")
                .lastName("FAKEOFF")
                .build());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.of(User.builder()
        .firstName("FAKE")
        .lastName("FAKEOFF")
        .build());
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
