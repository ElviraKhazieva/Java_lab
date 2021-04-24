//package ru.itis.config;
//
//import org.springframework.core.env.PropertySource;
//import org.springframework.core.io.support.ResourcePropertySource;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import ru.itis.security.config.SecurityConfig;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import java.io.IOException;
//
////use AbstractAnnotationConfigDispatcherServletInitializer class to register and initialize the DispatcherServlet
//public class MvcWebApplicationInitializer
//        extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();
//
//        PropertySource propertySource = null;
//        try {
//            propertySource = new ResourcePropertySource("classpath:application.properties");
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));
//        springWebContext.register(ApplicationConfig.class, SecurityConfig.class);
//        springWebContext.setServletContext(servletContext);
//        servletContext.addListener(new ContextLoaderListener(springWebContext));
//
//        ServletRegistration.Dynamic dispatcherServlet =
//                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
//        dispatcherServlet.setLoadOnStartup(1);
//        dispatcherServlet.addMapping("/");
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        FilterRegistration.Dynamic filterRegistration = servletContext
//                .addFilter("characterEncodingFilter", characterEncodingFilter);
//        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
//    }
//
//    // Load database and spring security configuration
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { ApplicationConfig.class, SecurityConfig.class };
//    }
//
//    // Load spring web configuration
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//       return null;
//       // return new Class[] { MvcConfig.class };
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] { "/" };
//    }
//
//}
