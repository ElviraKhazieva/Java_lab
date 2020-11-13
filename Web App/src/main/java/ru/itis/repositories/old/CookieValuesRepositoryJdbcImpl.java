//package ru.itis.repositories.old;
//
//import ru.itis.models.CookieValue;
//import ru.itis.repositories.CookieValuesRepository;
//import ru.itis.repositories.UsersRepository;
//import ru.itis.repositories.old.RowMapper;
//import ru.itis.repositories.old.SimpleJdbcTemplate;
//import ru.itis.repositories.old.UsersRepositoryJdbcImpl;
//
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Optional;
//
//public class CookieValuesRepositoryJdbcImpl implements CookieValuesRepository {
//    //language=PostgreSQL
//    private static final String SQL_FIND_COOKIE = "select * from users_cookie where student_id = ? and name = ?";
//    //language=PostgreSQL
//    private static final String SQL_INSERT_INTO_USERS_COOKIE = "insert into users_cookie (student_id, name, value) values (?, ?, ?) returning id";
//    //language=PostgreSQL
//    private static final String SQL_COOKIE_EXIST = "select * from users_cookie where value = ?";
//    //language=PostgreSQL
//    private static final String SQL_UPDATE_COOKIE= "update users_cookie set name = ?, value = ? where student_id = ?";
//
//    private DataSource dataSource;
//    private SimpleJdbcTemplate template;
//
//    public CookieValuesRepositoryJdbcImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//        template = new SimpleJdbcTemplate(this.dataSource);
//    }
//
//    UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
//    private RowMapper<CookieValue> cookieRowMapper = row -> CookieValue.builder()
//            .id(row.getLong("id"))
//            .name(row.getString("name"))
//            .value(row.getString("value"))
//            .user(usersRepository.findById(row.getLong("student_id")).orElse(null))
//            .build();
//
//    @Override
//    public CookieValue findCookieByIdAndName(Long id, String name) {
//        List<CookieValue> cookies = template.SelectQuery(SQL_FIND_COOKIE, cookieRowMapper, id, name);
//        if (cookies.size() > 0) {
//            return (cookies.get(0));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean cookieExist(String cookie) {
//        return (template.SelectQuery(SQL_COOKIE_EXIST, cookieRowMapper, cookie).size() > 0);
//    }
//
//    @Override
//    public void save(CookieValue entity) {
//        Long id = template.InsertQuery(SQL_INSERT_INTO_USERS_COOKIE, cookieRowMapper,  entity.getUser().getId(), entity.getName(), entity.getValue());
//        entity.setId(id);
//    }
//    @Override
//    public void update(CookieValue entity) {
//        template.UpdateOrDeleteQuery(SQL_UPDATE_COOKIE, cookieRowMapper, entity.getName(), entity.getValue(), entity.getUser().getId());
//    }
//
//    @Override
//    public void delete(CookieValue entity) {
//    }
//
//    @Override
//    public Optional<CookieValue> findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<CookieValue> findAll() {
//        return null;
//    }
//}
