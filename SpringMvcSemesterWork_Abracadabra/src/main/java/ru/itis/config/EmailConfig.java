package ru.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.itis")
public class EmailConfig {

    @Autowired
    public Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setDefaultEncoding(environment.getProperty("spring.mail.encoding"));

        Properties mailProperties = mailSender.getJavaMailProperties();
        mailProperties.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.allow8bitmime", environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
        mailProperties.put("mail.smtp.ssl.trust", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
        mailProperties.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));
        return mailSender;
    }

}
