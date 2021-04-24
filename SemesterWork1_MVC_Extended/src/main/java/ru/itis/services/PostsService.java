package ru.itis.services;

import ru.itis.dto.*;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;
import java.util.List;
import java.util.Optional;

public interface PostsService {

    void addPost(PostForm post, User author);

    PostDto getPostById(Long id);

    List<PostDto> getAllByTag(String tag);

    List<PostDto> getAllByAuthor(UserDto user);

    List<CommentDto> getAllCommentsByPost(Post post);

//    void likePost(User user, Post post);
//
//    List<PostDto> getFeed(User user, int begin, int end);

//    void removeLike(User user, Post post);
//
//    void addComment(Comment comment);
//
//    void removeComment(Comment comment);


}
