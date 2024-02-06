package com.example.myapp.controller;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("savePost/{id}")
    public String savePost(@RequestParam String title, @RequestParam String content, @RequestParam("file") MultipartFile file,@PathVariable String id, Model model) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(timestamp);
        model.addAttribute("currentCourseId",id);
        postService.join(post, file, Long.valueOf(id));
        return "redirect:/course/" + id;
    }

    @PostMapping("editPost/{courseId}")
    public String editSavePost(@RequestParam Long id, @RequestParam String title, @RequestParam String content, @PathVariable String courseId, Model model) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setDate(timestamp);
        model.addAttribute("currentCourseId",courseId);
        postService.update(post);
        return "redirect:/course/" + courseId;
    }

    @GetMapping("course/{courseId}/post/{postId}")
    public String viewPost(@PathVariable Long courseId, @PathVariable Long postId, Model model) {
        Optional<Post> postOptional = postService.findOne(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            model.addAttribute("courseId", courseId); // 모델에 courseId 추가
            model.addAttribute("prevPostId", postService.findPreviousPostId(postId));
            model.addAttribute("nextPostId", postService.findNextPostId(postId));
        } else {
            return "redirect:/errorPage";
        }
        return "postDetail";
    }

    @GetMapping("course/{courseId}/post/update/{postId}")
    public String updatePost(@PathVariable Long courseId, @PathVariable Long postId, Model model) {
        Optional<Post> postOptional = postService.findOne(postId);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            model.addAttribute("courseId", courseId); // courseId를 모델에 추가
        } else {
            return "redirect:/errorPage";
        }
        return "update";
    }


    @PostMapping("/course/{courseId}/post/delete/{postId}")
    public String deletePost(@PathVariable Long courseId, @PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/course/" + courseId;
    }

    @GetMapping("/search-posts")
    public String searchPosts(@RequestParam(required = false) String title, Model model) {
        List<Course> courses = postService.findCourse();
        model.addAttribute("courses", courses);

        if (title != null && !title.isEmpty()) {
            List<Post> posts = postService.findByTitleContaining(title);
            model.addAttribute("posts", posts);
        } else {
            model.addAttribute("posts", Collections.emptyList());
        }
        return "course";
    }
}
