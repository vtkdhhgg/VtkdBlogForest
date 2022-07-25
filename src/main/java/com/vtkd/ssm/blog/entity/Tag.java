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

    public Tag(){

    }

    public Tag(Integer tagId, String tagName, String tagDescription) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return getTagId().equals(tag.getTagId()) && getTagName().equals(tag.getTagName()) && getTagDescription().equals(tag.getTagDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTagId(), getTagName(), getTagDescription());
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagDescription='" + tagDescription + '\'' +
                '}';
    }
}