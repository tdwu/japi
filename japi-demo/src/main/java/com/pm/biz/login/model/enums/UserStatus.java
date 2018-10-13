package com.pm.biz.login.model.enums;


import java.io.Serializable;

public enum UserStatus{
    del(-2, "删除"),
    lock(-1, "禁用"),
    none(0, "待激活"),
    active(1, "正常");

    private int value;
    private String desc;

    UserStatus(final int value, final String desc) {
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
