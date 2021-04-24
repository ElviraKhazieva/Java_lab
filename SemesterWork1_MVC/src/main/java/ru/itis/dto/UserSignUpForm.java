package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.ValidNames;
import ru.itis.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidNames(message = "{errors.invalid.names}",
            name = "firstName",
            surname = "lastName"
)
public class UserSignUpForm {

    private String firstName;

    private String lastName;

    @NotBlank(message = "{errors.empty.nickname}")
    private String nickname;

    @NotBlank(message = "{errors.empty.password}")
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    @Email(message = "{errors.incorrect.email}")
    private String email;

   // private Date birthDate;

}
