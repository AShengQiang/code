package com.jsnu.controller;

import com.jsnu.pojo.Blog;
import com.jsnu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FooterRecommendController {

    @Autowired
    BlogService blogService;
    @GetMapping("/footer/recommend")
    public String recommendBlog(Model model){

        List<Blog> blogs = blogService.listRecommendBlogLimit3();
        model.addAttribute("blogs",blogs);
        return "_fragments :: recommendList";
    }
}
