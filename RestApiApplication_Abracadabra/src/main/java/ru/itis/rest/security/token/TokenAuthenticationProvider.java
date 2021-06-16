package ru.itis.rest.security.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.rest.exceptions.InvalidTokenException;
import ru.itis.rest.utils.JwtUtil;

@Component
@Slf4j
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("tokenUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        if (!jwtUtil.verifyToken((String) tokenAuthentication.getCredentials())) {
            throw new InvalidTokenException("Invalid token");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) tokenAuthentication.getCredentials());
        if (userDetails.isCredentialsNonExpired()) {
            tokenAuthentication.setAuthenticated(true);
        }
        tokenAuthentication.setUserDetails(userDetails);
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
