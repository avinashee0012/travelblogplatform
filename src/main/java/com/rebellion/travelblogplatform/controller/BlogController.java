package com.rebellion.travelblogplatform.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @GetMapping("/test")
    public String testBlogController() {
        return "BlogController: OK";
    }
}
