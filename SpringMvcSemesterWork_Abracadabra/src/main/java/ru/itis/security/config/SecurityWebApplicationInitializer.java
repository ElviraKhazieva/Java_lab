package ru.itis.security.config;

import org.springframework.security.web.context.*;

// зарегистрирует фильтр springSecurityFilterChain для каждого URL-адреса в приложении
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {
}