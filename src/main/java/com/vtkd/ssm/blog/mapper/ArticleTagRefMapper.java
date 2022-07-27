package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.ArticleTagRef;
import com.vtkd.ssm.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章和标签 关联类 数据层接口映射
 *
 * @author 君上
 * @date 2022-7-26 晚上
 */
public interface ArticleTagRefMapper {

    /**
     * 根据 标签id 查询 相关联文章总数
     * @param tagId 标签id
     * @return 文章总数
     */
    Integer countArticleByTagId(Integer tagId);
    /**
     * 根据 文章id 查询 相关联标签总数
     * @param articleId 文章id
     * @return 文章总数
     */
    Integer countTagByArticleId(Integer articleId);

    /**
     * 根据 标签id 获取相关联文章的id 列表
     * @param tagId 标签id
     * @return 关联文章id
     */
    List<Integer> getArticleListByTagId(Integer tagId);

    /**
     * 根据 文章id 获取相关联标签的id 列表
     * @param articleId 文章id
     * @return 关联标签id
     */
    List<Tag> getTagListByArticleId(Integer articleId);

    /**
     * 添加 文章和标签的映射关系
     * @param articleTagRef 文章和标签
     * @return 影响行数
     */
    int insertArticleTagRef(ArticleTagRef articleTagRef);

    /**
     * 删除 文章和标签的映射关系
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);



}
