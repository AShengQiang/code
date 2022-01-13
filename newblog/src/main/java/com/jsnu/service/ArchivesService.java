package com.jsnu.service;

import com.jsnu.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface ArchivesService {

    /*按年份返回查询博客*/
    Map<String, List<Blog>> getBlogByYear();

    /*获取blog的总数量*/
    Integer getBlogCount();
}
