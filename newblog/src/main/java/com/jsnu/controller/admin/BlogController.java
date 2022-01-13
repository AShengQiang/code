package com.jsnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.dao.BlogDao;
import com.jsnu.pojo.*;
import com.jsnu.service.BlogService;
import com.jsnu.service.TagService;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService  tagService;
    @Autowired
    BlogDao blogDao;

   /*展示博客列表*/
    @GetMapping("/blogs")
    public String blogs(Model model,@RequestParam(defaultValue = "1",value = "page") Integer page){

        PageHelper.startPage(page,4);
        List<Blog> blogs = blogService.listBlog();
        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogShow";

    }

    @PostMapping("/blogs/search")
    public String searchBlog( @RequestParam(defaultValue = "1",value = "page") Integer page, BlogQuery blog,Model model){
        PageHelper.startPage(page,2);

        List<Blog> blogs = blogService.listBlog(blog);

        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogShow ::blogList";
    }


    @GetMapping("/blogs/input")
    public String toAddNewblogPage(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/input")
    public String addNewblogPage(Blog blog, HttpSession session,RedirectAttributes redirectAttributes){

        Type type = typeService.getTypeById(blog.getTypeId());
        blog.setType(type);
        List<Tag> tags = tagService.listTag(blog.getTagIds());
        blog.setTags(tags);
        blog.setUser((User) session.getAttribute("user"));
        blogService.saveBlog(blog);
        redirectAttributes.addFlashAttribute("msg","添加成功！");
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/del/{id}")
    public String delete(RedirectAttributes redirectAttributes, @PathVariable Long id){
        blogService.delBlog(id);
       redirectAttributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/blogs";
    }

    /*访问编辑页面controller*/
    @GetMapping("/blogs/edit/{id}")
    public String toEditblogPage(Model model,@PathVariable Long id){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        List<Tag> tags = tagService.listTag();
        model.addAttribute("tags",tags);
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }
    /*编辑博客controller*/
    @PostMapping("/blogs/edit/{id}")
    public String editBlog(Blog blog, HttpSession session,RedirectAttributes redirectAttributes,@PathVariable Long id){
        Type type = typeService.getTypeById(blog.getTypeId());
        blog.setType(type);
        List<Tag> tags = tagService.listTag(blog.getTagIds());
        blog.setTags(tags);
        blog.setUser((User) session.getAttribute("user"));
        blogService.updateBlog(id,blog);
        redirectAttributes.addFlashAttribute("msg","修改成功！");
        return "redirect:/admin/blogs";
    }

}
