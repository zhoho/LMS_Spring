package com.example.myapp.controller;

import java.util.List;

import com.example.myapp.domain.Course;
import com.example.myapp.domain.Post;
import com.example.myapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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

    @GetMapping("/course/{id}")
    public String list(Model model, @PathVariable String id) {
        List<Post> posts = postService.findByCourseId(Long.valueOf(id));
        List<Course> courses = postService.findCourse();
        model.addAttribute("posts", posts);
        model.addAttribute("courses", courses);
        model.addAttribute("currentCourseId", id);
        return "course";
    }
    @GetMapping("/write/{id}")
    public String write(Model model, @PathVariable String id) {
        List<Course> courses = postService.findCourse();
        model.addAttribute("courses", courses);
        model.addAttribute("currentCourseId", id);
        return "write";
    }
}
