//package ru.itis.repositories;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import ru.itis.models.Post;
//import ru.itis.models.User;
//import java.sql.Date;
//
//import java.util.*;
//
//@Repository
//public class PostsRepositoryJdbcTemplateImpl implements PostsRepository {
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_POSTS_BY_USER_ID = "select * from post where author_id = :authorId";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_FEED = "select * from post where author_id in " +
//            "(select user_id from subscription where follower_id = :followerId) " +
//            "order by date limit :limit offset :offset ";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_POSTS_BY_TAGS = "select * from post where id in (select post_id from hashtag" +
//            " where tag = :tag)";
//
//    //language=PostgreSQL
//    private static final String SQL_INSERT_LIKE = "insert into post_likes(user_id, post_id) values (:userId, :postId)";
//
//    //language=PostgreSQL
//    private static final String SQL_REMOVE_LIKE = "delete from post_likes where post_id = :postId and user_id = :userId";
//
//    //language=PostgreSQL
//    private static final String SQL_UPDATE_LIKES = "update post set likes_count = :likesCount where id = :id";
//
//    //language=PostgreSQL
//    private static final String SQL_DELETE_POST = "delete from post where id = :id";
//
//    //language=PostgreSQL
//    private static final String SQL_INSERT_ATTACHMENT = "insert into post_attachment(post_id, path)" +
//            " values (:postId, :path)";
//
//    //language=PostgreSQL
//    private static final String SQL_REMOVE_ATTACHMENT = "delete from post_attachment where post_id = :postId " +
//            "and path = :path";
//
//    //language=PostgreSQL
//    private static final String SQL_INSERT_POST = "insert into post(author_id, text_content, date, likes_count," +
//            " comments_count) values (:authorId, :textContent, now(), :likesCount, :commentsCount)";
//
//    //language=PostgreSQL
//    private static final String SQL_UPDATE_POST = "update post set author_id = :authorId, text_content = :textContent," +
//            " date = :date, likes_count = :likesCount, comments_count = :commentsCount where id = :id";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_ALL = "select * from post";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_BY_ID = "select * from post where id = :id";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_ATTACHMENTS = "select path from post_attachment where post_id = :postId";
//
//    //language=PostgreSQL
//    private static final String SQL_SELECT_ALL_POSTS_WITH_LIKES_BY_USER = "select * from post where id in (select post_id from post_likes where user_id = :userId)";
//
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
//            Post.builder()
//                .author(User.builder().id(row.getLong("author_id")).build())
//                .id(row.getLong("id"))
//                .commentsCount(row.getInt("comments_count"))
//                .likesCount(row.getInt("likes_count"))
//                .text(row.getString("text_content"))
//                    .date(row.getDate("date"))
//                .build();
//
//    @Override
//    public List<Post> findByTag(String tag) {
//        return jdbcTemplate.query(SQL_SELECT_POSTS_BY_TAGS, Collections.singletonMap("tag", tag),
//                postRowMapper);
//    }
//
//    @Override
//    public List<Post> findPostsByUser(User user) {
//        return jdbcTemplate.query(SQL_SELECT_POSTS_BY_USER_ID, Collections.singletonMap("authorId", user.getId()), postRowMapper);
//    }
//
//    @Override
//    public void addLikeToPost(User user, Post post) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("userId", user.getId());
//        params.put("postId", post.getId());
//        jdbcTemplate.update(SQL_INSERT_LIKE, params);
//        incrementLikesCount(post);
//    }
//
//    private void incrementLikesCount(Post post) {
//        post.setLikesCount(post.getLikesCount() + 1);
//        Map<String, Object> params = new HashMap<>();
//        params.put("likesCount", post.getLikesCount());
//        params.put("id", post.getId());
//        jdbcTemplate.update(SQL_UPDATE_LIKES, params);
//    }
//
//    @Override
//    public List<Post> findFeed(User user, int begin, int end) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("followerId", user.getId());
//        params.put("limit", end - begin );
//        params.put("offset", begin);
//        return jdbcTemplate.query(SQL_SELECT_FEED, params, postRowMapper);
//    }
//
//    @Override
//    public void removeLikeFromPost(User user, Post post) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("userId", user.getId());
//        params.put("postId", post.getId());
//        jdbcTemplate.update(SQL_REMOVE_LIKE, params);
//        decrementLikesCount(post);
//    }
//
//    private void decrementLikesCount(Post post) {
//        post.setLikesCount(post.getLikesCount() - 1);
//        Map<String, Object> params = new HashMap<>();
//        params.put("likesCount", post.getLikesCount());
//        params.put("id", post.getId());
//        jdbcTemplate.update(SQL_UPDATE_LIKES, params);
//    }
//
//    @Override
//    public void saveAttachment(Post post, String path) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("path", path);
//        params.put("postId", post.getId());
//        jdbcTemplate.update(SQL_INSERT_ATTACHMENT, params);
//    }
//
//    @Override
//    public void removeAttachment(Post post, String path) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("path", path);
//        params.put("postId", post.getId());
//        jdbcTemplate.update(SQL_REMOVE_ATTACHMENT, params);
//    }
//
//    @Override
//    public List<String> getAttachments(Post post) {
//        return jdbcTemplate.query(SQL_SELECT_ATTACHMENTS, Collections.singletonMap("postId", post.getId()), (row, i) -> row.getString("path") );
//    }
//
//
//    @Override
//    public void save(Post entity) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        Map<String, Object> params = new HashMap<>();
//        params.put("likesCount", entity.getLikesCount());
//        params.put("commentsCount", entity.getCommentsCount());
//        params.put("authorId", entity.getAuthor().getId());
//        params.put("textContent", entity.getText());
//        params.put("date", entity.getDate());
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
//        jdbcTemplate.update(SQL_INSERT_POST, sqlParameterSource, keyHolder, new String[] { "id" });
//        Long id = (Long) keyHolder.getKey();
//        entity.setId(id);
//        //:authorId, :textContent, :date, :likesCount, :commentsCount
//    }
//
//    @Override
//    public void update(Post entity) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("likesCount", entity.getLikesCount());
//        params.put("commentsCount", entity.getCommentsCount());
//        params.put("authorId", entity.getAuthor().getId());
//        params.put("textContent", entity.getText());
//        params.put("date", entity.getDate());
//        params.put("id", entity.getId());
//        jdbcTemplate.update(SQL_UPDATE_POST, params);
//    }
//
//    @Override
//    public void delete(Post entity) {
//        jdbcTemplate.update(SQL_DELETE_POST, Collections.singletonMap("id", entity.getId()));
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        try {
//            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
//                    Collections.singletonMap("id", id),
//                    postRowMapper));
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, postRowMapper);
//    }
//}
