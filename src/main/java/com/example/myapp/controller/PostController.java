package com.example.myapp.controller;

import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
//@Slf4j
public class PostController  {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("lms/savePost")
    public String savePost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(new Date());
        postService.join(post);
        return "redirect:/course";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> postOptional = postService.findOne(id);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
        } else {
            // 게시물이 존재하지 않을 때의 처리 (예: 오류 메시지 표시, 다른 페이지로 리디렉션)
            return "redirect:/errorPage";
        }
        return "postDetail";
    }
}
