package com.vtkd.ssm.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.mapper.ArticleMapper;
import com.vtkd.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.MailDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 文章 服务层接口实现
 *
 * @date 2022-7-26
 * */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public void insertArticle(Article article) {
        articleMapper.insertArticle(article);
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
        articleMapper.deleteArticle(articleId);
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {

    }

    @Override
    public void updateArticleDetail(Article article) {

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
        return articleMapper.getArticleById(articleId);
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

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        return null;
    }
}
