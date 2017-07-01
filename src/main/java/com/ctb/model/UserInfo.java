package com.ctb.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**用户编号*/
    private Long userId;
    /**用户微信openid*/
    private String userOpenId;
    /**用户手机号*/
    private String userMobile;
    /**用户绑定状态*/
    private Integer bindStatus;
    /**绑定用户短信验证码*/
    private String decode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId == null ? null : userOpenId.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }
}