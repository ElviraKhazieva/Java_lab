package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.CookieValue;
import java.util.*;

@Repository
public class CookieValuesRepositoryJdbcImpl implements CookieValuesRepository {
    //language=PostgreSQL
    private static final String SQL_SELECT_COOKIE_BY_STUDENT_ID_AND_COOKIE_NAME= "select * from users_cookie where student_id = :studentId and name = :name";
    //language=PostgreSQL
    private static final String SQL_SELECT_COOKIE_BY_COOKIE_VALUE = "select * from users_cookie where value = :value ";
    //language=PostgreSQL
    private static final String SQL_UPDATE_COOKIE= "update users_cookie set name = :name, value = :value where student_id = :stidentId";
    //language=PostgreSQL
    private static final String SQL_INSERT_INTO_USERS_COOKIE = "insert into users_cookie (student_id, name, value) values (:studentId, :name, :value )";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UsersRepository usersRepository;

    private RowMapper<CookieValue> cookieRowMapper = (row, i) -> CookieValue.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .value(row.getString("value"))
            .user(usersRepository.findById(row.getLong("student_id")).orElse(null))
            .build();

//    public CookieValuesRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, UsersRepository usersRepository) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//        this.usersRepository = usersRepository;
//    }

//    @Override
//    public CookieValue findCookieByStudentIdAndCookieName(Long id, String name) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("studentId", id);
//        params.put("name", name);
//        return namedParameterJdbcTemplate.queryForObject(SQL_SELECT_COOKIE_BY_STUDENT_ID_AND_COOKIE_NAME, params, cookieRowMapper);
//    }

    @Override
    public boolean cookieExist(String cookie) {
        return namedParameterJdbcTemplate.query(SQL_SELECT_COOKIE_BY_COOKIE_VALUE, Collections.singletonMap("value", cookie), cookieRowMapper).size()>0;
    }

    @Override
    public void save(CookieValue entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("name", entity.getName());
        params.put("value", entity.getValue());
        params.put("studentId", entity.getUser().getId());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(SQL_INSERT_INTO_USERS_COOKIE, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
    }

    @Override
    public void update(CookieValue entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", entity.getName());
        params.put("value", entity.getValue());
        params.put("studentId", entity.getUser().getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_COOKIE, params);
    }

    @Override
    public void delete(CookieValue entity) {

    }

    @Override
    public Optional<CookieValue> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<CookieValue> findAll() {
        return null;
    }
}
