package com.jsnu.service.Impl;

import com.jsnu.dao.BlogDao;
import com.jsnu.pojo.Blog;
import com.jsnu.service.ArchivesService;
import com.jsnu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    BlogDao blogDao;
    @Override
    public Map<String, List<Blog>> getBlogByYear() {
        Map<String,List<Blog>> map=new LinkedHashMap<>();
        List<String> blogYear = blogDao.findBlogYear();
        for (String year : blogYear) {
            List<Blog> blogs = blogDao.findBlogByYear(year);
            map.put(year,blogs);
        }
        return map;
    }

    @Override
    public Integer getBlogCount() {
        return blogDao.findBlogCount();
    }
}
