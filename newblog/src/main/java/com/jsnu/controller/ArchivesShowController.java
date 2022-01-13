package com.jsnu.controller;

import com.jsnu.pojo.Blog;
import com.jsnu.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesShowController {

    @Autowired
    ArchivesService archivesService;

    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = archivesService.getBlogByYear();
        Integer count = archivesService.getBlogCount();
        model.addAttribute("maps",map);
        model.addAttribute("count",count);
        return "archives";
    }
}
