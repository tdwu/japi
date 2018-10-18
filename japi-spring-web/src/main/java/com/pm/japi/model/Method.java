package com.pm.japi.model;

import java.util.List;

public class Method {
    private String path;
    private String name;
    private String markDown;
    private String note;
    private String type;//方法名称,Post,Get 等
    private String paramType;
    private List<Param> paramList = null;//请求参数
    private String returnType;
    private List<Param> resultList = null;//对returnType的描述

    private int order;

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

    public String getMarkDown() {
        return markDown;
    }

    public void setMarkDown(String markDown) {
        this.markDown = markDown;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Method clone() {
        Method fMethod = new Method();
        fMethod.setType(this.getType());
        fMethod.setName(this.getName());
        fMethod.setNote(this.getNote());
        fMethod.setParamType(this.getParamType());
        fMethod.setParamList(this.getParamList());
        fMethod.setReturnType(this.getReturnType());
        fMethod.setResultList(this.getResultList());
        fMethod.setOrder(this.getOrder());
        fMethod.setMarkDown(this.getMarkDown());
        return fMethod;
    }

}
