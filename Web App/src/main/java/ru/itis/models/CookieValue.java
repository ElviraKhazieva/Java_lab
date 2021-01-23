package ru.itis.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CookieValue {

    private Long id;

    private String name;

    private String value;

    private User user;

}
