package com.example.myapp;

import com.example.SpringMvcTest;
import com.example.myapp.domain.Post;
import com.example.myapp.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Commit
@SpringMvcTest
public class EntityTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void test() {
        Post savedName = postRepository.save(new Post("thisisme", "wowww"));

        assertThat(postRepository.findById(savedName.getId())).isPresent();
    }
    @Test
    void findByIdTest() {
        Post post = new Post("FindByIdTest", "Content");
        Post savedPost = postRepository.save(post);
        Optional<Post> foundPost = postRepository.findById(savedPost.getId());

        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getTitle()).isEqualTo(post.getTitle());
        assertThat(foundPost.get().getContent()).isEqualTo(post.getContent());
    }

    @Test
    void findByTitleTest() {
        Post post = new Post("FindByTitleTest", "Content");
        postRepository.save(post);
        Optional<Post> foundPost = postRepository.findByTitle("FindByTitleTest");

        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    void findAllTest() {
        postRepository.save(new Post("Title1", "Content1"));
        postRepository.save(new Post("Title2", "Content2"));

        List<Post> posts = postRepository.findAll();
        assertThat(posts).isNotEmpty();
        assertThat(posts.size()).isGreaterThanOrEqualTo(2);
    }
}