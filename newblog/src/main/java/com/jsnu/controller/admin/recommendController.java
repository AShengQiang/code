package com.jsnu.controller.admin;

import com.jsnu.pojo.Blog;
import com.jsnu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class recommendController {

    @Autowired
    BlogService blogService;
    @GetMapping("/admin/footer/recommend")
    public String recommendBlog(Model model){

        List<Blog> blogs = blogService.listRecommendBlogLimit3();
        model.addAttribute("blogs",blogs);
        return "admin/_fragments :: recommendList";
    }
}
