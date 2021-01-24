package ru.itis.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String hashPassword;
    private String confirmCode;
    private Boolean isDeleted;

}
