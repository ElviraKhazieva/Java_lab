package ru.itis.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.Tag;
import ru.itis.models.User;
import ru.itis.repositories.PostsRepository;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.sql.Date;
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

    private MultipartFile [] files;

    List<String> fileNames;

    private SimpleUserDto author;

    private Date date;

    private Integer commentsCount;

    private Integer likesCount;

    public static PostDto from(Post post, SimpleUserDto user) {
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
                .build();
    }

}
