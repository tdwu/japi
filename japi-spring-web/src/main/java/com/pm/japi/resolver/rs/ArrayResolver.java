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
        TypeInfo typeInfo = new TypeInfo(config.getClassName(type, lastNode), "Array");
        modelProvider.addTypeInfo(typeInfo);
        typeInfo.setType("$" + type.getTypeName().replace("[]", ""));

        return typeInfo;

    }
}
