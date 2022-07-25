package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分类
 */
public class Category implements Serializable {


    private static final long serialVersionUID = 7326310912472164715L;
    private Integer categoryId;
    private Integer categoryPid; // 分类父ID
    private String categoryName; // 分类名称
    private String categoryDescription; //描述
    private Integer categoryOrder; // 排序值
    private String categoryIcon; // 图标

    public Category(Integer categoryId, Integer categoryPid, String categoryName, String categoryDescription, Integer categoryOrder, String categoryIcon) {
        this.categoryId = categoryId;
        this.categoryPid = categoryPid;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryOrder = categoryOrder;
        this.categoryIcon = categoryIcon;
    }

    public Category() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(Integer categoryPid) {
        this.categoryPid = categoryPid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return getCategoryId().equals(category.getCategoryId()) && getCategoryPid().equals(category.getCategoryPid()) && getCategoryName().equals(category.getCategoryName()) && getCategoryDescription().equals(category.getCategoryDescription()) && getCategoryOrder().equals(category.getCategoryOrder()) && getCategoryIcon().equals(category.getCategoryIcon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getCategoryPid(), getCategoryName(), getCategoryDescription(), getCategoryOrder(), getCategoryIcon());
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryPid=" + categoryPid +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryOrder=" + categoryOrder +
                ", categoryIcon='" + categoryIcon + '\'' +
                '}';
    }
}
