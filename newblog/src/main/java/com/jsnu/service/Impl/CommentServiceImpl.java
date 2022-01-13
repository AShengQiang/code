package com.jsnu.service.Impl;

import com.jsnu.dao.CommentDao;
import com.jsnu.pojo.Comment;
import com.jsnu.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;


    private void addParentComment(Long id,List<Comment> comments){
        for (Comment comment : comments) {
            Comment parentComment = commentDao.findParentComment(id);
            comment.setParentComment(parentComment);
        }
    }

    /*保存评论信息*/

    @Override
    public List<Comment> listCommentsByBlogId(Long blogId) {
        /*查询出最上级的评论*/
        List<Comment> comments= commentDao.findCommentById(blogId);
        /*设置评论的父评论、回复*/
        for (Comment comment : comments) {
                Long id = comment.getId();
                /*查询子评论*/
                List<Comment> replyComments = commentDao.findReplyComments(id);
                if (replyComments!=null){
                    comment.setReplyComments(replyComments);
                    addParentComment(id,replyComments);
                }else {
                    comment.setReplyComments(null);
                }
        }

        return eachComment(comments);
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setChildComment(false);
        Long parentId = comment.getParentCommentId();
        if(parentId!=-1){

            Comment parentComment = commentDao.findParentComment(parentId);
            parentComment.setChildComment(true);
            commentDao.updateComment(parentComment);
        }
        commentDao.saveComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //System.out.println("combineChildren方法开始");
            /*for (Comment tempReply : tempReplys) {
                System.out.println(tempReply.getNickname());
            }*/
           // System.out.println("combineChildren方法结束");
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
       // System.out.println(comment.getNickname());
        if (comment.isChildComment()) {
            List<Comment> replys = commentDao.findReplyComments(comment.getId());
            comment.setReplyComments(replys);
            addParentComment(comment.getId(),replys);
           // List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.isChildComment()) {
                    recursively(reply);
                }
            }
        }
    }


}
