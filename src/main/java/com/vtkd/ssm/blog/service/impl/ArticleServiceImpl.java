package com.vtkd.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.*;
import com.vtkd.ssm.blog.mapper.*;
import com.vtkd.ssm.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文章 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-26
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper acMapper;

    @Autowired
    private ArticleTagRefMapper atMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageInfo<Article> pageInfo = null;
        try {
            // 分页查询
            PageHelper.startPage(pageIndex, pageSize);
            // 根据criteria 条件查询
            List<Article> articles = articleMapper.findAll(criteria);
            for (Article article : articles) {
                // 封装CategoryList
                List<Category> categoryList = acMapper.getCategoryListByArticleId(article.getArticleId());
                // 分类为空,对category初始化
                if (categoryList == null || categoryList.size() == 0) {
                    categoryList = new ArrayList<>();
                    categoryList.add(Category.Default());
                }
                // 添加文章的分类信息
                article.setCategoryList(categoryList);
                // 添加文章的用户信息
                article.setUser(userMapper.getUserById(article.getArticleUserId()));
            }
            return pageInfo = new PageInfo<>(articles);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章分页数据失败, pageIndex:{}, pageSize:{}, criteria:{}, cause:{}", pageIndex, pageSize, criteria, e);
        }
        return pageInfo;
    }

    @Override
    public void articleViewIncrease(Integer articleId) {
        try {
            articleMapper.articleViewIncrease(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新文章浏览数量失败, articleId:{} cause:{}", articleId, e);
        }
    }

    @Override
    public void articleLikeIncrease(Integer articleId) {
        try {
            articleMapper.articleLikeIncrease(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新文章点赞数量失败, articleId:{} cause:{}", articleId, e);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        try {
            articleMapper.insertArticle(article);
            // 添加 文章-分类列表
            Integer articleId = article.getArticleId();
            List<Category> categoryList = article.getCategoryList();
            if (!categoryList.isEmpty()){
                for (Category category : categoryList) {
                    acMapper.insertArticleCategoryRef(new ArticleCategoryRef(articleId, category.getCategoryId()));
                }
            }

            // 添加 文章-标签列表
            List<Tag> tagList = article.getTagList();
            if (!tagList.isEmpty()) {
                for (Tag tag : tagList) {
                    atMapper.insertArticleTagRef(new ArticleTagRef(articleId, tag.getTagId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加文章失败,  article:{}, cause:{}", article, e);
        }
    }


    @Override
    public Integer countArticleView() {
       Integer countView = null;

        try {
            countView = articleMapper.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询浏览总量失败, cause:{}", e);
        }

        return countView;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer articleId) {
        try {
            // 删除文章的 同时也要删除 分类, 标签, 评论
            articleMapper.deleteArticle(articleId);

            acMapper.deleteByArticleId(articleId);

            atMapper.deleteByArticleId(articleId);

            commentMapper.deleteCommentByArticleId(articleId);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除文章错误, articleId:{}, cause:{}", articleId, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        try {
            article.setArticleUpdateTime(new Date());
            articleMapper.updateArticle(article);

            Integer articleId = article.getArticleId();

            //删除分类和文章关联
            List<Category> categoryList = article.getCategoryList();
            if (categoryList != null){
                acMapper.deleteByArticleId(articleId);
                for (Category category : categoryList) {
                    ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(articleId, category.getCategoryId());
                    acMapper.insertArticleCategoryRef(articleCategoryRef);
                }
            }
            // 添加分类和文章关联
            List<Tag> tagList = article.getTagList();
            if (tagList != null){
                atMapper.deleteByArticleId(articleId);
                for (Tag tag : tagList) {
                    ArticleTagRef articleTagRef = new ArticleTagRef(articleId, tag.getTagId());
                    atMapper.insertArticleTagRef(articleTagRef);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改文章详细信息失败, article:{}, cause:{}", article, e);
        }

    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public Article getLastUpdateArticle() {
        Article article = null;
        try {
            article = articleMapper.getLastUpdateArticle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询最后更新的文章失败, cause:{}", e);
        }
        return article;
    }

    /**
     * @param articleId 文章id
     * @param limit
     * @return
     */
    @Override
    public List<Article> listArticleByArticleId(Integer articleId, Integer limit) {
        List<Article> articles = null;

        try {
            articles = articleMapper.listArticleByArticleId(articleId, limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取相关文章失败, articleId:{}, limit:{}, cause:{}", articleId, limit, e);
        }

        return articles;
    }

    @Override
    public Article getArticleById(Integer articleId) {
        Article article = null;
        try {
            article = articleMapper.getArticleById(articleId);
            // 查询 分类列表
            List<Category> categoryList = acMapper.getCategoryListByArticleId(articleId);
            // 查询 标签列表
            List<Tag> tagList = atMapper.getTagListByArticleId(articleId);
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章详细信息失败, articleId:{}, cause:{}", articleId, e);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articles = null;
        try {
            articles = articleMapper.listArticleByViewCount(limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询猜你喜欢文章失败, limit:{}, cause:{}", limit, e);
        }

        return articles;
    }

    @Override
    public Article getAfterArticle(Integer articleId) {
        Article article = null;
        try {
            article = articleMapper.getAfterArticle(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询下一篇文章失败, articleId:{}, cause:{}", articleId, e);
        }

        return article;
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        Article article = null;
        try {
            article = articleMapper.getPreArticle(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询下一篇文章失败, articleId:{}, cause:{}", articleId, e);
        }

        return article;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> articles = null;
        try {
            articles = articleMapper.listRandomArticle(limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询随机文章失败, limit:{}, cause:{}", limit, e);
        }

        return articles;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        List<Article> articles = null;
        try {
            articles = articleMapper.listArticleByCommentCount(limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询热评文章失败, limit:{}, cause:{}", limit, e);
        }
        return articles;
    }


    @Override
    public Integer countArticle(Integer status) {
        return articleMapper.countArticle(status);
    }

    @Override
    public List<Article> listRecentArticle(Integer userId, Integer limit) {
        List<Article> articles = null;
        try {
            articles = articleMapper.listRecentArticle(userId, limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询最新文章失败, userId:{}, limit:{}, cause:{}", userId, limit, e);
        }

        return articles;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        List<Article> articles = null;
        try {
            articles = articleMapper.listAllNotWithContent();
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询归档文章失败, cause:{}", e);
        }

        return articles;
    }


}
