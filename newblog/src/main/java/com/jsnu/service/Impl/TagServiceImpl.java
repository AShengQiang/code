package com.jsnu.service.Impl;

import com.jsnu.dao.TagDao;
import com.jsnu.dao.TypeDao;
import com.jsnu.pojo.Tag;
import com.jsnu.pojo.TagQuery;
import com.jsnu.pojo.Type;
import com.jsnu.service.TagService;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;
    @Override
    public List<Tag> listTag() {
        return tagDao.findAllTags();
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Long> tags = convertToList(ids);
        List<Tag> list=new ArrayList<>();
        for (Long tag : tags) {
            Tag tagById = tagDao.findTagById(tag);
            list.add(tagById);
        }
        return list;
    }

    @Override
    public List<TagQuery> listAllTags() {
        return tagDao.findAllTagsAndCount();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findTagByName(name);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.findTagById(id);
    }

    @Transactional
    @Override
    public void saveTag(Tag tag) {
      tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
       tagDao.deleteTag(id);
    }

    @Transactional
    @Override
    public void updateTag(Long id, Tag tag) {
        tagDao.updateTag(id,tag);
    }

    /*"1,2,3"->list集合*/
    private List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String[] split = ids.split(",");
            for (int i=0;i<split.length;i++){
                list.add(new Long(split[i]));
            }
        }
       return list;
    }
}
