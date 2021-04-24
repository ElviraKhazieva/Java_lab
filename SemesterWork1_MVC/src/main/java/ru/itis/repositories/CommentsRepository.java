package ru.itis.repositories;

import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Long> {

    List<Comment> findPostComments(Post post);

    void addAttachment(Comment comment, String path);

    void removeAttachment(Comment comment, String path);

    List<String> findCommentAttachments(Comment comment);

}
