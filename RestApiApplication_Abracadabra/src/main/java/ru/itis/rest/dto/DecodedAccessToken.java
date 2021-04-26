package ru.itis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rest.models.User;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DecodedAccessToken {

    private Long id;

    private Date expiration;

    private User.Role role;

    private User.ProfileState profileState;

    private String email;

}
