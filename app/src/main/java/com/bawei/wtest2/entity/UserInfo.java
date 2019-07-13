package com.bawei.wtest2.entity;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:登录成功返回的信息
 */
public class UserInfo {

    private String userId;
    private String sessionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
