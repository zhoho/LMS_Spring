package com.example.myapp;

import com.example.myapp.repository.JdbcPostRepository;
import com.example.myapp.repository.PostRepository;
import com.example.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
public class SpringConfig {
      private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public PostService postService() { return new PostService(postRepository());}

    @Bean
    public PostRepository postRepository() {
//        return new JpaPostRepository(em);
        return new JdbcPostRepository(dataSource);
    }
}

