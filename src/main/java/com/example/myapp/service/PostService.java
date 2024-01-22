package com.example.myapp.service;

import com.example.myapp.domain.Post;
import com.example.myapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService{

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
@Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public Optional<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
