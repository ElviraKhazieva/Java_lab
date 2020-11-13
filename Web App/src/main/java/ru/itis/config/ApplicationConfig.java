package ru.itis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.CookieValuesRepositoryJdbcImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "ru.itis")
public class ApplicationConfig {
    @Autowired
    private  Environment environment;

//    @Bean
//    public UsersService usersService() {
//        return new UsersServiceImpl(usersRepository(), cookieValuesRepository(), passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CookieValuesRepository cookieValuesRepository() {
//        return new CookieValuesRepositoryJdbcImpl(namedParameterJdbcTemplate(), usersRepository());
//    }

//    @Bean
//    public UsersRepository usersRepository() {
//        return new UsersRepositoryJdbcTemplateImpl(namedParameterJdbcTemplate());
//    }
    @Bean//(value = "jdbcTemplate") - сами задаем id бину
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean // создали bean с id = objectMapper
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

}
