package com.example.myapp.controller;

import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/lms/editPost")
    public String editSavePost(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setDate(new Date());
        postService.update(post);
        return "redirect:/course";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> postOptional = postService.findOne(id);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            Long prevPostId = postService.findPreviousPostId(id);
            Long nextPostId = postService.findNextPostId(id);
            model.addAttribute("prevPostId", prevPostId);
            model.addAttribute("isPrevPostAvailable", prevPostId != null);
            model.addAttribute("nextPostId", nextPostId);
            model.addAttribute("isNextPostAvailable", nextPostId != null);
        } else {
            return "redirect:/errorPage";
        }
        return "postDetail";
    }

    @GetMapping("/post/update/{id}")
    public String updatePost(@PathVariable Long id, Model model) {
        Optional<Post> postOptional = postService.findOne(id);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
        } else {
            return "redirect:/errorPage";
        }
        return "update";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/course";
    }

}
