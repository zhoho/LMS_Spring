package com.example.myapp.repository;

import com.example.myapp.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long postId);
    Optional<Post> findByTitle(String title);
    List<Post> findAll();
    int deleteById(Long id);


}
