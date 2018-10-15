package com.pm.japi.resolver.rs;


import com.pm.japi.resolver.BaseResolver;
import com.pm.japi.resolver.ResolverType;
import com.pm.japi.resolver.TypeConfig;
import com.pm.japi.resolver.TypeInfo;
import com.pm.japi.sacnner.ModelProvider;

import java.lang.reflect.Type;

public class ArrayResolver implements BaseResolver {
    private TypeConfig config;

    public ArrayResolver(TypeConfig config) {
        this.config = config;

    }

    @Override
    public TypeInfo resolved(Type type, ModelProvider modelProvider, ResolverType lastNode) {
        String className = type.getTypeName().replace("[]", "");
        TypeInfo typeInfo = new TypeInfo(type.getTypeName(), "[]");
        typeInfo.setType("$" + className);
        modelProvider.addTypeInfo(typeInfo);

        try {
            //对象继续处理
            Class clazz = Class.forName(className);
            modelProvider.addType(clazz, lastNode);
        } catch (ClassNotFoundException e) {
        }

        return typeInfo;

    }
}
