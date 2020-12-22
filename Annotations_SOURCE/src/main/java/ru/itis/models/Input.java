package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class Input {
    private String type;
    private String name;
    private String placeholder;
}
