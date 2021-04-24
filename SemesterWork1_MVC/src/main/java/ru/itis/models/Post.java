package ru.itis.models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Post {

    private Long id;

    private String text;

    private User author;

    private Date date;

    private Integer commentsCount;

    private Integer likesCount;

}
