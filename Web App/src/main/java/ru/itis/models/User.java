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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getEmail() {
        return email;
    }
}

