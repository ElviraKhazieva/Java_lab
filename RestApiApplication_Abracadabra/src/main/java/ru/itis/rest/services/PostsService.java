package ru.itis.rest.services;

import ru.itis.rest.dto.CommentDto;
import ru.itis.rest.dto.PostDto;
import ru.itis.rest.dto.PostForm;
import ru.itis.rest.models.Post;
import ru.itis.rest.models.User;
import ru.itis.rest.security.details.UserDetailsImpl;

import java.util.List;

public interface PostsService {

    Post addPost(PostForm post, User author);

    PostDto getPostById(Long id);

    List<PostDto> getAllByTag(String tag);

    List<PostDto> getAllByAuthorId(Long authorId);

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
