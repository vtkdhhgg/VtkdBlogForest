package com.vtkd.ssm.blog.enums;

/**
 * 菜单等级
 *
 * @author 君上
 * @date 2022-7-25
 */
public enum MenuLevel {
    TOP_MENU(1, "顶部菜单"),
    MAIN_MENU(2, "主体菜单");

    /**
     * 菜单 等级
     */
    private Integer value;

    /**
     * 菜单描述
     */
    private String message;

    MenuLevel(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
