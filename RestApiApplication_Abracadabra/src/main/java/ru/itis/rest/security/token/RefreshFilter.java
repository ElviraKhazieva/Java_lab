package ru.itis.rest.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.rest.exceptions.TokenExpiredException;
import ru.itis.rest.models.RefreshToken;
import ru.itis.rest.repositories.TokensRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class RefreshFilter extends OncePerRequestFilter {

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if (!httpServletRequest.getServletPath().startsWith("/refresh")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String refreshToken = httpServletRequest.getParameter("refreshToken");
        Optional<RefreshToken> refreshTokenOptional = tokensRepository.findByToken(refreshToken);
        RefreshToken token = refreshTokenOptional.orElseThrow(() -> new UsernameNotFoundException("Token not found"));

        if (token.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new TokenExpiredException("token expired");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
