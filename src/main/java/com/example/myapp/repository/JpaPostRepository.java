//package com.example.myapp.repository;
//
//import com.example.myapp.domain.Post;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
////@Primary
////@Repository
//public class JpaPostRepository implements PostRepository {
//
//    private final EntityManager em;
//
//    public JpaPostRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Post save(Post post) {
//        em.persist(post);
//        return post;
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        Post user = em.find(Post.class, id);
//        return Optional.ofNullable(user);
//    }
//
//    @Override
//    public Optional<Post> findByTitle(String title) {
//        List<Post> result = em.createQuery("select m from Post m where m.title = :title", Post.class)
//                .setParameter("title", title)
//                .getResultList();
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return em.createQuery("select m from Post m", Post.class)
//                .getResultList();
//    }
//}
