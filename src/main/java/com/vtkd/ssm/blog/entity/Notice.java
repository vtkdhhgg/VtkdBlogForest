package com.vtkd.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 公告
 * @author 君上
 * @2022-7-25
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 6374611638611819541L;
    // 公告id
    private Integer noticeId;
    // 公告标题
    private String noticeTitle;
    // 公告内容
    private String noticeContent;
    // 创建时间
    private Date noticeCreateTime;
    // 更新时间
    private Date noticeUpdateTime;
    // 状态
    private Integer noticeStatus;
    // 排序值
    private Integer noticeOrder;

    public Notice() {
    }

    public Notice(Integer noticeId, String noticeTitle, String noticeContent, Date noticeCreateTime, Date noticeUpdateTime, Integer noticeStatus, Integer noticeOrder) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCreateTime = noticeCreateTime;
        this.noticeUpdateTime = noticeUpdateTime;
        this.noticeStatus = noticeStatus;
        this.noticeOrder = noticeOrder;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(Date noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public Date getNoticeUpdateTime() {
        return noticeUpdateTime;
    }

    public void setNoticeUpdateTime(Date noticeUpdateTime) {
        this.noticeUpdateTime = noticeUpdateTime;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getNoticeOrder() {
        return noticeOrder;
    }

    public void setNoticeOrder(Integer noticeOrder) {
        this.noticeOrder = noticeOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notice notice = (Notice) o;
        return getNoticeId().equals(notice.getNoticeId()) && getNoticeTitle().equals(notice.getNoticeTitle()) && getNoticeContent().equals(notice.getNoticeContent()) && getNoticeCreateTime().equals(notice.getNoticeCreateTime()) && getNoticeUpdateTime().equals(notice.getNoticeUpdateTime()) && getNoticeStatus().equals(notice.getNoticeStatus()) && getNoticeOrder().equals(notice.getNoticeOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoticeId(), getNoticeTitle(), getNoticeContent(), getNoticeCreateTime(), getNoticeUpdateTime(), getNoticeStatus(), getNoticeOrder());
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeCreateTime=" + noticeCreateTime +
                ", noticeUpdateTime=" + noticeUpdateTime +
                ", noticeStatus=" + noticeStatus +
                ", noticeOrder=" + noticeOrder +
                '}';
    }
}
