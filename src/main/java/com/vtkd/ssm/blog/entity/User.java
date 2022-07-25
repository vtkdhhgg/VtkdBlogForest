package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 用户
 */
public class User implements Serializable {


    private static final long serialVersionUID = -6825237555983418142L;


    private Integer userId;

    private String userName;

    // 密码
    private String userPass;

    // 昵称
    private String userNickname;

    // 用户邮箱
    private String userEmail;

    // 个人主页
    private String userUrl;

    // 头像
    private String userAvatar;

    // 上次登录ip
    private String userLastLoginIp;

    // 注册时间
    private Date userRegisterTime;

    // 上次登陆时间
    private Date userLastLoginTime;

    // 状态
    private Integer userStatus;

    // 角色
    private String userRole;

    /**
     * 文章数量（不是数据库字段）
     */
    private Integer articleCount;

    public User() {
    }

    public User(Integer userId, String userName, String userPass, String userNickname, String userEmail, String userUrl, String userAvatar, String userLastLoginIp, Date userRegisterTime, Date userLastLoginTime, Integer userStatus, String userRole, Integer articleCount) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userUrl = userUrl;
        this.userAvatar = userAvatar;
        this.userLastLoginIp = userLastLoginIp;
        this.userRegisterTime = userRegisterTime;
        this.userLastLoginTime = userLastLoginTime;
        this.userStatus = userStatus;
        this.userRole = userRole;
        this.articleCount = articleCount;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public void setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getUserId().equals(user.getUserId()) && getUserName().equals(user.getUserName()) && getUserPass().equals(user.getUserPass()) && getUserNickname().equals(user.getUserNickname()) && getUserEmail().equals(user.getUserEmail()) && getUserUrl().equals(user.getUserUrl()) && getUserAvatar().equals(user.getUserAvatar()) && getUserLastLoginIp().equals(user.getUserLastLoginIp()) && getUserRegisterTime().equals(user.getUserRegisterTime()) && getUserLastLoginTime().equals(user.getUserLastLoginTime()) && getUserStatus().equals(user.getUserStatus()) && getUserRole().equals(user.getUserRole()) && getArticleCount().equals(user.getArticleCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getUserPass(), getUserNickname(), getUserEmail(), getUserUrl(), getUserAvatar(), getUserLastLoginIp(), getUserRegisterTime(), getUserLastLoginTime(), getUserStatus(), getUserRole(), getArticleCount());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userLastLoginIp='" + userLastLoginIp + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userLastLoginTime=" + userLastLoginTime +
                ", userStatus=" + userStatus +
                ", userRole='" + userRole + '\'' +
                ", articleCount=" + articleCount +
                '}';
    }
}
