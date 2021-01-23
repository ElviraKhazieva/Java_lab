package ru.itis.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.Tag;
import ru.itis.models.User;
import ru.itis.repositories.PostsRepository;

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

public class PostDto {

    private Long id;
    private String text;
    private List<String> paths;
    private SimpleUserDto author;
    private Date date;
    private Integer commentsCount;
    private Integer likesCount;

    public static PostDto from(Post post, List<String> paths, SimpleUserDto user) {
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .author(user)
                .commentsCount(post.getCommentsCount())
                .likesCount(post.getLikesCount())
                .date(post.getDate())
                .paths(paths)
                .build();
    }

}
