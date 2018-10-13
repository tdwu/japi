package com.pm.biz.login.model;
import com.pm.japi.annotations.ApiNote;

import java.util.Date;

public class UserToken {
    private long id;
    @ApiNote("用户编号")
    private long userId;
    @ApiNote("账号编号,支持用户多编号，如微信，小程序，是同一人的情况")
    private long userAccountId;
    @ApiNote("唯一标识")
    private long tokenCode;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(long tokenCode) {
        this.tokenCode = tokenCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(long userAccountId) {
        this.userAccountId = userAccountId;
    }
}
