package com.jsnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.pojo.*;
import com.jsnu.service.BlogService;
import com.jsnu.service.TagService;
import com.jsnu.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogShowController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.listBlog();
        PageInfo<Blog>pageInfo=new PageInfo<>(blogs);
        /*右侧分类和标签*/
        List<TypeQuery> types = typeService.listAllType();
        List<TagQuery> tags = tagService.listAllTags();
        /*推荐博客*/
        List<Blog> recommendBlogs = blogService.listRecommendBlog();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommends",recommendBlogs);
        return "index";
    }
    /*访问具体博客*/
    @GetMapping("/blog/{id}")
    public String blog(Model model,@PathVariable Long id){
        Blog blog = blogService.getBlogById(id);
        int views = blog.getViews()+1;
        blog.setViews(views);
        blogService.updateBlog(id,blog);
        List<Tag> tags = tagService.listTag(blog.getTagIds());
        blog.setTags(tags);
        model.addAttribute("blog",blog);
        return "blog";
    }

}
