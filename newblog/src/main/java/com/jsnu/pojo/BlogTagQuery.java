package com.jsnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogTagQuery {

   private Long   id;//用来封装blog的id
   private String title;
   private String description;
   private User  user;
   private Date  updateTime;
   private Integer views;
   private String tagname;
   private String firstpicture;
}
