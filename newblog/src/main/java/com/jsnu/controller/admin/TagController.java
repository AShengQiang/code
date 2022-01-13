package com.jsnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.pojo.Tag;
import com.jsnu.pojo.Type;
import com.jsnu.service.TagService;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    /*查询所有标签*/
    public String tags(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Tag> tags = tagService.listTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    /*新增标签页面*/
    public String toAddNewtags(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }
    @PostMapping("/tags")
    /*添加标签controller*/
    public String addNewtags(Tag tag,Model model,RedirectAttributes redirectAttributes){
        Tag findTag = tagService.getTagByName(tag.getName());
        if (findTag!=null){
            model.addAttribute("msg","不能添加重复的标签");
            return "admin/tag-input";
        }
            tagService.saveTag(tag);
           redirectAttributes.addFlashAttribute("msg","添加成功");
           return "redirect:/admin/tags";
    }

    @GetMapping("/tags/delete/{id}")
    /*删除标签页面*/
    public String toAddNewtags(@PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/input/{id}")
    /*编辑标签页面*/
    public String toAddNewtags(@PathVariable Long id,Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "admin/tag-input";
    }
    @PostMapping("/tags/{id}")
    /*编辑标签controller*/
    public String editTags(Tag tag,Model model,@PathVariable Long id,RedirectAttributes redirectAttributes){
        Tag findTag = tagService.getTagByName(tag.getName());
        if (findTag!=null){
            model.addAttribute("msg","不能添加重复的标签");
            return "admin/tag-input";
        }
        tagService.updateTag(id,tag);
        redirectAttributes.addFlashAttribute("msg","添加成功");
        return "redirect:/admin/tags";
    }

}
