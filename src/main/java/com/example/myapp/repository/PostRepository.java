package com.example.myapp.repository;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long postId);
    List<Post> findByTitle(String title);
    List<Post> findAll();
    List<Course> findAllCourse();

    int deleteById(Long id);
    Post update(Post post);

    Long findPreviousPostId(Long id);
    Long findNextPostId(Long id);
    List<Post> findByTitleContaining(String title);
}
