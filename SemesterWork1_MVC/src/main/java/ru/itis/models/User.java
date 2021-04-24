package ru.itis.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String nickname;

    private String confirmCode;

    private String hashPassword;

    private String email;

    private Date birthDate;

    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }

}

