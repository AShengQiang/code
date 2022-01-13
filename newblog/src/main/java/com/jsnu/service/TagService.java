package com.jsnu.service;

import com.jsnu.pojo.Tag;
import com.jsnu.pojo.TagQuery;

import java.util.List;

public interface TagService {

    List<Tag> listTag();
    List<Tag> listTag(String ids);
    /*查询所有标签并返回其所含博客数量*/
    List<TagQuery> listAllTags();
    Tag getTagByName(String name);
    Tag getTagById(Long id);
    void saveTag(Tag tag);
    void deleteTag(Long id);
    void updateTag(Long id, Tag tag);
}
