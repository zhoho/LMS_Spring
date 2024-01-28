package com.example.myapp.service;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService{

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long join(Post post) {
        validateDuplicatePost(post);
        postRepository.save(post);
        return post.getId();
    }

    public Long update(Post post) {
        validateDuplicatePost(post);
        postRepository.update(post);
        return post.getId();
    }

    private void validateDuplicatePost(Post post) {
        Optional<Post> result = postRepository.findByTitle(post.getTitle());
        result.ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 글입니다.");
        });
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Course> findCourse() {
        return postRepository.findAllCourse();
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    public int delete(Long id) {
        return postRepository.deleteById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Optional<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }



}
