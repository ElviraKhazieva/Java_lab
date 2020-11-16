package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
@Component
public class EntityManager {
    //private DataSource dataSource;
    //private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public EntityManager(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    // createTable("account", User.class);
    // сгенерировать CREATE TABLE на основе класса
    // create table account ( id integer, firstName varchar(255), ...))
    public <T> void createTable(String tableName, Class<T> entityClass) throws SQLException {
        StringBuilder sql = new StringBuilder("create table " + tableName + " " + "(");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String type = fields[i].getType().getSimpleName();
            String databaseType = checkType(type);
            if (i != fields.length - 1) {
                sql.append(fields[i].getName()).append(" ").append(databaseType).append(", ");
            } else {
                sql.append(fields[i].getName()).append(" ").append(databaseType);
            }
        }
        sql.append(");");
        jdbcTemplate.execute(String.valueOf(sql));
    }

    public static String checkType(String type) {
//        return switch(type) {
//            case "String", "Character", "char" -> "varchar(30)";
//            case "Integer", "int" -> "integer";
//            case "Byte", "byte", "Short", "short" -> "smallint";
//            case "Long", "long" -> "bigint";
//            case "Double", "double", "Float", "float" -> "real";
//            case "Boolean", "boolean" -> "boolean";
//            default -> throw new IllegalStateException("Unexpected value: " + type);
//        };
        switch (type) {
            case "String":
            case "Character":
            case "char":
                return "varchar(30)";

            case "Integer":
            case "int":
                return "integer";

            case "Byte":
            case "byte":
            case "Short":
            case "short":
                return "smallint";

            case "Long":
            case "long":
                return "bigint";

            case "Double":
            case "double":
            case "Float":
            case "float":
                return "real";

            case "Boolean":
            case "boolean":
                return "boolean";

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    // сканируем его поля
    // сканируем значения этих полей
    // генерируем insert into
    public void save(String tableName, Object entity) {
        StringBuilder sql = new StringBuilder("insert into " + tableName + " ( ");
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (i != fields.length - 1) {
                sql.append(fieldName).append(", ");
            } else {
                sql.append(fieldName).append(" ) values ( ");
            }
        }
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (i != fields.length - 1) {
                    if (fields[i].getType().getSimpleName().equals("String")) {
                        sql.append("'").append(fields[i].get(entity).toString()).append("'").append(", ");
                    } else {
                        sql.append(fields[i].get(entity)).append(", ");
                    }
                } else {
                    if (fields[i].getType().getSimpleName().equals("String")) {
                        sql.append("'").append(fields[i].get(entity)).append("'").append(" );");
                    } else {
                        sql.append(fields[i].get(entity)).append(" );");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        jdbcTemplate.execute(String.valueOf(sql));
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    // сгенеририровать select
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) throws IllegalAccessException, InstantiationException, SQLException {
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(tableName).append(" where id = ").append(idValue);
        RowMapper<T> rowMapper = createRowMapper(resultType);
        return jdbcTemplate.queryForObject(String.valueOf(sql), rowMapper);
//        Map<String, Object> map = jdbcTemplate.queryForMap(String.valueOf(sql));
//        for (String key: map.keySet()) {
//            System.out.println(key);
//        }
    }

    public <T> RowMapper<T> createRowMapper(Class<T> resultType) {
        RowMapper<T> rowMapper = (row, rowNumber) -> {
            try {
                T entity = resultType.newInstance();
                Field[] fields = resultType.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    fields[i].set(entity, row.getObject(fields[i].getName().toLowerCase()));
                }
                return entity;
            } catch (IllegalAccessException | InstantiationException e) {
                throw new IllegalStateException(e);
            }
        };
        return rowMapper;
    }
}
