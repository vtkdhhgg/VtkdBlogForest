package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.Article;

import java.util.List;

/**
 * 文章 数据库接口
 *
 * @author 君上
 * @date 2022-7-26
 * */
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article 文章
     * @return
     */
    int insertArticle(Article article);

    /**
     * 根据 id 删除 文章
     * @param articleId id
     * @return
     */
    int deleteArticle(Integer articleId);

    /**
     * 更新 文章
     * @param article 文章
     * @return
     */
    int updateArticle(Article article);

    /**
     * 根据 id 获取文章
     * @param articleId id
     * @return
     */
    Article getArticleById(Integer articleId);

    /**
     * 文章列表
     * @param status 状态
     * @return
     */
    List<Article> listArticle(Integer status);

    /**
     * 获得文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

}
