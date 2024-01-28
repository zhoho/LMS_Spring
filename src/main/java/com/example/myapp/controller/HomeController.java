package com.example.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@Slf4j
public class HomeController {

    private final PostService postService;
    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Course> courses = postService.findCourse();
        model.addAttribute("courses", courses);
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/course")
    public String list(Model model) {
        List<Post> posts = postService.findPosts();
        List<Course> courses = postService.findCourse();
        model.addAttribute("posts", posts);
        model.addAttribute("courses", courses);
        return "course";
    }
    @GetMapping("/write")
    public String write() {
        return "write";
    }

//    @PostMapping("/write")
//    public String form(@RequestParam String name, Model model) {
//        model.addAttribute("name", name);
//        return "write";
//    }

//    @GetMapping("/apis/welcome")
//    @ResponseBody
//    public ResponseEntity<Map<String, String>> welcome() {
//        log.info("GET /rest-api");
//
//        Map<String, String> body = new HashMap<>();
//        body.put("message", "Hello, World!");
//        return ResponseEntity.ok(body);
//    }
}
