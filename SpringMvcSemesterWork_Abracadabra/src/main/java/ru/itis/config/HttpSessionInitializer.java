package ru.itis.config;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

//нам нужно убедиться, что наш контейнер сервлетов (то есть Tomcat) использует наш springSessionRepositoryFilter для каждого запроса.
// Важно, чтобы Spring Session's springSessionRepositoryFilter вызывалась до Spring Security's SpringSecurityFilterChain .
// Это гарантирует, что HttpSession, который использует Spring Security, поддерживается Spring Session.
// Расширяя AbstractHttpSessionApplicationInitializer, мы гарантируем, что bean-компонент Spring с именем springSessionRepositoryFilter зарегистрирован в нашем контейнере сервлетов для каждого запроса до Spring Security's SpringSecurityFilterChain .
@EnableJdbcHttpSession
public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {

}