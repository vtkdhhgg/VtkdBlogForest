package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.mapper.TagMapper;
import com.vtkd.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-25
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void insertTag(Tag tag) {
        tagMapper.insertTag(tag);
    }

    @Override
    public void deleteTagById(Integer tagId) {
        tagMapper.deleteTagById(tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateTag(tag);
    }

    @Override
    public Tag getTagById(Integer tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagMapper.getTagByName(tagName);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public Integer countTag() {
        return tagMapper.countTag();
    }
}
