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
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=PostgreSQL
    private static final String SQL_INSERT_USER = "insert into simple_user(email, password, confirm_code) " +
            "values (:email, :password, :confirm_code)";

    //language=PostgreSQL
    private static final String SQL_UPDATE_USER = "update simple_user set email = :email, password = :password, confirm_code = :confirm_code, is_deleted = :is_deleted " +
            "where id = :id";

    //language=PostgreSQL
    private static final String SQL_FIND_USER_BY_ID = "select * from simple_user where id = :id";

    //language=PostgreSQL
    private static final String SQL_FIND_USER_BY_EMAIL = "select * from simple_user where email = :email";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select * from simple_user";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .confirmCode(row.getString("confirm_code"))
            .hashPassword(row.getString("password"))
            .email(row.getString("email"))
            .isDeleted(false)
            .build();

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL,
                    Collections.singletonMap("email", email),
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
        params.put("confirm_code", entity.getConfirmCode());
        params.put("password", entity.getHashPassword());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(SQL_INSERT_USER, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet generatedKeys = null;
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, entity.getEmail());
//            statement.setString(2, entity.getPassword());
//            statement.setString(3, entity.getConfirmCode());
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows != 1) {
//                throw new SQLException("User save not executed");
//            }
//
//            generatedKeys = statement.getGeneratedKeys();
//
//            if (generatedKeys.next()) {
//                Long id = generatedKeys.getLong("id");
//                entity.setId(id);
//            } else {
//                throw new SQLException("Something wrong");
//            }
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException ignored) {
//                }
//            }
//            if (generatedKeys != null) {
//                try {
//                    generatedKeys.close();
//                } catch (SQLException ignored) {
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ignored) {
//                }
//            }
//        }
//    }
    }

    @Override
    public void update(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("confirm_code", entity.getConfirmCode());
        params.put("password", entity.getHashPassword());
        params.put("is_deleted", entity.getIsDeleted());
        params.put("id", entity.getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_USER, params);
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID,
                    Collections.singletonMap("id", id),
                    userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
