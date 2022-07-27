package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 文章 bean
 */
public class Article implements Serializable {

    private static final long serialVersionUID = -2267400931032924649L;
    private Integer articleId;
    private Integer articleUserId;
    private String articleTitle; // 标题
    private String articleContent; // 内容
    private Integer articleViewCount; // 访问量
    private Integer articleCommentCount; // 评论数
    private Integer articleLikeCount; // 点赞数
    private Integer articleIsComment; // 是否允许评论
    private Integer articleStatus; // 状态
    private Integer articleOrder; // 排序值
    private Date articleUpdateTime; // 更新时间
    private Date articleCreateTime; // 创建时间
    private String articleSummary; // 摘要
    private String articleThumbnail; // 缩略图

    /**
     * 用户信息 不是数据库字段
     */
    private User user;

    /**
     * 标签信息 不是数据库字段
     */
    private List<Tag> tagList;

    /**
     * 分类信息 不是数据库字段
     */
    private List<Category> categoryList;

    public Article() {
    }

    public Article(Integer articleId, Integer articleUserId, String articleTitle, String articleContent, Integer articleViewCount, Integer articleCommentCount, Integer articleLikeCount, Integer articleIsComment, Integer articleStatus, Integer articleOrder, Date articleUpdateTime, Date articleCreateTime, String articleSummary, String articleThumbnail, User user, List<Tag> tagList, List<Category> categoryList) {
        this.articleId = articleId;
        this.articleUserId = articleUserId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleViewCount = articleViewCount;
        this.articleCommentCount = articleCommentCount;
        this.articleLikeCount = articleLikeCount;
        this.articleIsComment = articleIsComment;
        this.articleStatus = articleStatus;
        this.articleOrder = articleOrder;
        this.articleUpdateTime = articleUpdateTime;
        this.articleCreateTime = articleCreateTime;
        this.articleSummary = articleSummary;
        this.articleThumbnail = articleThumbnail;
        this.user = user;
        this.tagList = tagList;
        this.categoryList = categoryList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }


    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Integer getArticleIsComment() {
        return articleIsComment;
    }

    public void setArticleIsComment(Integer articleIsComment) {
        this.articleIsComment = articleIsComment;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        return getArticleId().equals(article.getArticleId()) && getArticleUserId().equals(article.getArticleUserId()) && getArticleTitle().equals(article.getArticleTitle()) && getArticleContent().equals(article.getArticleContent()) && getArticleViewCount().equals(article.getArticleViewCount()) && getArticleCommentCount().equals(article.getArticleCommentCount()) && getArticleLikeCount().equals(article.getArticleLikeCount()) && getArticleIsComment().equals(article.getArticleIsComment()) && getArticleStatus().equals(article.getArticleStatus()) && getArticleOrder().equals(article.getArticleOrder()) && getArticleUpdateTime().equals(article.getArticleUpdateTime()) && getArticleCreateTime().equals(article.getArticleCreateTime()) && getArticleSummary().equals(article.getArticleSummary()) && getArticleThumbnail().equals(article.getArticleThumbnail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArticleId(), getArticleUserId(), getArticleTitle(), getArticleContent(), getArticleViewCount(), getArticleCommentCount(), getArticleLikeCount(), getArticleIsComment(), getArticleStatus(), getArticleOrder(), getArticleUpdateTime(), getArticleCreateTime(), getArticleSummary(), getArticleThumbnail());
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleUserId=" + articleUserId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentCount=" + articleCommentCount +
                ", articleLikeCount=" + articleLikeCount +
                ", articleIsComment=" + articleIsComment +
                ", articleStatus=" + articleStatus +
                ", articleOrder=" + articleOrder +
                ", articleUpdateTime=" + articleUpdateTime +
                ", articleCreateTime=" + articleCreateTime +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleThumbnail='" + articleThumbnail + '\'' +
                ", user=" + user +
                ", tagList=" + tagList +
                ", categoryList=" + categoryList +
                '}';
    }
}
