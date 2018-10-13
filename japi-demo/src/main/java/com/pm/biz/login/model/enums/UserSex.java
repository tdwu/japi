package com.pm.biz.login.model.enums;


import java.io.Serializable;

public enum  UserSex  {

    NO(0, "未知"),
    MAN(1, "男"),
    WOMAN(2, "女");

    private int value;
    private String desc;

    UserSex(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Serializable getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

}

