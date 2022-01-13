package com.jsnu.controller;

import com.jsnu.pojo.Comment;
import com.jsnu.pojo.User;
import com.jsnu.service.CommentService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Value("${comment.avatar}")
    private String avatar;


    @GetMapping("/comments/{blogId}")
    public String listComments(Model model,@PathVariable Long blogId){
        List<Comment> comments = commentService.listCommentsByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog:: commentList";
    }



    /*保存comment*/
    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session){
      //  System.out.println("saveComment方法执行了。。。。。");
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }
}
