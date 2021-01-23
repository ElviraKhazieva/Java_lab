package ru.itis.dto;

import lombok.*;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.Tag;
import ru.itis.models.User;

import java.text.SimpleDateFormat;
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

public class PostViewDto {

    private Long id;
    private String text;
    private List<String> paths;
    private SimpleUserDto author;
    private Date date;
    private List<CommentDto> comments;
    private Integer commentsCount;
    private Integer likesCount;

    public static PostViewDto from(Post post, List<String> paths, List<CommentDto> comments, SimpleUserDto user) {
        if (post == null) {
            return null;
        }
        return PostViewDto.builder()
                .id(post.getId())
                .text(post.getText())
                .author(user)
                .commentsCount(post.getCommentsCount())
                .likesCount(post.getLikesCount())
                .date(post.getDate())
                .paths(paths)
                .comments(comments)
                .build();
    }


}
