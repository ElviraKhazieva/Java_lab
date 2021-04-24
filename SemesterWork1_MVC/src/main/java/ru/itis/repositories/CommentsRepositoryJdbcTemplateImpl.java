package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;
import ru.itis.models.Post;
import ru.itis.models.User;

import java.util.*;

@Repository
public class CommentsRepositoryJdbcTemplateImpl implements CommentsRepository {

    //language=PostgreSQL
    private static final String SQL_SELECT_COMMENTS_BY_POST = "select * from comment where post_id = :postId";

    //language=PostgreSQL
    private static final String SQL_INSERT_ATTACHMENT = "insert into comment_attachment(comment_id, path) values (:commentId, :path)";

    //language=PostgreSQL
    private static final String SQL_REMOVE_ATTACHMENT = "delete from comment_attachment where comment_id = :commentId and path = :path";

    //language=PostgreSQL
    private static final String SQL_INSERT_COMMENT = "insert into comment(author_id, post_id, text, date) values (:authorId, :postId, :text, :date)";

    //language=PostgreSQL
    private static final String SQL_UPDATE_COMMENT = "update comment set author_id = :authorId, post_id = :postId, text = :text, date = :date where id = :id";

    //language=PostgreSQL
    private static final String SQL_DELETE_COMMENT = "delete from comment where id = :id";

    //language=PostgreSQL
    private static final String SQL_SELECT_BY_ID = "select * from comment where id = :id";

    //language=PostgreSQL
    private static final String SQL_SELECT_ALL = "select * from comment";

    //language=PostgreSQL
    private static final String SQL_SELECT_ATTACHMENTS = "select path from comment_attachment where comment_id = :commentId";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Comment> commentRowMapper = (row, rowNumber) ->
            Comment.builder()
                    .author(User.builder().id(row.getLong("author_id")).build())
                    .post(Post.builder().id(row.getLong("post_id")).build())
                    .text(row.getString("text"))
                    .id(row.getLong("id"))
                    .build();

    @Override
    public List<Comment> findPostComments(Post post) {
        return jdbcTemplate.query(SQL_SELECT_COMMENTS_BY_POST, Collections.singletonMap("postId", post.getId()), commentRowMapper);
    }

    @Override
    public void addAttachment(Comment comment, String path) {
        Map<String, Object> params = new HashMap<>();
        params.put("path", path);
        params.put("commentId", comment.getId());
        jdbcTemplate.update(SQL_INSERT_ATTACHMENT, params);
    }

    @Override
    public void removeAttachment(Comment comment, String path) {
        Map<String, Object> params = new HashMap<>();
        params.put("path", path);
        params.put("commentId", comment.getId());
        jdbcTemplate.update(SQL_REMOVE_ATTACHMENT, params);
    }

    @Override
    public List<String> findCommentAttachments(Comment comment) {
        return jdbcTemplate.query(SQL_SELECT_ATTACHMENTS,Collections.singletonMap("commentId", comment.getId()),
                (row, i) -> row.getString("path"));
    }

    @Override
    public void save(Comment entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", entity.getAuthor().getId());
        params.put("postId", entity.getPost().getId());
        params.put("text", entity.getText());
        params.put("date", entity.getDate());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update(SQL_INSERT_COMMENT, sqlParameterSource, keyHolder, new String[] { "id" });
        Long id = (Long) keyHolder.getKey();
        entity.setId(id);
    }

    @Override
    public void update(Comment entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorId", entity.getAuthor().getId());
        params.put("postId", entity.getPost().getId());
        params.put("text", entity.getText());
        params.put("date", entity.getDate());
        params.put("id", entity.getId());
        jdbcTemplate.update(SQL_UPDATE_COMMENT, params);
    }

    @Override
    public void delete(Comment entity) {
        jdbcTemplate.update(SQL_DELETE_COMMENT, Collections.singletonMap("id", entity.getId()));
    }

    @Override
    public Optional<Comment> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id),
                    commentRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, commentRowMapper);
    }

}
