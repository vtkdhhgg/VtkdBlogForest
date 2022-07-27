package com.vtkd.ssm.blog.service.impl;

import cn.hutool.log.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.*;
import com.vtkd.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.vtkd.ssm.blog.mapper.ArticleMapper;
import com.vtkd.ssm.blog.mapper.ArticleTagRefMapper;
import com.vtkd.ssm.blog.mapper.UserMapper;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MailDateFormat;
import java.awt.event.FocusEvent;
import java.util.*;

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
    public Integer countArticleComment() {
        return null;
    }

    @Override
    public Integer countArticleView() {
        return null;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return null;
    }

    @Override
    public void deleteArticle(Integer articleId) {
        try {
            articleMapper.deleteArticle(articleId);

            acMapper.deleteByArticleId(articleId);

            atMapper.deleteByArticleId(articleId);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除文章错误, articleId:{}, cause:{}", articleId, e);
        }
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {

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
    public void updateCommentCount(Integer articleId) {

    }

    @Override
    public Article getLastUpdateArticle() {
        return null;
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return null;
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
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return null;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        return null;
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return null;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return null;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return null;
    }

    @Override
    public Article getPreArticle(Integer id) {
        return null;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return null;
    }


    @Override
    public Integer countArticle(Integer status) {
        return articleMapper.countArticle(status);
    }

    @Override
    public List<Article> listRecentArticle(Integer userId, Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return null;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return null;
    }


}
