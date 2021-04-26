package ru.itis.rest.security.details;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.rest.dto.DecodedAccessToken;
import ru.itis.rest.models.RefreshToken;
import ru.itis.rest.models.User;
import ru.itis.rest.repositories.TokensRepository;
import ru.itis.rest.utils.JwtUtil;

@Service("tokenUserDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        DecodedAccessToken decodedToken = jwtUtil.decode(token);
        User user = User.builder()
                .id(decodedToken.getId())
                .role(decodedToken.getRole())
                .profileState(decodedToken.getProfileState())
                .hashPassword("")
                .build();
        log.info(String.valueOf(decodedToken.getExpiration().getTime()));
        log.info(String.valueOf(System.currentTimeMillis()));
        boolean expired = decodedToken.getExpiration().getTime() < System.currentTimeMillis();
        UserDetails userDetails = new UserDetailsImpl(user, expired);
//        RefreshToken result = tokensRepository.findByToken(token).orElseThrow(() -> new UsernameNotFoundException("Token not found"));
          return userDetails;
    }
}
