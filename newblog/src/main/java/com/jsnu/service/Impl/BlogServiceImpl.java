package com.jsnu.service.Impl;

import com.jsnu.dao.BlogDao;
import com.jsnu.dao.LinkDao;
import com.jsnu.pojo.Blog;
import com.jsnu.pojo.BlogQuery;
import com.jsnu.pojo.BlogTagQuery;
import com.jsnu.pojo.Link;
import com.jsnu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private LinkDao linkDao;
    @Override
    public List<Blog> listBlog() {
        return blogDao.findAllBlog();
    }

    @Override
    public List<Blog> listBlog(BlogQuery blog) {
        return blogDao.findBlogByBlogQuery(blog);
    }

    @Transactional
    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blogDao.saveBlog(blog);
        String tagIds = blog.getTagIds();
        Long blogId = blog.getId();
        System.out.println(blogId);
        if(!StringUtils.isEmptyOrWhitespace(tagIds)){
            saveLink(blogId,tagIds);
        }
    }
    private void saveLink(Long blogId, String tagIds) {
        List<String> stringList = Arrays.asList(tagIds.split(","));
        // 批量插入到中间表里
        List<Link> linkList = new ArrayList<>();
        for (int j = 0; j < stringList.size(); j++) {
            Link link = new Link();
            link.setBlogId(blogId);
            link.setTagId(Long.parseLong(stringList.get(j)));
            linkList.add(link);
        }
        linkDao.saveLink(linkList);
    }

    @Transactional
    @Override
    public void delBlog(Long id) {
        blogDao.deleteBlog(id);
        linkDao.deleteLinkById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.findBlogById(id);
    }

    @Transactional
    @Override
    public void updateBlog(Long id, Blog blog) {
        blog.setUpdateTime(new Date());
        String tagIds = blogDao.findBlogById(id).getTagIds();
        /*查看与未修改之前是否相同*/
        if(!tagIds.equalsIgnoreCase(blog.getTagIds())){
        linkDao.deleteLinkById(id);
        saveLink(id,blog.getTagIds());
    }
        blogDao.updateBlog(id,blog);
}

    @Override
    public List<Blog> listRecommendBlog() {
        return blogDao.listRecommendBlog();
    }


    @Override
    public List<Blog> listRecommendBlogLimit3() {
        return blogDao.findRecommendBlogLimit3();
    }

    /*通过typeid 查询博客*/
    @Override
    public List<Blog> listBlogByTypeId(Long typeId) {
        return blogDao.findBlogByTypeId(typeId);
    }
    /*通过tagid 查询博客*/
    @Override
    public List<BlogTagQuery> listBlogByTagId(Long tagId) {

        return blogDao.findBlogByTagId(tagId);
    }
}
