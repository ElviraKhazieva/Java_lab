package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.CookieValuesRepository;
import ru.itis.repositories.CookieValuesRepositoryJdbcImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener("/**")
public class CustomServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("dataSource", dataSource);
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        CookieValuesRepository cookieValuesRepository = new CookieValuesRepositoryJdbcImpl(dataSource);
        UsersService usersService = UsersServiceImpl.builder().usersRepository(usersRepository).cookieValuesRepository(cookieValuesRepository).passwordEncoder(new BCryptPasswordEncoder()).build();
        servletContext.setAttribute("usersService", usersService);
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //servletContext.setAttribute("passwordEncoder", passwordEncoder);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
