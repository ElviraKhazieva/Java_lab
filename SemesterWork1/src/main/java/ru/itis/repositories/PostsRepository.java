package ru.itis.repositories;

import javafx.geometry.Pos;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;
import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Long> {

    List<Post> findByTag(String tag);
    List<Post> findPostsByUser(User user);
    void addLikeToPost(User user, Post post);
    List<Post> findFeed(User user, int begin, int end);
    void removeLikeFromPost(User user, Post post);
    void addAttachment(Post post, String path);
    void removeAttachment(Post post, String path);
    List<String> getAttachments(Post post);
}
