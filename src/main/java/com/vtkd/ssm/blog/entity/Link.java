package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 链接
 */
public class Link implements Serializable {

    private static final long serialVersionUID = 2413333260069658101L;

    private Integer linkId; // 链接id
    private String linkUrl; // URL
    private String linkName; // 姓名
    private String linkImage; // 头像
    private String linkDescription; // 描述
    private String linkOwnerNickname;// 所属人名称
    private String linkOwnerContact; // 联系方式
    private Date linkUpdateTime; // 更新时间
    private Date linkCreateTime; // 创建时间
    private Integer linkOrder; // 排序值
    private Integer linkStatus; // 状态

    public Link() {
    }

    public Link(Integer linkId, String linkUrl, String linkName
            , String linkImage, String linkDescription, String linkOwnerNickname
            , String linkOwnerContact, Date linkUpdateTime, Date linkCreateTime, Integer linkOrder, Integer linkStatus) {
        this.linkId = linkId;
        this.linkUrl = linkUrl;
        this.linkName = linkName;
        this.linkImage = linkImage;
        this.linkDescription = linkDescription;
        this.linkOwnerNickname = linkOwnerNickname;
        this.linkOwnerContact = linkOwnerContact;
        this.linkUpdateTime = linkUpdateTime;
        this.linkCreateTime = linkCreateTime;
        this.linkOrder = linkOrder;
        this.linkStatus = linkStatus;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    public String getLinkOwnerNickname() {
        return linkOwnerNickname;
    }

    public void setLinkOwnerNickname(String linkOwnerNickname) {
        this.linkOwnerNickname = linkOwnerNickname;
    }

    public String getLinkOwnerContact() {
        return linkOwnerContact;
    }

    public void setLinkOwnerContact(String linkOwnerContact) {
        this.linkOwnerContact = linkOwnerContact;
    }

    public Date getLinkUpdateTime() {
        return linkUpdateTime;
    }

    public void setLinkUpdateTime(Date linkUpdateTime) {
        this.linkUpdateTime = linkUpdateTime;
    }

    public Date getLinkCreateTime() {
        return linkCreateTime;
    }

    public void setLinkCreateTime(Date linkCreateTime) {
        this.linkCreateTime = linkCreateTime;
    }

    public Integer getLinkOrder() {
        return linkOrder;
    }

    public void setLinkOrder(Integer linkOrder) {
        this.linkOrder = linkOrder;
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Link link = (Link) o;
        return getLinkId().equals(link.getLinkId()) && getLinkUrl().equals(link.getLinkUrl()) && getLinkName().equals(link.getLinkName()) && getLinkImage().equals(link.getLinkImage()) && getLinkDescription().equals(link.getLinkDescription()) && getLinkOwnerNickname().equals(link.getLinkOwnerNickname()) && getLinkOwnerContact().equals(link.getLinkOwnerContact()) && getLinkUpdateTime().equals(link.getLinkUpdateTime()) && getLinkCreateTime().equals(link.getLinkCreateTime()) && getLinkOrder().equals(link.getLinkOrder()) && getLinkStatus().equals(link.getLinkStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLinkId(), getLinkUrl(), getLinkName(), getLinkImage(), getLinkDescription(), getLinkOwnerNickname(), getLinkOwnerContact(), getLinkUpdateTime(), getLinkCreateTime(), getLinkOrder(), getLinkStatus());
    }

    @Override
    public String toString() {
        return "Link{" +
                "linkId=" + linkId +
                ", linkUrl='" + linkUrl + '\'' +
                ", linkName='" + linkName + '\'' +
                ", linkImage='" + linkImage + '\'' +
                ", linkDescription='" + linkDescription + '\'' +
                ", linkOwnerNickname='" + linkOwnerNickname + '\'' +
                ", linkOwnerContact='" + linkOwnerContact + '\'' +
                ", linkUpdateTime=" + linkUpdateTime +
                ", linkCreateTime=" + linkCreateTime +
                ", linkOrder=" + linkOrder +
                ", linkStatus=" + linkStatus +
                '}';
    }
}
