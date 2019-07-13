package com.bawei.wtest2.entity;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:用户信息
 */
public class UserEntity {

    private String userId;
    private String nickName;
    private String phone;
    private int sex;
    private String headPic;  //用户头像

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
