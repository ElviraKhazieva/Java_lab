package ru.itis.services;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.*;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;
import ru.itis.repositories.CommentsRepository;
import ru.itis.repositories.PostsRepository;
import ru.itis.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsServiceJdbcTemplateImpl implements PostsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentsRepository commentsRepository;


    private List<CommentDto> convertToListCommentDto(List<Comment> comments) {
        List<CommentDto> listCommentDto = null;
        List<String> attachments;
        for (Comment comment: comments) {
            attachments = commentsRepository.findCommentAttachments(comment);
            listCommentDto.add(CommentDto.from(comment, attachments, convertToSimpleUserDto(comment.getAuthor())));
        }
        return listCommentDto;
    }

    private PostViewDto convertToPostViewDto(Post post) {
         return PostViewDto.from(post, postsRepository.getAttachments(post),
                convertToListCommentDto(commentsRepository.findPostComments(post)), convertToSimpleUserDto(post.getAuthor()) );
    }

    private SimpleUserDto convertToSimpleUserDto(User user) {
        Optional<User> userOptional = usersRepository.findById(user.getId());
        return userOptional.map(SimpleUserDto::from).orElse(null);
    }

    private PostDto convertToPostDto(Post post) {
        return PostDto.from(post, convertToSimpleUserDto(post.getAuthor()));
    }

    private List<PostDto> convertToListPostDto(List<Post> posts) {
        return posts.stream().map(this::convertToPostDto).collect(Collectors.toList());
    }

    @Override
    public void addPost(PostDto postDto, User author) {
        Post post = Post.builder()
                .author(author)
                .text(postDto.getText())
                .date(postDto.getDate())
                .commentsCount(postDto.getCommentsCount())
                .likesCount(postDto.getLikesCount())
                .build();
        postsRepository.save(post);
        List<String> fileNames = postDto.getFileNames();
        if (fileNames != null) {
            for(String fileName: fileNames) {
                System.out.println(fileName);
                postsRepository.saveAttachment(post, fileName);
            }
        }
//        MultipartFile [] attachments = postDto.getFiles();
//        if (attachments != null && attachments.length > 0) {
//            for (MultipartFile attachment : attachments) {
//                postsRepository.saveAttachment(post, attachment.getOriginalFilename());
//            }
//        }
    }

    @Override
    public Optional<PostViewDto> getPostById(Long id) {
        Optional<Post> postOptional = postsRepository.findById(id);
        return postOptional.map(this::convertToPostViewDto);
    }


    @Override
    public List<PostDto> getPostsByTag(String tag) {
        return convertToListPostDto(postsRepository.findByTag(tag));
    }

    @Override
    public List<PostDto> getPostsByUser(UserDto userDto) {
        User user = usersRepository.findById(userDto.getId()).get();
        return convertToListPostDto(postsRepository.findPostsByUser(user));
    }

    @Override
    public void likePost(User user, Post post) {
        postsRepository.addLikeToPost(user, post);
    }

    @Override
    public List<PostDto> getFeed(User user, int begin, int end) {
        return convertToListPostDto(postsRepository.findFeed(user, begin, end));
    }

    @Override
    public void removeLike(User user, Post post) {
        postsRepository.removeLikeFromPost(user, post);
    }

    @Override
    public void addComment(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        commentsRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getPostComments(Post post) {
        return convertToListCommentDto(commentsRepository.findPostComments(post));
    }
}
