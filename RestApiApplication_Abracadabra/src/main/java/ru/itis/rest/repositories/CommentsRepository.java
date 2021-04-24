package ru.itis.rest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest.models.Comment;
import ru.itis.rest.models.Post;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

}
