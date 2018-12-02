package com.lease.domain;

import java.util.Date;

public class User {
    /**
     *  
     */
    private Integer userId;

    /**
     *  用户名
     */
    private String userName;

    /**
     *  密码
     */
    private String userPwd;

    /**
     *  
     */
    private Date createDate;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}