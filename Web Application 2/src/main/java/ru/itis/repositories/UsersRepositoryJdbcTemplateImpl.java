package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=PostgreSQL
    private static final String SQL_INSERT = "insert into student (first_name, last_name, age, group_number, email, password) " +
            "values (:firstName, :lastName, :age, :groupNumber, :email, :password)";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_ID = "select * from student where id = ?";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from student order by id limit :limit offset :offset ;";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select * from student";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_AGE = "select * from student where age = ?";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

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
        params.put("password", entity.getPassword());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User entity) {

    }

}

