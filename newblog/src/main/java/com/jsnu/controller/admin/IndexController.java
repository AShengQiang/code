package com.jsnu.controller.admin;

import com.jsnu.pojo.User;
import com.jsnu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    UserService userService;

    /*访问登录页面*/
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }

    /*登陆验证*/
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes){

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登陆数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try{
            subject.login(token);//执行登陆方法
            return "admin/index";
        }catch (UnknownAccountException e){
            //用户不存在
           redirectAttributes.addFlashAttribute("msg","用户名不存在");
           return  "redirect:/admin";
        }catch (IncorrectCredentialsException e){
            //密码不存在
            redirectAttributes.addFlashAttribute("msg","密码错误");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
