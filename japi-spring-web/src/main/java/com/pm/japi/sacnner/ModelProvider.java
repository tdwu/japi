package com.pm.japi.sacnner;


import com.pm.japi.resolver.BaseResolver;
import com.pm.japi.resolver.ResolverType;
import com.pm.japi.resolver.TypeConfig;
import com.pm.japi.resolver.TypeInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ModelProvider {

    TypeConfig config = new TypeConfig();
    public Map<String, TypeInfo> typeMap = new HashMap<String, TypeInfo>();

    public ModelProvider() {
        this.initDefault();
    }

    public TypeInfo addType(Type type, ResolverType lastNode) {
        String className = config.getClassName(type, lastNode);
        TypeInfo typeInfo = typeMap.get(className);
        if (type != null && typeInfo == null) {
            BaseResolver resolver = config.getResolver(type);
            typeInfo = resolver.resolved(type, this, lastNode);
        }
        return typeInfo;
    }

    public void addTypeInfo(TypeInfo typeInfo) {
        typeMap.put(typeInfo.getName(), typeInfo);
    }


    public Map<String, TypeInfo> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, TypeInfo> typeMap) {
        this.typeMap = typeMap;
    }


    public String getMethodName(Method method) {
        String name = method.getName();
        return name.substring(3, 4).toLowerCase() + name.substring(4);
    }

    private void initDefault() {
        typeMap.put(Boolean.class.getTypeName(), new TypeInfo(Boolean.class.getTypeName(), "布尔型"));
        typeMap.put("byte", new TypeInfo("byte", "字节"));
        typeMap.put(Byte.class.getTypeName(), new TypeInfo(Byte.class.getTypeName(), "字节"));

        typeMap.put("short", new TypeInfo("short", "短整数"));
        typeMap.put(Short.class.getTypeName(), new TypeInfo(Short.class.getTypeName(), "短整数"));
        typeMap.put("int", new TypeInfo("int", "整数"));
        typeMap.put(Integer.class.getTypeName(), new TypeInfo(Integer.class.getTypeName(), "整数"));
        typeMap.put("long", new TypeInfo("long", "长整数"));
        typeMap.put(Long.class.getTypeName(), new TypeInfo(Long.class.getTypeName(), "长整数"));
        typeMap.put(BigInteger.class.getTypeName(), new TypeInfo(BigInteger.class.getTypeName(), "长整数"));
        typeMap.put(Number.class.getTypeName(), new TypeInfo(Number.class.getTypeName(), "数字"));

        typeMap.put("float", new TypeInfo("float", "小数"));
        typeMap.put(Float.class.getTypeName(), new TypeInfo(Float.class.getTypeName(), "小数"));
        typeMap.put("double", new TypeInfo("double", "小数"));
        typeMap.put(Double.class.getTypeName(), new TypeInfo(Double.class.getTypeName(), "小数"));
        typeMap.put("boolean", new TypeInfo("boolean", "小数"));
        typeMap.put(BigDecimal.class.getTypeName(), new TypeInfo(BigDecimal.class.getTypeName(), "高精度小数"));

        typeMap.put(String.class.getTypeName(), new TypeInfo(String.class.getTypeName(), "字符串"));
        typeMap.put(StringBuffer.class.getTypeName(), new TypeInfo(StringBuffer.class.getTypeName(), "字符串"));

        typeMap.put(java.util.Date.class.getTypeName(), new TypeInfo(java.util.Date.class.getTypeName(), "日期"));
        typeMap.put(java.sql.Date.class.getTypeName(), new TypeInfo(java.sql.Date.class.getTypeName(), "日期"));

        typeMap.put(Object.class.getTypeName(), new TypeInfo(Object.class.getTypeName(), "对象"));

    }
}
