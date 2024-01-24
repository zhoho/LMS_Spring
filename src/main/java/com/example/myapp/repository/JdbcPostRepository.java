package com.example.myapp.repository;

import com.example.myapp.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

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
        // 여기에 Post 객체의 다른 필드를 설정하세요
        return post;
    };

    @Override
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("posts").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post;
    }

    @Override
    public Optional<Post> findById(Long postId) {
        List<Post> results = jdbcTemplate.query("SELECT * FROM posts WHERE id = ?", postRowMapper, postId);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        List<Post> results = jdbcTemplate.query("SELECT * FROM posts WHERE title = ?", postRowMapper, title);
        return results.stream().findFirst();
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts", postRowMapper);
    }
}
