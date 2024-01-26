package com.example.myapp.repository;

import com.example.myapp.domain.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Primary
@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Post> postRowMapper = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        return post;
    };

    @Override
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("qna").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        parameters.put("date", post.getDate());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> results = jdbcTemplate.query("SELECT * FROM qna WHERE id = ?", postRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        List<Post> results = jdbcTemplate.query("SELECT * FROM qna WHERE title = ?", postRowMapper, title);
        return results.stream().findFirst();
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM qna", postRowMapper);
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM qna WHERE id = ?", id);
    }

}
