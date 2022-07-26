package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 标签
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = 3790492331085691694L;

    // 标签id
    private Integer tagId;
    // 标签名称
    private String tagName;
    // 描述
    private String tagDescription;

    /**
     * 文章数量(不是数据库字段)
     */
    private Integer articleCount;

    public Tag(){

    }

    public Tag(Integer tagId){
        this.tagId = tagId;
    }

    public Tag(Integer tagId, String tagName, String tagDescription, Integer articleCount) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
        this.articleCount = articleCount;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagDescription='" + tagDescription + '\'' +
                ", articleCount=" + articleCount +
                '}';
    }
}
