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

public class Tag {

    private Long id;
    private String tag;

}
