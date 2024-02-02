package com.example.myapp.repository;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        post.setDate(rs.getDate("date"));
        return post;
    };

    private final RowMapper<Course> courseRowMapper = (rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setSemester(rs.getString("semester"));
        return course;
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
    public List<Post> findByTitle(String title) {
        String query = "%" + title + "%";
        return jdbcTemplate.query("SELECT * FROM posts WHERE title LIKE ?", postRowMapper, query);
    }


    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM qna", postRowMapper);
    }

    @Override
    public List<Course> findAllCourse() {
        return jdbcTemplate.query("SELECT * FROM course", courseRowMapper);
    }


    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM qna WHERE id = ?", id);
    }

    @Override
    public Post update(Post post) {
        jdbcTemplate.update("UPDATE qna SET title = ?, content = ?, date = ? WHERE id = ?",
                post.getTitle(), post.getContent(), post.getDate(), post.getId());
        return post;
    }
    @Override
    public Long findPreviousPostId(Long currentPostId) {
        String sql = "SELECT id FROM qna WHERE id < ? ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{currentPostId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public Long findNextPostId(Long currentPostId) {
        String sql = "SELECT id FROM qna WHERE id > ? ORDER BY id ASC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{currentPostId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Post> findByTitleContaining(String title) {
        String sql = "SELECT * FROM qna WHERE title LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + title + "%"}, postRowMapper);
    }
}
