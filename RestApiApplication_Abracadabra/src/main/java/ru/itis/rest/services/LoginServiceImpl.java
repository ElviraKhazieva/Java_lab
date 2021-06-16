package ru.itis.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rest.dto.DecodedAccessToken;
import ru.itis.rest.dto.EmailPasswordDto;
import ru.itis.rest.dto.TokenDto;
import ru.itis.rest.models.RefreshToken;
import ru.itis.rest.models.User;
import ru.itis.rest.redis.services.RedisUsersService;
import ru.itis.rest.repositories.TokensRepository;
import ru.itis.rest.repositories.UsersRepository;
import ru.itis.rest.utils.JwtUtil;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.refreshExpirationDateInMs}")
    private int refreshExpirationDateInMs;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private RedisUsersService redisUsersService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TokenDto login(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {

            RefreshToken refreshToken = RefreshToken.builder()
                    .token(jwtUtil.generateRefreshToken())
                    .expiration(new Timestamp(System.currentTimeMillis() + refreshExpirationDateInMs))
                    .user(user)
                    .build();
            tokensRepository.save(refreshToken);

            DecodedAccessToken userData = DecodedAccessToken.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .profileState(user.getProfileState())
                    .build();

            String accessToken = jwtUtil.generateToken(userData);
            redisUsersService.addTokenToUser(user, accessToken);

            return TokenDto.from(refreshToken, accessToken);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public TokenDto login(String refreshToken) {
        Optional<RefreshToken> refreshTokenOptional = tokensRepository.findByToken(refreshToken);
        RefreshToken token = refreshTokenOptional.orElseThrow(() -> new UsernameNotFoundException("Token not found"));

        token.setToken(jwtUtil.generateRefreshToken());
        tokensRepository.save(token);

        User user = token.getUser();
        DecodedAccessToken userData = DecodedAccessToken.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .profileState(user.getProfileState())
                .build();
        String accessToken = jwtUtil.generateToken(userData);
        redisUsersService.addTokenToUser(user, accessToken);

        return TokenDto.from(token, accessToken);
    }

}
