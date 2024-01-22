package com.example.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.myapp.domain.Post;
import com.example.myapp.repository.JpaPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HomeController {
    private JpaPostRepository jpaPostRepository;
    @Autowired


    @GetMapping("/")
    public String home() {
        return "dashboard";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/course")
    public String course() {
        return "course";
    }
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @PostMapping("/write")
    public String form(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "write";
    }

    @PostMapping("/savePost")
    public String savePost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        jpaPostRepository.save(post);
        return "redirect:/course";
    }

    @GetMapping("/apis/welcome")
    @ResponseBody
    public ResponseEntity<Map<String, String>> welcome() {
        log.info("GET /rest-api");

        Map<String, String> body = new HashMap<>();
        body.put("message", "Hello, World!");
        return ResponseEntity.ok(body);
    }
}
