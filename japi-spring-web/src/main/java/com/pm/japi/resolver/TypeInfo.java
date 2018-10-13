package com.pm.japi.resolver;

import java.util.ArrayList;
import java.util.List;

public class TypeInfo {
    private String name;
    private String type;//通过一个类型来进行描述，使用场景list《User》类型，这个地方存放的就是User类型名称，其他情况应通过属性来描述
    private String note;
    public List<TypeInfo> properties=new ArrayList<TypeInfo>();//通过多个属性来描述，一般与type进行二选1，优先使用属性描述

    public TypeInfo(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TypeInfo> getProperties() {
        return properties;
    }

    public void setProperties(List<TypeInfo> properties) {
        this.properties = properties;
    }
}
