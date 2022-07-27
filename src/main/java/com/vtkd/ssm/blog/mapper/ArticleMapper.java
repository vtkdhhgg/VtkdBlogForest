package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 文章 数据库接口
 *
 * @author 君上
 * @date 2022-7-26
 * */
public interface ArticleMapper {

    /**
     * 分页查询
     *
     * @param status    状态
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);

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

    /**
     * 根据 criteria 查询 文章
     * @param criteria
     * @return
     */
    List<Article> findAll(HashMap<String, Object> criteria);

}
