package ru.itis.repositories;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

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
            .build();



    @Override
    public List<User> findAllByAge(int age) {
        return template.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, userRowMapper);
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
}
