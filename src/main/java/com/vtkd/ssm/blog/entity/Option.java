package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 一些关于站点的信息
 */
public class Option implements Serializable {


    private static final long serialVersionUID = 2052035664786767624L;

    // id
    private Integer optionId;

    // 站点标题
    private String optionSiteTitle;

    // 站点描述
    private String optionSiteDescrption;

    // 描述
    private String optionMetaDescrption;

    // 关键字
    private String optionMetaKeyword;

    // 关于站点头像
    private String optionAboutsiteAvatar;

    // 关于站点标题
    private String optionAboutsiteTitle;

    // 关于站点内容
    private String optionAboutsiteContent;

    // 关于站点 微信链接
    private String optionAboutsiteWechat;

    // 关于站点 qq链接
    private String optionAboutsiteQq;

    //关于站点 github链接
    private String optionAboutsiteGithub;

    //关于站点 微博链接
    private String optionAboutsiteWeibo;

    private String optionTongji;

    // 状态
    private Integer optionStatus;

    public Option() {
    }

    public Option(Integer optionId, String optionSiteTitle, String optionSiteDescrption, String optionMetaDescrption, String optionMetaKeyword, String optionAboutsiteAvatar, String optionAboutsiteTitle, String optionAboutsiteContent, String optionAboutsiteWechat
            , String optionAboutsiteQq, String optionAboutsiteGithub, String optionAboutsiteWeibo, String optionTongji, Integer optionStatus) {
        this.optionId = optionId;
        this.optionSiteTitle = optionSiteTitle;
        this.optionSiteDescrption = optionSiteDescrption;
        this.optionMetaDescrption = optionMetaDescrption;
        this.optionMetaKeyword = optionMetaKeyword;
        this.optionAboutsiteAvatar = optionAboutsiteAvatar;
        this.optionAboutsiteTitle = optionAboutsiteTitle;
        this.optionAboutsiteContent = optionAboutsiteContent;
        this.optionAboutsiteWechat = optionAboutsiteWechat;
        this.optionAboutsiteQq = optionAboutsiteQq;
        this.optionAboutsiteGithub = optionAboutsiteGithub;
        this.optionAboutsiteWeibo = optionAboutsiteWeibo;
        this.optionTongji = optionTongji;
        this.optionStatus = optionStatus;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionSiteTitle() {
        return optionSiteTitle;
    }

    public void setOptionSiteTitle(String optionSiteTitle) {
        this.optionSiteTitle = optionSiteTitle;
    }

    public String getOptionSiteDescrption() {
        return optionSiteDescrption;
    }

    public void setOptionSiteDescrption(String optionSiteDescrption) {
        this.optionSiteDescrption = optionSiteDescrption;
    }

    public String getOptionMetaDescrption() {
        return optionMetaDescrption;
    }

    public void setOptionMetaDescrption(String optionMetaDescrption) {
        this.optionMetaDescrption = optionMetaDescrption;
    }

    public String getOptionMetaKeyword() {
        return optionMetaKeyword;
    }

    public void setOptionMetaKeyword(String optionMetaKeyword) {
        this.optionMetaKeyword = optionMetaKeyword;
    }

    public String getOptionAboutsiteAvatar() {
        return optionAboutsiteAvatar;
    }

    public void setOptionAboutsiteAvatar(String optionAboutsiteAvatar) {
        this.optionAboutsiteAvatar = optionAboutsiteAvatar;
    }

    public String getOptionAboutsiteTitle() {
        return optionAboutsiteTitle;
    }

    public void setOptionAboutsiteTitle(String optionAboutsiteTitle) {
        this.optionAboutsiteTitle = optionAboutsiteTitle;
    }

    public String getOptionAboutsiteContent() {
        return optionAboutsiteContent;
    }

    public void setOptionAboutsiteContent(String optionAboutsiteContent) {
        this.optionAboutsiteContent = optionAboutsiteContent;
    }

    public String getOptionAboutsiteWechat() {
        return optionAboutsiteWechat;
    }

    public void setOptionAboutsiteWechat(String optionAboutsiteWechat) {
        this.optionAboutsiteWechat = optionAboutsiteWechat;
    }

    public String getOptionAboutsiteQq() {
        return optionAboutsiteQq;
    }

    public void setOptionAboutsiteQq(String optionAboutsiteQq) {
        this.optionAboutsiteQq = optionAboutsiteQq;
    }

    public String getOptionAboutsiteGithub() {
        return optionAboutsiteGithub;
    }

    public void setOptionAboutsiteGithub(String optionAboutsiteGithub) {
        this.optionAboutsiteGithub = optionAboutsiteGithub;
    }

    public String getOptionAboutsiteWeibo() {
        return optionAboutsiteWeibo;
    }

    public void setOptionAboutsiteWeibo(String optionAboutsiteWeibo) {
        this.optionAboutsiteWeibo = optionAboutsiteWeibo;
    }

    public String getOptionTongji() {
        return optionTongji;
    }

    public void setOptionTongji(String optionTongji) {
        this.optionTongji = optionTongji;
    }

    public Integer getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(Integer optionStatus) {
        this.optionStatus = optionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return getOptionId().equals(option.getOptionId()) && getOptionSiteTitle().equals(option.getOptionSiteTitle())
                && getOptionSiteDescrption().equals(option.getOptionSiteDescrption()) && getOptionMetaDescrption().equals(option.getOptionMetaDescrption()) && getOptionMetaKeyword().equals(option.getOptionMetaKeyword()) && getOptionAboutsiteAvatar().equals(option.getOptionAboutsiteAvatar()) && getOptionAboutsiteTitle().equals(option.getOptionAboutsiteTitle()) && getOptionAboutsiteContent().equals(option.getOptionAboutsiteContent()) && getOptionAboutsiteWechat().equals(option.getOptionAboutsiteWechat()) && getOptionAboutsiteQq().equals(option.getOptionAboutsiteQq()) && getOptionAboutsiteGithub().equals(option.getOptionAboutsiteGithub()) && getOptionAboutsiteWeibo().equals(option.getOptionAboutsiteWeibo()) && getOptionTongji().equals(option.getOptionTongji()) && getOptionStatus().equals(option.getOptionStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOptionId(), getOptionSiteTitle(), getOptionSiteDescrption(), getOptionMetaDescrption(), getOptionMetaKeyword(), getOptionAboutsiteAvatar(), getOptionAboutsiteTitle(), getOptionAboutsiteContent(), getOptionAboutsiteWechat(), getOptionAboutsiteQq(), getOptionAboutsiteGithub(), getOptionAboutsiteWeibo(), getOptionTongji(), getOptionStatus());
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", optionSiteTitle='" + optionSiteTitle + '\'' +
                ", optionSiteDescrption='" + optionSiteDescrption + '\'' +
                ", optionMetaDescrption='" + optionMetaDescrption + '\'' +
                ", optionMetaKeyword='" + optionMetaKeyword + '\'' +
                ", optionAboutsiteAvatar='" + optionAboutsiteAvatar + '\'' +
                ", optionAboutsiteTitle='" + optionAboutsiteTitle + '\'' +
                ", optionAboutsiteContent='" + optionAboutsiteContent + '\'' +
                ", optionAboutsiteWechat='" + optionAboutsiteWechat + '\'' +
                ", optionAboutsiteQq='" + optionAboutsiteQq + '\'' +
                ", optionAboutsiteGithub='" + optionAboutsiteGithub + '\'' +
                ", optionAboutsiteWeibo='" + optionAboutsiteWeibo + '\'' +
                ", optionTongji='" + optionTongji + '\'' +
                ", optionStatus=" + optionStatus +
                '}';
    }
}
