package ru.itis.rest.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostForm {

    private String text;

    private MultipartFile[] files;

    private List<String> fileNames;

}
