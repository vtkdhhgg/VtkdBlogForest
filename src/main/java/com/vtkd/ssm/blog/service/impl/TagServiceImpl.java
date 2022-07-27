package com.vtkd.ssm.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.mapper.ArticleTagRefMapper;
import com.vtkd.ssm.blog.mapper.TagMapper;
import com.vtkd.ssm.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-25
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRefMapper acMapper;

    @Override
    public void deleteTagById(Integer tagId) {
        try {
            // 先判断 有没有文章,没有文章才能删除
            Integer articleCount = acMapper.countArticleByTagId(tagId);
            if (articleCount ==  0){
                tagMapper.deleteTagById(tagId);
            }
        } catch (Exception e) {
            log.error("标签删除失败，tagId:{}, cause:{}", tagId, e);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        try {
            tagMapper.updateTag(tag);
        } catch (Exception e) {
            log.error("标签修改失败，tag:{}, cause:{}", tag, e);
        }
    }

    @Override
    public void insertTag(Tag tag) {
        try {
            tagMapper.insertTag(tag);
        } catch (Exception e) {
            log.error("标签插入失败，tag:{}, cause:{}", tag, e);
        }
    }

    @Override
    public Tag getTagById(Integer tagId) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagById(tagId);
        } catch (Exception e) {
            log.error("根据id获取标签失败，tagId:{}, cause:{}", tagId, e);
        }

        return tag;
    }

    @Override
    public Tag getTagByName(String tagName) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagByName(tagName);
        } catch (Exception e) {
            log.error("根据name获取标签失败，tagName:{}, cause:{}", tagName, e);
        }

        return tag;
    }

    @Override
    public List<Tag> listTag() {

        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
        } catch (Exception e) {
            log.error("获取所有标签失败, cause:{}", e);
        }
        return tagList;
    }

    @Override
    public PageInfo<Tag> pageListTag(Integer pageSize, Integer pageIndex) {
        return null;
    }

    @Override
    public List<Tag> listTagWithCount() {

        List<Tag> tagList = null;

        try {

            tagList = this.listTag();
            for (Tag tag : tagList) {
                Integer articleCount = acMapper.countArticleByTagId(tag.getTagId());
                tag.setArticleCount(articleCount);
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("获取所有标签和文章数量失败, cause:{}", e);
        }

        return tagList;
    }

    @Override
    public Integer countTag() {
        return null;
    }
}
