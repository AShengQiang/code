package com.jsnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private Long id;
    private String nickname;
    private String avatar;
    private String content;
    private String email;
    private Date createTime;
    private Boolean adminComment;
    private boolean childComment;

    private Long blogId;
    private Long parentCommentId;
    private Comment parentComment;
    private List<Comment> replyComments=new ArrayList<>();



}
