package ru.itis.models;

import lombok.*;

import java.util.Date;
import java.util.List;

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
