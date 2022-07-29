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
     * 获得文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    /**
     * 根据 criteria 查询 文章
     *
     * @param criteria 条件
     * @return 文章
     */
    List<Article> findAll(HashMap<String, Object> criteria);

    /**
     * 获取最新文章
     *
     * @param userId 用户id
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRecentArticle(@Param("userId") Integer userId,
                                    @Param("limit") Integer limit);

    /**
     * 根据分类id 获得相关文章
     *
     * @param categoryIds 分类ID集合
     * @param limit  查询数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                          @Param("limit") Integer limit);

    /**
     * 根据文章id 查询文章(相关文章)
     * @param articleId 文章id
     * @return 相关文章
     */
    List<Article> listArticleByArticleId(@Param("articleId") Integer articleId,
                                         @Param("limit") Integer limit);

    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获取下一篇文章
     * @param articleId 文章id
     * @return 文章
     */
    Article getAfterArticle(Integer articleId);

    /**
     * 获取上一篇文章
     * @param articleId 文章id
     * @return 文章
     */
    Article getPreArticle(Integer articleId);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得评论数较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 增加文章 浏览人数
     * @param articleId 文章id
     */
    int articleViewIncrease(Integer articleId);

    /**
     * 查询浏览总量
     * @return 浏览总量
     */
    Integer countArticleView();

    /**
     * 查询最后更新的文章
     * @return
     */
    Article getLastUpdateArticle();

    /**
     * 增加文章 的 点赞
     * @param articleId 文章id
     * @return 影响行数
     */

    /**
     * 点赞增加
     * @param articleId 文章id
     * @return 影响行数
     */
    int articleLikeIncrease(Integer articleId);

    /**
     * 归档 文章
     * @return
     */
    List<Article> listAllNotWithContent();
}
