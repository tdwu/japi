package com.pm.japi.model;


import com.pm.japi.resolver.TypeInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDocument implements Serializable {

    private  String groupName;
    private  String basePath;

    private List<Module> moduleList=new ArrayList<Module>();

    public Map<String, TypeInfo> defines = new HashMap<String, TypeInfo>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public Map<String, TypeInfo> getDefines() {
        return defines;
    }

    public void setDefines(Map<String, TypeInfo> defines) {
        this.defines = defines;
    }
}
