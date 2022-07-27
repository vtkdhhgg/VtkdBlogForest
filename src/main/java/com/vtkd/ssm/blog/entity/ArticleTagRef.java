package com.vtkd.ssm.blog.entity;

import java.io.Serializable;

/**
 * 文章-标签 关联表
 *
 * @author 君上
 * @date 2022-7-26 晚上
 */
public class ArticleTagRef implements Serializable {

    private static final long serialVersionUID = -129941688591235943L;
    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 标签id
     */
    private Integer tagId;


    public ArticleTagRef() {
    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTagRef{" +
                "articleId=" + articleId +
                ", tagId=" + tagId +
                '}';
    }
}
