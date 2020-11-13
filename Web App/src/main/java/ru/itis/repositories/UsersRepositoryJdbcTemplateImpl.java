package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

    //private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=PostgreSQL
    private static final String SQL_INSERT = "insert into student (first_name, last_name, age, group_number, email, password) " +
            "values (:firstName, :lastName, :age, :groupNumber, :email, :hashPassword)";
    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select * from student";
    //language=PostgreSQL
    private static final String SQL_SELECT_ALL_BY_AGE = "select * from student where age = :age";
    //language=PostgreSQL
    private static final String SQL_SELECT_BY_ID = "select * from student where id = :id";
    //language=PostgreSQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from student where email = :email";
    //language=PostgreSQL
    private static final String SQL_UPDATE = "update student set first_name = :firstName, last_name = :lastName, age = :age, group_number = :groupNumber, email = :email, password= :hashPassword where id = :id";
    //language=PostgreSQL
    private static final String SQL_SELECT_USER_BY_UUID = "select student.id AS id, first_name, last_name, age, group_number, email, password from student INNER JOIN users_cookie on (student.id = student_id) where value = :uuid";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

//    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
////        this.jdbcTemplate = template;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

    @Override
    public List<User> findAllByAge(int age) {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_BY_AGE,
                Collections.singletonMap("age", age),
                userRowMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,
                    Collections.singletonMap("email", email), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User findUserByUuid(String uuid) {
        return namedParameterJdbcTemplate.queryForObject(SQL_SELECT_USER_BY_UUID,
                    Collections.singletonMap("uuid", uuid),
                    userRowMapper);

    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("age", entity.getAge());
        params.put("groupNumber", entity.getGroupNumber());
        params.put("hashPassword", entity.getHashPassword());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
    }

    @Override
    public void update(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("hashPassword", entity.getHashPassword());
        params.put("age", entity.getAge());
        params.put("groupNumber", entity.getGroupNumber());
        namedParameterJdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
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
