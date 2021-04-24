package ru.itis.rest.dto;

import lombok.*;
import ru.itis.rest.models.Post;

import javax.persistence.OneToMany;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PostDto {

    private Long id;

    private String text;

    private List<String> paths;

    private SimpleUserDto author;

    private LocalDate date;

    private List<UserDto> likeUsers;

    private List<CommentDto> comments;

    public static PostDto from(Post post) {
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .author(SimpleUserDto.from(post.getAuthor()))
                .likeUsers(UserDto.from(post.getLikeUsers()))
                .comments(CommentDto.from(post.getComments()))
                .date(post.getDate())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

}
