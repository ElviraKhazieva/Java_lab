package ru.itis.rest.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@ToString(exclude = {"author"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ElementCollection
    @CollectionTable(name = "post_attachment", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    @Column(name = "path")
    private List<String> paths;

    private LocalDate date;

    private String tag;

    @ManyToMany
    @JoinTable(name = "post_likes",
    joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> likeUsers;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
