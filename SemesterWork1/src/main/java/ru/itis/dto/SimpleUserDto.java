package ru.itis.dto;

import lombok.*;
import ru.itis.models.User;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SimpleUserDto {

    private Long id;
    private String nickname;

    public static SimpleUserDto from(User user) {
        if (user == null) {
            return null;
        }
        return SimpleUserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }

    public static List<SimpleUserDto> from(List<User> users) {
        return users.stream()
                .map(SimpleUserDto::from)
                .collect(Collectors.toList());
    }
}
