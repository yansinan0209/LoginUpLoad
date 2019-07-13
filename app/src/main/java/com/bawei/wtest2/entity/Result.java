package com.bawei.wtest2.entity;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:数据接口的总层次（最外层数据）
 */
public class Result<T> {

    /*private String message = "请求失败";
    private String status = "-1";
    private T result;
    private String headPath;*/
    private String message = "请求失败!";
    private String status = "-1";
    private T result;
    private String headPath;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
