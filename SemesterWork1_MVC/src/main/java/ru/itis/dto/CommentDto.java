package ru.itis.dto;

import lombok.*;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.Tag;
import ru.itis.models.User;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentDto {

    private Long id;

    private String text;

    private SimpleUserDto author;

    private Post post;

    private List<String> attachmentsPaths;

    private Date date;

    public static CommentDto from(Comment comment, List<String> attachmentsPaths, SimpleUserDto user) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(user)
                .post(comment.getPost())
                .date(comment.getDate())
                .attachmentsPaths(attachmentsPaths)
                .build();
    }


}
