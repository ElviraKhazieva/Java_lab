package ru.itis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rest.models.RefreshToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String refreshToken;
    private String accessToken;

    public static TokenDto from(RefreshToken refreshToken, String accessToken) {
        if (refreshToken == null) {
            return null;
        }
        return TokenDto.builder()
                .refreshToken(refreshToken.getToken())
                .accessToken(accessToken)
                .build();
    }

}
