package com.example.myapp;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/form")
    public String form(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "home";
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
