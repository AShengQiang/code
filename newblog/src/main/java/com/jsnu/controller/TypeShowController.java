package com.jsnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.pojo.Blog;
import com.jsnu.pojo.BlogQuery;
import com.jsnu.pojo.TypeQuery;
import com.jsnu.service.BlogService;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(Model model,@RequestParam(defaultValue = "1" ,value="pageNum") Integer pageNum,@PathVariable Long id){
        PageHelper.startPage(pageNum,4);
        List<TypeQuery> types = typeService.listAllType();
        BlogQuery blogQuery=new BlogQuery();
        if(id==-1){
            id = types.get(0).getTypeId();

        }
        List<Blog> blogs = blogService.listBlogByTypeId(id);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("activeId",id);
        return "types";
    }

}
