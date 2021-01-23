package ru.itis.models;

import lombok.*;

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

    private Integer age;

    private String groupNumber;

    private String email;

    private String hashPassword;

}

