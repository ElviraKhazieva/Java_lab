package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.ApplicationConfig;
import ru.itis.models.User;

import java.sql.SQLException;

public class Main {
        public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
            EntityManager entityManager = applicationContext.getBean(EntityManager.class);
            entityManager.createTable("cool_user", User.class);
            User user = User.builder()
                    .id(1L)
                    .firstName("Marsel")
                    .lastName("Sidikov")
                    .isWorker(true)
                    .build();

            entityManager.save("cool_user", user);

            User user2 = entityManager.findById("cool_user", User.class, Long.class, 1L);
            System.out.println(user2.getId() + " " + user2.getFirstName() + " " + user2.getLastName() + " " + user2.isWorker());
        }



}
