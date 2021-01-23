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

public class Comment {

    private Long id;
    private String text;
    private User author;
    private Post post;
   // private List<String> attachmentsPaths;
    private Date date;
    //private Comment parentComment;
}
