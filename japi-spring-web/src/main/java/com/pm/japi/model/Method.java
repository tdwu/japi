package com.pm.japi.model;

import java.util.List;

public class Method {
    private String path;
    private String name;
    private String note;
    private String paramType;
    private List<Param> paramList=null;//请求参数
    private String returnType;
    private List<Param> resultList=null;//对returnType的描述

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public List<Param> getParamList() {
        return paramList;
    }

    public void setParamList(List<Param> paramList) {
        this.paramList = paramList;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<Param> getResultList() {
        return resultList;
    }

    public void setResultList(List<Param> resultList) {
        this.resultList = resultList;
    }
}
