package com.pm.japi.model;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String name;
    private String markDown;
    public List<ApiInfo> apiList=new ArrayList<ApiInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarkDown() {
        return markDown;
    }

    public void setMarkDown(String markDown) {
        this.markDown = markDown;
    }

    public List<ApiInfo> getApiList() {
        return apiList;
    }

    public void setApiList(List<ApiInfo> apiList) {
        this.apiList = apiList;
    }
}
