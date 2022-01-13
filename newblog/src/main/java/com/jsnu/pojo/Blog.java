package com.jsnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private String description;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;

    private Date createTime;
    private Date updateTime;

    private Long typeId;
    private Long userId;

    private Type type;
    private User user;
    private String tagIds;
    private List<Tag> tags=new ArrayList<>();
    private List<Comment> comments=new ArrayList<>();

}
