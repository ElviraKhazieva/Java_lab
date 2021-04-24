package ru.itis.rest.dto;

import lombok.*;
import ru.itis.rest.models.User;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private List<UserDto> followers;

    private List<UserDto> subscriptions;

    private List<PostDto> posts;

    private List<CommentDto> comments;

    private String username;

    private String email;

    private Date birthDate;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .followers(UserDto.from(user.getFollowers()))
                .subscriptions(UserDto.from(user.getSubscriptions()))
                .birthDate(user.getBirthDate())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
