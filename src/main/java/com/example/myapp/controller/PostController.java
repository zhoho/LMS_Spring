package com.example.myapp.controller;

import com.example.myapp.domain.Post;
import com.example.myapp.domain.PostFile;
import com.example.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
//@Slf4j
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("lms/savePost")
    public String savePost(@RequestParam String title, @RequestParam String content, @RequestParam("file") MultipartFile file) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(timestamp);

//        PostFile postFile = new PostFile();
//        if (!file.isEmpty()) {
//            String fileName = file.getOriginalFilename();
//            String filePath = "lms/uploads/" + fileName;
//            long fileSize = file.getSize();
//
//            postFile.setFileName(fileName);
//            postFile.setFilePath(filePath);
//            postFile.setFileSize(fileSize);
//            post.setFile(postFile);
//        }

        postService.join(post, file);
        return "redirect:/course/1";
    }



    @PostMapping("/lms/editPost")
    public String editSavePost(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setDate(timestamp);
        postService.update(post);
        return "redirect:/course/1";
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
        return "redirect:/course/1";
    }

    @GetMapping("lms/search-posts")
    public String searchPosts(@RequestParam(required = false) String title, Model model) {
        if (title != null && !title.isEmpty()) {
            List<Post> posts = postService.findByTitleContaining(title);
            model.addAttribute("posts", posts);
        } else {
            model.addAttribute("posts", Collections.emptyList());
        }
        return "course/1";
    }
}
