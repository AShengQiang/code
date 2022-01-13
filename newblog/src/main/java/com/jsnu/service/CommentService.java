package com.jsnu.service;

import com.jsnu.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment>  listCommentsByBlogId(Long blogId);
    void saveComment(Comment comment);
}
