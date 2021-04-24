package ru.itis.rest.dto;

import lombok.*;
import ru.itis.rest.models.Comment;
import ru.itis.rest.models.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    private PostDto post;

    private LocalDate date;

    public static CommentDto from(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(SimpleUserDto.from(comment.getAuthor()))
                .post(PostDto.from(comment.getPost()))
                .date(comment.getDate())
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }


}
