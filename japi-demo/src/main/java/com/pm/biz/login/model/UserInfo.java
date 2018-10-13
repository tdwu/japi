package com.pm.biz.login.model;


import com.pm.biz.login.model.enums.UserSex;
import com.pm.biz.login.model.enums.UserStatus;

import java.util.Date;

public class UserInfo {

    private long id = 0;
    private String name = "";
    private String phone = "";
    private String profilePhoto = "";
    private UserSex sex = UserSex.MAN;
    private UserStatus status = UserStatus.none;
    private Date createTime;//加入时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sex.getDesc();
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getStatusName() {
        return status.getDesc();
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
