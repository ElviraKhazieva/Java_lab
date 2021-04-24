package ru.itis.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.rest.dto.*;
import ru.itis.rest.models.Post;
import ru.itis.rest.models.User;
import ru.itis.rest.repositories.CommentsRepository;
import ru.itis.rest.repositories.PostsRepository;
import ru.itis.rest.repositories.UsersRepository;
import ru.itis.rest.security.details.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentsRepository commentsRepository;


    @Override
    public Post addPost(PostForm postForm, User author) {
        Post post = Post.builder()
                .author(author)
                .text(postForm.getText())
                .paths(postForm.getFileNames())
                .build();
        postsRepository.save(post);
        return post;
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> postOptional = postsRepository.findById(id);
        return postOptional.map(PostDto::from).orElse(null);
    }


    @Override
    public List<PostDto> getAllByTag(String tag) {
        return PostDto.from(postsRepository.findAllByTag(tag));
    }

    @Override
    public List<PostDto> getAllByAuthorId(Long authorId) {
        User author = usersRepository.findById(authorId).orElseThrow(IllegalArgumentException::new);
        return PostDto.from(postsRepository.findAllByAuthor(author));
    }

    @Override
    public List<CommentDto> getAllCommentsByPost(Post post) {
        return CommentDto.from(commentsRepository.findAllByPost(post));
    }

//    @Override
//    public void likePost(User user, Post post) {
//        postsRepository.addLikeToPost(user, post);
//    }

//    @Override
//    public List<PostDto> getFeed(User user, int begin, int end) {
//        return PostDto.from(postsRepository.findFeed(user, begin, end));
//    }
//
//    @Override
//    public void removeLike(User user, Post post) {
//        postsRepository.removeLikeFromPost(user, post);
//    }

//    @Override
//    public void addComment(Comment comment) {
//        commentsRepository.save(comment);
//    }
//
//    @Override
//    public void removeComment(Comment comment) {
//        commentsRepository.delete(comment);
//    }


}
