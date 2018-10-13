package com.pm.japi.resolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldInfo {
    private String name;
    private Class clazz;
    private Field field;
    private Method method;

    public FieldInfo() {
    }

    public FieldInfo(String name, Class clazz, Method method) {
        this.name = name;
        this.clazz = clazz;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
