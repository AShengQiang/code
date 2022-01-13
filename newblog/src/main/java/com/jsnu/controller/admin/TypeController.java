package com.jsnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsnu.pojo.Type;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    /*查询所有分类*/
    public String types(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("查需执行了！");
        PageHelper.startPage(pageNum,3);
        List<Type> types = typeService.listType();
        PageInfo<Type> pageInfo=new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    /*分类添加页面controller*/
    public String toAddTypePage(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }


    @PostMapping("/types")
    /*分类添加页面controller*/
    public String saveType(Type type, RedirectAttributes redirectAttributes,Model model){
        Type findType = typeService.getTypeByName(type.getName());
        if (findType!=null){
            model.addAttribute("msg","不能添加重复的类");
            return "admin/type-input";
        }
        System.out.println("添加执行了！");
        typeService.saveType(type);
        redirectAttributes.addFlashAttribute("msg","新增成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/delete/{id}")
    /*分类删除页面controller*/
    public String deleteType(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("msg","删除分类成功");
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    /*分类编辑页面controller*/
    public String EditType(Type type, RedirectAttributes redirectAttributes,Model model,@PathVariable Long id){
        Type t = typeService.getTypeByName(type.getName());
        if (t!=null){
            model.addAttribute("msg","不能添加重复的类");
            return "admin/type-input";
        }
        typeService.updateType(id,type);
        redirectAttributes.addFlashAttribute("msg","新增成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/input/{id}")
    /*分类编辑页面controller*/
    public String toEditType(@PathVariable Long id,Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/type-input";
    }
}
