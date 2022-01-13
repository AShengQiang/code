package com.jsnu.dao;

import com.jsnu.pojo.Tag;
import com.jsnu.pojo.TagQuery;
import com.jsnu.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagDao {

    @Select("select * from t_tag ")
    public List<Tag> findAllTags();

    @Select("select * from t_tag where name=#{name}")
    Tag findTagByName(String name);

    @Select("select * from t_tag where id=#{id}")
    Tag findTagById(Long id);

    @Insert("insert into t_tag(name) values(#{name})")
    void saveTag(Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    void deleteTag(Long id);

    @Update("update t_tag set name=#{tag.name} where id=#{id}")
    void updateTag(Long id, Tag tag);

    @Select("select t.id tagId,t.name tagName,count(b.id) count from t_tag t,t_blog b,t_blog_tags bt " +
            "where t.id=bt.tag_id and b.id=bt.blog_id" +
            " group by t.id order by count(b.id) DESC")
    List<TagQuery> findAllTagsAndCount();
}
