package com.vtkd.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Tag;

import java.util.List;

/**
 * 标签 服务层接口
 *
 * @author 君上
 * @date 2022-7-25
 */
public interface TagService {


    /**
     * 增加 标签
     * @param tag 标签
     * @return
     */
    void insertTag(Tag tag);

    /**
     * 根据 id 删除标签
     * @param tagId 标签id
     * @return
     */
    void deleteTagById(Integer tagId);

    /**
     * 更新 标签
     * @param tag 标签
     * @return
     */
    void updateTag(Tag tag);

    /**
     * 根据 id 查询标签
     * @param tagId
     * @return 标签
     */
    Tag getTagById(Integer tagId);

    /**
     * 根据 id 查询标签
     * @param tagName 标签名称
     * @return 标签
     */
    Tag getTagByName(String tagName);


    /**
     * 查询所有标签
     * @return
     */
    List<Tag> listTag();

    /**
     * 查询所有标签 和 文章数量
     * @return
     */
    List<Tag> listTagWithCount();

    /**
     * 查询标签总数
     * @return
     */
    Integer countTag();

}
