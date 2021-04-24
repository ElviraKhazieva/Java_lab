package ru.itis.config;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.*;
import java.io.IOException;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        PropertySource propertySource = null;
        try {
            propertySource = new ResourcePropertySource("classpath:properties/application.properties");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));

        springWebContext.register(ApplicationConfig.class);
        springWebContext.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic filterRegistration = servletContext
                .addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

//        String TMP_FOLDER = "C:/tmp/";
//        int MAX_UPLOAD_SIZE = 1024 * 1024 * 1024;
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
//                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
//        dispatcherServlet.setMultipartConfig(multipartConfigElement);

    }
}
