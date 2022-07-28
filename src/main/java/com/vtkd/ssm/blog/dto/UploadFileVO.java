package com.vtkd.ssm.blog.dto;

/**
 * 文件信息
 *
 * @author 君上
 * @date 2022-7-28
 */
public class UploadFileVO {

    private String src;

    private String title;


    public UploadFileVO() {
    }

    public UploadFileVO(String src, String title) {
        this.src = src;
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UploadFileVO{" +
                "src='" + src + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
