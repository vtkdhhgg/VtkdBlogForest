package com.vtkd.ssm.blog.entity;

import java.io.Serializable;

/**
 * 文章-分类 关联表
 *
 * @author 君上
 * @date 2022-7-26 下午
 */
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = 2183718759086100919L;
    // 文章id
    private Integer articleId;

    // 分类id
    private Integer categoryId;

    public ArticleCategoryRef() {
    }

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ArticleCategoryRef{" +
                "articleId=" + articleId +
                ", categoryId=" + categoryId +
                '}';
    }
}
