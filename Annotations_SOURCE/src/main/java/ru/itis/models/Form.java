package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class Form {
    private String action;
    private String method;
}
