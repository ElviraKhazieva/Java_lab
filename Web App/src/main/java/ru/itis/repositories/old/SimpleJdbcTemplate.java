//package ru.itis.repositories.old;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class SimpleJdbcTemplate {
//    private DataSource dataSource;
//
//    public SimpleJdbcTemplate(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public <T> List<T> SelectQuery(String sql, RowMapper<T> rowMapper, Object ... args) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            for (int i = 0; i < args.length; i++) {
//                statement.setObject(i + 1, args[i]);
//            }
//            resultSet = statement.executeQuery();
//            List<T> result = new ArrayList<>();
//            while (resultSet.next()) {
//                result.add(rowMapper.mapRow(resultSet));
//            }
//            return result;
//        } catch (SQLException throwables) {
//            throw new IllegalArgumentException();
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
//                    // ignore
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    // ignore
//                }
//            }
//        }
//    }
//
//    public <T> void UpdateOrDeleteQuery(String sql, RowMapper<T> rowMapper, Object ... args) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            for (int i = 0; i < args.length; i++) {
//                statement.setObject(i + 1, args[i]);
//            }
//            statement.execute();
//        } catch (SQLException throwables) {
//            throw new IllegalArgumentException();
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException throwables) {
//                    // ignore
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    // ignore
//                }
//            }
//        }
//    }
//    public <T> Long InsertQuery(String sql, RowMapper<T> rowMapper, Object ... args) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            for (int i = 0; i < args.length; i++) {
//                statement.setObject(i + 1, args[i]);
//            }
//            resultSet = statement.executeQuery();
//            resultSet.next();
//            return resultSet.getLong("id");
//        } catch (SQLException throwables) {
//            throw new IllegalArgumentException(throwables);
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
//                    // ignore
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    // ignore
//                }
//            }
//        }
//    }
//}
