package com.vtkd.ssm.blog.enums;

/**
 * 页面 状态枚举
 *
 * @updateAuthor 君上
 * @date 2022-7-26
 */
public enum PageStatus {

    NORMAL(1,"显示"),
    HIDDEN(0,"隐藏");

    private Integer status;
    private String message;


    PageStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
