package ru.itis.rest.dto;

import lombok.*;
import ru.itis.rest.models.User;
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

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    public static SimpleUserDto from(User user) {
        if (user == null) {
            return null;
        }
        return SimpleUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static SimpleUserDto fromDto(UserDto user) {
        if (user == null) {
            return null;
        }
        return SimpleUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static List<SimpleUserDto> from(List<User> users) {
        return users.stream()
                .map(SimpleUserDto::from)
                .collect(Collectors.toList());
    }

    public static List<SimpleUserDto> fromDto(List<UserDto> usersDto) {
        return usersDto.stream()
                .map(SimpleUserDto::fromDto)
                .collect(Collectors.toList());
    }


}
