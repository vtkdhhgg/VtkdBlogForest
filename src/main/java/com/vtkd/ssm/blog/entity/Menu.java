package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 菜单
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -5926919522325665495L;

    private Integer menuId; // 菜单id
    private String menuName; //名称
    private String menuUrl; // URL
    private Integer menuLevel; // 等级
    private String menuIcon; // 图标
    private Integer menuOrder; // 排序值

    public Menu() {
    }

    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuLevel, String menuIcon, Integer menuOrder) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuLevel = menuLevel;
        this.menuIcon = menuIcon;
        this.menuOrder = menuOrder;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return getMenuId().equals(menu.getMenuId()) && getMenuName().equals(menu.getMenuName()) && getMenuUrl().equals(menu.getMenuUrl()) && getMenuLevel().equals(menu.getMenuLevel()) && getMenuIcon().equals(menu.getMenuIcon()) && getMenuOrder().equals(menu.getMenuOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuId(), getMenuName(), getMenuUrl(), getMenuLevel(), getMenuIcon(), getMenuOrder());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuLevel=" + menuLevel +
                ", menuIcon='" + menuIcon + '\'' +
                ", menuOrder=" + menuOrder +
                '}';
    }
}
