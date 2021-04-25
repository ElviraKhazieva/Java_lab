package ru.itis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import ru.itis.rest.models.Post;
import ru.itis.rest.models.Token;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String token;

    public static TokenDto from(Token token) {
        if (token == null) {
            return null;
        }
        return TokenDto.builder()
                .token(token.getToken())
                .build();
    }
    public static List<TokenDto> from(List<Token> tokens) {
        return tokens.stream()
                .map(TokenDto::from)
                .collect(Collectors.toList());
    }

}
