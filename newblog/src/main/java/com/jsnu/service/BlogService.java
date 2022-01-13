package com.jsnu.service;

import com.jsnu.pojo.Blog;
import com.jsnu.pojo.BlogQuery;
import com.jsnu.pojo.BlogTagQuery;

import java.util.List;

public interface BlogService {

    List<Blog> listBlog();
    //对博客进行模糊查询
    List<Blog> listBlog(BlogQuery blog);
    void saveBlog(Blog blog);
    void delBlog(Long id);
    /*通过id来获取具体博客*/
    Blog getBlogById(Long id);

    void updateBlog(Long id,Blog blog);
    /*查询推荐博客*/
    List<Blog> listRecommendBlog();
    /*查询推荐博客 限制数量3*/
    List<Blog> listRecommendBlogLimit3();
    /*根据typeid 查询博客*/
    List<Blog> listBlogByTypeId(Long typeId);
    /*根据tagid 查询博客*/
   List<BlogTagQuery> listBlogByTagId(Long tagId);
}
