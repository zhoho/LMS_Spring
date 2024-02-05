package com.example.myapp.repository;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.domain.PostFile;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post savePost(Post post, Long courseId);
    Optional<Post> findById(Long postId);

    List<Post> findByCourseId(Long courseId);

    List<Post> findByTitle(String title);
    List<Post> findAll();
    List<Course> findAllCourse();

    int deleteById(Long id);
    Post update(Post post);

    Long findPreviousPostId(Long id);
    Long findNextPostId(Long id);
    List<Post> findByTitleContaining(String title);

    void saveFile(PostFile file);
}
