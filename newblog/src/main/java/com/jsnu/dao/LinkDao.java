package com.jsnu.dao;

import com.jsnu.pojo.Link;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LinkDao {

    @Insert({"<script>insert into t_blog_tags(tag_id,blog_id) values" +
            "<foreach collection= \"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "(#{item.tagId},#{item.blogId})" +
            "</foreach></script>"})
    void saveLink(List<Link> list);

    @Delete("delete from t_blog_tags where blog_id=#{id}")
    void deleteLinkById(Long id);
}
