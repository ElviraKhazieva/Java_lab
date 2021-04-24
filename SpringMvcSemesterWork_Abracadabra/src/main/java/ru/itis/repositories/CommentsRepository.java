package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Comment;
import ru.itis.models.Post;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

}
