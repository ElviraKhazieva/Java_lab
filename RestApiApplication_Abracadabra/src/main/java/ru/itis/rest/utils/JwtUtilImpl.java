package ru.itis.rest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.rest.dto.DecodedAccessToken;
import ru.itis.rest.models.User;
import java.util.Date;
import java.util.UUID;

@Data
@Component
public class JwtUtilImpl implements JwtUtil {

    private String secret;

    private int jwtExpirationInMs;

    private int refreshExpirationDateInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    @Value("${jwt.refreshExpirationDateInMs}")
    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    @Override
    public String generateToken(DecodedAccessToken user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getProfileState().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("seckret_key"))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public DecodedAccessToken decode(String token) {

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);

        return DecodedAccessToken.builder()
                .id(Long.valueOf(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .expiration(decodedJWT.getExpiresAt())
                .profileState(User.ProfileState.valueOf(decodedJWT.getClaim("state").asString()))
                .role(User.Role.valueOf(decodedJWT.getClaim("role").asString()))
                .build();
    }

}
