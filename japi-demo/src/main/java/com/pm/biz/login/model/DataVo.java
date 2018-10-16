package com.pm.biz.login.model;

import java.util.List;

public class DataVo {
    private String name;
    private List<String> tags;
    private UserVo user;//如果是ajax提交的form格式，则会失败，除非text/plain

    private List<String> menuIds;//数组有3个，但没选中的index，会为null

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
