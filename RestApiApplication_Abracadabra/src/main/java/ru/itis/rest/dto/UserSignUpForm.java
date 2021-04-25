package ru.itis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpForm {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

   // private Date birthDate;

}
