package com.example.myapp.repository;

import com.example.myapp.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Optional<Post> findById(Long postId) {
        Post user = em.find(Post.class, postId);
        return Optional.ofNullable(user);
    }
    @Override
    public Optional<Post> findByTitle(String title) {
         List<Post> result = em.createQuery("select m from Post m where m.title = :title", Post.class)
                .setParameter("title", title)
                .getResultList();
         return result.stream().findAny();
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }
}
