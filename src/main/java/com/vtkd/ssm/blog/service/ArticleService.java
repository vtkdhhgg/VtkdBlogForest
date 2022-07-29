package com.vtkd.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.mapper.ArticleMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 文章 服务层接口
 *
 * @author 君上
 * @date 2022-7-26
 */
public interface ArticleService {

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertArticle(Article article);

    /**
     * 获取文章浏览总量
     *
     * @return 浏览总量
     */
    Integer countArticleView();

    /**
     * 根据 id 删除 文章
     *
     * @param articleId id
     */
    void deleteArticle(Integer articleId);

    /**
     * 修改文章详细信息
     *
     * @param article 文章
     */
    void updateArticleDetail(Article article);

    /**
     * 更新 文章
     * @param article 文章
     */
    void updateArticle(Article article);

    /**
     * 获得最后更新记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();


    /**
     * 根据文章id 查询文章(相关文章)
     *
     * @param articleId 文章id
     * @return 相关文章
     */
    List<Article> listArticleByArticleId(Integer articleId,
                                         Integer limit);

    /**
     * 根据 id 获取文章
     *
     * @param articleId id
     * @return 文章
     */
    Article getArticleById(Integer articleId);

    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer id);

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
     * 获得文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    /**
     * 获取最新文章
     *
     * @param userId 用户id
     * @param limit  查询数量
     * @return 文章列表
     */
    List<Article> listRecentArticle(Integer userId,
                                    Integer limit);

    /**
     * 获得所有的文章
     *
     * @return 列表
     */
    List<Article> listAllNotWithContent();

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 分页信息
     */
    PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

    /**
     * 增加文章 浏览人数
     * @param articleId 文章id
     */
    void articleViewIncrease(Integer articleId);

    /**
     * 增加文章 的 点赞
     * @param articleId 文章id
     */
    void articleLikeIncrease(Integer articleId);
}