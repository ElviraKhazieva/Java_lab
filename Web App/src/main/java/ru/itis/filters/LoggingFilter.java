package ru.itis.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter("*")
public class LoggingFilter implements Filter {

    private static Logger logger = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LoggerFactory.getLogger(LoggingFilter.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //HttpServletResponse response = (HttpServletResponse)servletResponse;
        logger.info("Method: {}", request.getMethod());
        logger.info("Request URI: {}", request.getRequestURI());
        logger.info("Protocol: {}", request.getProtocol());
        logger.info("Path info: {}", request.getPathInfo());
        logger.info("Remote address: {}", request.getRemoteAddr());

        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            String value = request.getHeader(name);
            logger.info( " {} = {} ", name, value);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
