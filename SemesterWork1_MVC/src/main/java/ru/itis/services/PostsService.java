package ru.itis.services;

import ru.itis.dto.CommentDto;
import ru.itis.dto.PostDto;
import ru.itis.dto.PostViewDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;
import java.util.List;
import java.util.Optional;

public interface PostsService {

    void addPost(PostDto post, User author);

    Optional<PostViewDto> getPostById(Long id);

    List<PostDto> getPostsByTag(String tag);

    List<PostDto> getPostsByUser(UserDto user);

    void likePost(User user, Post post);

    List<PostDto> getFeed(User user, int begin, int end);

    void removeLike(User user, Post post);

    void addComment(Comment comment);

    void removeComment(Comment comment);

    List<CommentDto> getPostComments(Post post);

}
