package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInForm {

    @NotBlank(message = "{errors.empty.password}")
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    @Email(message = "{errors.incorrect.email}")
    private String email;


}
