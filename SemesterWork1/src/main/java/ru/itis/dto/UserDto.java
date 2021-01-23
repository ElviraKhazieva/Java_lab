package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.User;

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
    private List<User> followers;
    private List<User> subscriptions;
    private String nickname;
    private String email;
    private Date birthDate;


    public static UserDto from(User user, List<User> followers, List<User> subscriptions) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .followers(followers)
                .subscriptions(subscriptions)
                .birthDate(user.getBirthDate())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }
}
