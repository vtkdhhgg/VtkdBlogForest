package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 评论
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 2195018755135693934L;

    private Integer commentId; // 评论id
    private Integer commentPid; // 上级评论id
    private String commentPname; // 上级评论名称
    private Integer commentArticleId; // 文章id
    private String commentAuthorName; // 评论人名称
    private String commentAuthorUrl; // 评论人个人主页
    private String commentAuthorAvatar; // 评论人头像
    private String commentContent; // 内容
    private String commentAgent; // 浏览器信息
    private String commentIp; // ip
    private Date commentCreateTime; // 评论时间
    private Integer commentRole; // 角色 1 管理员, 0访客
    private Integer commentUserId; // 评论id 可能为空

    /**
     * 非数据库字段
     */
    private Article article;



    public Comment() {
    }

    public Comment(Integer commentId, Integer commentPid, String commentPname, Integer commentArticleId, String commentAuthorName
            , String commentAuthorUrl, String commentAuthorAvatar, String commentContent, String commentAgent
            , String commentIp, Date commentCreateTime, Integer commentRole, Integer commentUserId) {
        this.commentId = commentId;
        this.commentPid = commentPid;
        this.commentPname = commentPname;
        this.commentArticleId = commentArticleId;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorUrl = commentAuthorUrl;
        this.commentAuthorAvatar = commentAuthorAvatar;
        this.commentContent = commentContent;
        this.commentAgent = commentAgent;
        this.commentIp = commentIp;
        this.commentCreateTime = commentCreateTime;
        this.commentRole = commentRole;
        this.commentUserId = commentUserId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentPid() {
        return commentPid;
    }

    public void setCommentPid(Integer commentPid) {
        this.commentPid = commentPid;
    }

    public String getCommentPname() {
        return commentPname;
    }

    public void setCommentPname(String commentPname) {
        this.commentPname = commentPname;
    }

    public Integer getCommentArticleId() {
        return commentArticleId;
    }

    public void setCommentArticleId(Integer commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName;
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }

    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Integer getCommentRole() {
        return commentRole;
    }

    public void setCommentRole(Integer commentRole) {
        this.commentRole = commentRole;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return getCommentId().equals(comment.getCommentId()) && getCommentPid().equals(comment.getCommentPid()) && getCommentPname().equals(comment.getCommentPname()) && getCommentArticleId().equals(comment.getCommentArticleId()) && getCommentAuthorName().equals(comment.getCommentAuthorName()) && getCommentAuthorUrl().equals(comment.getCommentAuthorUrl()) && getCommentAuthorAvatar().equals(comment.getCommentAuthorAvatar()) && getCommentContent().equals(comment.getCommentContent()) && getCommentAgent().equals(comment.getCommentAgent()) && getCommentIp().equals(comment.getCommentIp()) && getCommentCreateTime().equals(comment.getCommentCreateTime()) && getCommentRole().equals(comment.getCommentRole()) && getCommentUserId().equals(comment.getCommentUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId(), getCommentPid(), getCommentPname(), getCommentArticleId(), getCommentAuthorName(), getCommentAuthorUrl(), getCommentAuthorAvatar(), getCommentContent(), getCommentAgent(), getCommentIp(), getCommentCreateTime(), getCommentRole(), getCommentUserId());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentPid=" + commentPid +
                ", commentPname='" + commentPname + '\'' +
                ", commentArticleId=" + commentArticleId +
                ", commentAuthorName='" + commentAuthorName + '\'' +
                ", commentAuthorUrl='" + commentAuthorUrl + '\'' +
                ", commentAuthorAvatar='" + commentAuthorAvatar + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentAgent='" + commentAgent + '\'' +
                ", commentIp='" + commentIp + '\'' +
                ", commentCreateTime=" + commentCreateTime +
                ", commentRole=" + commentRole +
                ", commentUserId=" + commentUserId +
                '}';
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
