package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 自定义页面
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 7481497207701671860L;

    // 自定义页面 id

    private Integer pageId;

    // key
    private String pageKey;

    // 标题
    private String pageTitle;

    // 内容
    private String pageContent;

    // 创建时间
    private Date pageCreateTime;

    // 更新时间
    private Date pageUpdateTime;

    // 访问量
    private Integer pageViewCount;

    // 评论数
    private Integer pageCommentCount;

    // 状态
    private Integer pageStatus;

    public Page() {
    }

    public Page(Integer pageId, String pageKey, String pageTitle, String pageContent, Date pageCreateTime, Date pageUpdateTime, Integer pageViewCount, Integer pageCommentCount, Integer pageStatus) {
        this.pageId = pageId;
        this.pageKey = pageKey;
        this.pageTitle = pageTitle;
        this.pageContent = pageContent;
        this.pageCreateTime = pageCreateTime;
        this.pageUpdateTime = pageUpdateTime;
        this.pageViewCount = pageViewCount;
        this.pageCommentCount = pageCommentCount;
        this.pageStatus = pageStatus;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageKey() {
        return pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public Date getPageCreateTime() {
        return pageCreateTime;
    }

    public void setPageCreateTime(Date pageCreateTime) {
        this.pageCreateTime = pageCreateTime;
    }

    public Date getPageUpdateTime() {
        return pageUpdateTime;
    }

    public void setPageUpdateTime(Date pageUpdateTime) {
        this.pageUpdateTime = pageUpdateTime;
    }

    public Integer getPageViewCount() {
        return pageViewCount;
    }

    public void setPageViewCount(Integer pageViewCount) {
        this.pageViewCount = pageViewCount;
    }

    public Integer getPageCommentCount() {
        return pageCommentCount;
    }

    public void setPageCommentCount(Integer pageCommentCount) {
        this.pageCommentCount = pageCommentCount;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Page page = (Page) o;
        return getPageId().equals(page.getPageId()) && getPageKey().equals(page.getPageKey()) && getPageTitle().equals(page.getPageTitle()) && getPageContent().equals(page.getPageContent()) && getPageCreateTime().equals(page.getPageCreateTime()) && getPageUpdateTime().equals(page.getPageUpdateTime()) && getPageViewCount().equals(page.getPageViewCount()) && getPageCommentCount().equals(page.getPageCommentCount()) && getPageStatus().equals(page.getPageStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPageId(), getPageKey(), getPageTitle(), getPageContent(), getPageCreateTime(), getPageUpdateTime(), getPageViewCount(), getPageCommentCount(), getPageStatus());
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageId=" + pageId +
                ", pageKey='" + pageKey + '\'' +
                ", pageTitle='" + pageTitle + '\'' +
                ", pageContent='" + pageContent + '\'' +
                ", pageCreateTime=" + pageCreateTime +
                ", pageUpdateTime=" + pageUpdateTime +
                ", pageViewCount=" + pageViewCount +
                ", pageCommentCount=" + pageCommentCount +
                ", pageStatus=" + pageStatus +
                '}';
    }
}
