package com.example.myapp.controller;

import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController  {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("lmss/course")
    public String savePost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postService.join(post);
        return "redirect:/";
    }
}
