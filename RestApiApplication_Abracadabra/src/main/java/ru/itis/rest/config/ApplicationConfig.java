package ru.itis.rest.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ComponentScan(basePackages = "ru.itis")
@Configuration
public class ApplicationConfig {

//    @Bean
//    public FreeMarkerViewResolver freemarkerViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftlh");
//        resolver.setContentType("text/html;charset=UTF-8");
//        return resolver;
//    }

//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() {
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setTemplateLoaderPath("classpath:templates/");
//        return configurer;
//    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000000);
        return multipartResolver;
    }
}
