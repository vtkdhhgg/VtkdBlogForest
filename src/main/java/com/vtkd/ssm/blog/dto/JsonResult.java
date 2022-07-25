package com.vtkd.ssm.blog.dto;

/**
 * 返回前端数据 标准类
 *
 * @param <T>
 *
 * @author 刘帮君
 * @date 2022/7/23
 */
public class JsonResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体信息
     */
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult<T> fail(){
        return new JsonResult<T>(1, "操作失败", null);
    }

    public JsonResult<T> fail(String msg){
        return new JsonResult<T>(1, msg, null);
    }
    public JsonResult<T> fail(String msg, T data){
        return new JsonResult<T>(1, msg, data);
    }

    public JsonResult<T> ok(){
        return ok("操作成功",null);
    }

    public JsonResult<T> ok(T data){
        return ok(null,data);
    }
    public JsonResult<T> ok(String msg){
        return ok(msg,null);
    }
    public JsonResult<T> ok(String msg, T data){
        return new JsonResult<T>(0, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
