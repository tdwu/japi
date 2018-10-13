package com.pm.japi.model;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String name;
    public List<ApiInfo> apiList=new ArrayList<ApiInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApiInfo> getApiList() {
        return apiList;
    }

    public void setApiList(List<ApiInfo> apiList) {
        this.apiList = apiList;
    }
}
