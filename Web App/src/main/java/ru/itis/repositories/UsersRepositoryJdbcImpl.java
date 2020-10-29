package ru.itis.repositories;

import ru.itis.models.CookieValue;
import ru.itis.models.User;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    //language=PostgreSQL
    private static final String SQL_INSERT_INTO_STUDENT = "insert into student (first_name, last_name, age, group_number, email, password) values (?, ?, ?, ?, ?, ?) returning id";
    //language=PostgreSQL
    private static final String SQL_FIND_ALL = "select * from student";
    //language=PostgreSQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";
    //language=PostgreSQL
    private static final String SQL_FIND_BY_LOGIN = "select * from student where email = ?";
    //language=PostgreSQL
    private static final String SQL_UPDATE = "update student set first_name = ?, last_name = ?, age = ?, group_number = ?, email = ?, password= ? where id = ?";
    //language=PostgreSQL
    private static final String SQL_FIND_USER_BY_UUID = "select student.id AS id, first_name, last_name, age, group_number, email, password from student INNER JOIN users_cookie on (student.id=student_id) where value=?";
    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new SimpleJdbcTemplate(this.dataSource);

    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .groupNumber(row.getString("group_number"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();



    @Override
    public List<User> findAllByAge(int age) {
        return template.SelectQuery(SQL_FIND_ALL_BY_AGE, userRowMapper, age);
    }

    @Override
    public void save(User entity) {
        Long id = template.InsertQuery(SQL_INSERT_INTO_STUDENT, userRowMapper, entity.getFirstName(), entity.getLastName(), entity.getAge(),
                entity.getGroupNumber(), entity.getEmail(), entity.getPassword());
        entity.setId(id);
    }

    @Override
    public void update(User entity) {
        template.UpdateOrDeleteQuery(SQL_UPDATE, userRowMapper, entity.getFirstName(), entity.getLastName(), entity.getAge(),
                entity.getGroupNumber(), entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(User entity) {
    }

    @Override
    public User findById(Long aLong) {
        return null;
    }


    @Override
    public List<User> findAll() {
        return template.SelectQuery(SQL_FIND_ALL, userRowMapper);
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dataSource.getConnection();
//            connection.createStatement();
//            resultSet = statement.executeQuery(SQL_FIND_ALL);
//
//            List<User> result = new ArrayList<>();
//            while (resultSet.next()) {
//                result.add(userRowMapper.mapRow(resultSet));
//            }
//            return result;
//        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException throwables) {
//                    //ignore
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException throwables) {
//                    //ignore
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    //ignore
//                }
//            }
//        }
    }
    @Override
    public User find(String login) {
        List<User> users = template.SelectQuery(SQL_FIND_BY_LOGIN, userRowMapper, login);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
   }

    @Override
    public User findUserByUuid(String uuid) {
        List<User> list = template.SelectQuery(SQL_FIND_USER_BY_UUID, userRowMapper, uuid);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
