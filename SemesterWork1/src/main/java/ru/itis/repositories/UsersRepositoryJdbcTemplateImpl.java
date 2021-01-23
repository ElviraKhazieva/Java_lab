package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import java.util.*;

@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select * from simple_user";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_ID = "select * from simple_user where id = :id";

    //language=PostgreSQL
    private static final String SQL_INSERT_USER = "insert into simple_user(nickname, first_name, last_name, birth_date," +
            " email, hash_password, confirm_code) values (:nickname, :firstName, :lastName, :birthDate, :email, " +
            ":hashPassword, :confirmCode)";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from simple_user where email = :email limit 1";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_NICKNAME = "select * from simple_user where nickname = :nickname limit 1";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL_FOLLOWERS = "select * from simple_user where id in (select follower_id" +
            " from subscription where user_id = :userId)";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL_SUBSCRIPTIONS = "select * from  simple_user where id in (select user_id " +
            "from subscription where follower_id = :followerId)";

    //language=PostgreSQL
    private static final String SQL_UPDATE_USER = "update simple_user set email = :email, hash_password = :password," +
            " nickname = :nickname, first_name = :firstName, last_name = :lastName, birth_date = :birthDate, " +
            "confirm_code = :confirmCode where id = :id";

    //language=PostgreSQL
    private static final String SQL_UPDATE_PASSWORD = "update simple_user set hash_password = :hashPassword where id = :id";

    //language=PostgreSQL
    private static final String SQL_DELETE_USER = "delete from simple_user where id = :id";

    //language=PostgreSQL
    private static final String SQL_INSERT_SUBSCRIPTION = "insert into subscription(user_id, follower_id) " +
            "values (:userId, :followerId)";

    //language=PostgreSQL
    private static final String SQL_REMOVE_SUBSCRIPTION = "delete from subscription " +
            "where user_id = :userId and follower_id = :followerId";

    //language=PostgreSQL
    private static final String SQL_IS_SUBSCRIBED = "select user_id from subscription " +
            "where user_id = :userId and follower_id = :followerId";

    //language=PostgreSQL
    private static final String SQL_SELECT_USER_BY_UUID = "select * from simple_user where confirm_code = :confirmCode";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, i) ->
            User.builder()
                .id(row.getLong("id"))
                .firstName(row.getString("first_name"))
                .lastName(row.getString("last_name"))
                .email(row.getString("email"))
                .hashPassword(row.getString("hash_password"))
                .confirmCode(row.getString("confirm_code"))
                .birthDate(row.getDate("birth_date"))
                .nickname(row.getString("nickname"))
                .build();


    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,
                    Collections.singletonMap("email", email),
                    userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_NICKNAME, Collections.singletonMap("nickname", nickname),
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

    }

    @Override
    public List<User> findAllFollowers(User user) {
        return jdbcTemplate.query(SQL_SELECT_ALL_FOLLOWERS, Collections.singletonMap("userId", user.getId()), userRowMapper);
    }

    @Override
    public List<User> findAllSubscriptions(User user) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUBSCRIPTIONS, Collections.singletonMap("followerId", user.getId()), userRowMapper);
    }

    @Override
    public void subscribe(User userFrom, User userTo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userTo);
        params.put("followerId", userFrom);
        jdbcTemplate.update(SQL_INSERT_SUBSCRIPTION, params);
    }

    @Override
    public void unsubscribe(User userFrom, User userTo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userTo);
        params.put("followerId", userFrom);
        jdbcTemplate.update(SQL_REMOVE_SUBSCRIPTION, params);
    }

    @Override
    public boolean isSubscribed(User userFrom, User userTo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userTo);
        params.put("followerId", userFrom);
        return  jdbcTemplate.query(SQL_IS_SUBSCRIBED, params, (row, i) -> 1).size() > 0;
    }

    @Override
    public void updatePassword(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("hashPassword", user.getEmail());
        params.put("id", user.getId());
        jdbcTemplate.update(SQL_UPDATE_PASSWORD, params);
    }

    @Override
    public Optional<User> findUserByUuid(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_UUID,
                    Collections.singletonMap("confirmCode", uuid),
                    userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("nickname", entity.getNickname());
        params.put("birthDate", entity.getBirthDate());
        params.put("confirmCode", entity.getConfirmCode());
        params.put("hashPassword", entity.getHashPassword());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update(SQL_INSERT_USER, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
    }

    @Override
    public void update(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("nickname", entity.getNickname());
        params.put("birthDate", entity.getBirthDate());
        params.put("confirmCode", entity.getConfirmCode());
        params.put("password", entity.getHashPassword());
        params.put("id", entity.getId());
        jdbcTemplate.update(SQL_UPDATE_USER, params);
    }

    @Override
    public void delete(User entity) {
        jdbcTemplate.update(SQL_DELETE_USER, Collections.singletonMap("id", entity.getId()));
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id),
                    userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
