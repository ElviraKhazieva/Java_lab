package ru.itis.rest.utils;

import ru.itis.rest.dto.DecodedAccessToken;

public interface JwtUtil {

    String generateToken(DecodedAccessToken token);

    String generateRefreshToken();

    boolean verifyToken(String token);

    DecodedAccessToken decode(String token);

}
