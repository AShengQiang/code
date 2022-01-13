package com.jsnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.pojo.BlogTagQuery;
import com.jsnu.pojo.TagQuery;
import com.jsnu.service.BlogService;
import com.jsnu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.NodeList;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model, @PathVariable Long id){
        List<TagQuery> tags = tagService.listAllTags();
        PageHelper.startPage(pageNum,4);
        if(id==-1){
            id= tags.get(0).getTagId();
        }
        List<BlogTagQuery> blogs = blogService.listBlogByTagId(id);
        PageInfo<BlogTagQuery> pageInfo=new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeId",id);
        return "tags";
    }
}
