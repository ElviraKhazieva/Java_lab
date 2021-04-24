package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Post;
import ru.itis.models.User;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTag(String tag);

    List<Post> findAllByAuthor(User author);

}
