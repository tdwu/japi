package com.pm.japi.resolver.rs;

import com.pm.japi.annotations.ApiNote;
import com.pm.japi.resolver.BaseResolver;
import com.pm.japi.resolver.ResolverType;
import com.pm.japi.resolver.TypeConfig;
import com.pm.japi.resolver.TypeInfo;
import com.pm.japi.sacnner.ModelProvider;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JavaBeanResolver implements BaseResolver {
    private TypeConfig config;

    public JavaBeanResolver(TypeConfig config) {
        this.config = config;

    }

    @Override
    public TypeInfo resolved(Type type, ModelProvider modelProvider, ResolverType lastNode) {
        ResolverType resolverType = new ResolverType(type);
        //创建信息
        TypeInfo typeInfo = new TypeInfo(type.getTypeName(), null);
        modelProvider.addTypeInfo(typeInfo);

        //处理filed(java Bean)
        resolverType.getTypeField().forEach(fieldInfo -> {
            Method p = fieldInfo.getMethod();
            Type filedType = p.getGenericReturnType();
            TypeInfo pTypeInfo = null;
            if (filedType instanceof ParameterizedType) {
                ParameterizedType tt = (ParameterizedType) filedType;
                // 数据类里面使用了【泛型参数】：List<T> name;
                modelProvider.addType(filedType, resolverType);

                pTypeInfo = new TypeInfo(getMethodName(p), "$" + config.getClassName(tt, resolverType));
                typeInfo.getProperties().add(pTypeInfo);

            } else if (filedType instanceof TypeVariableImpl) {
                //数据类型是【泛型】的如：T name;
                Type pt = resolverType.getParam(filedType.getTypeName());
                if (pt == null) {
                    //泛型没有指定，则上线是Object
                    modelProvider.addType(Object.class, null);
                    pTypeInfo = new TypeInfo(getMethodName(p), Object.class.getName());
                    typeInfo.getProperties().add(pTypeInfo);
                } else {
                    //有指定
                    modelProvider.addType(pt, null);
                    pTypeInfo = new TypeInfo(getMethodName(p), "$" + pt.getTypeName());
                    typeInfo.getProperties().add(pTypeInfo);
                }
            } else {
                modelProvider.addType(filedType, null);
                pTypeInfo = new TypeInfo(getMethodName(p), "$" + filedType.getTypeName());
                typeInfo.getProperties().add(pTypeInfo);
            }
            if (pTypeInfo != null && fieldInfo.getField() != null) {
                ApiNote apiNote = fieldInfo.getField().getAnnotation(ApiNote.class);
                if (apiNote != null) {
                    pTypeInfo.setNote(apiNote.value());
                }
            }

        });

        return typeInfo;
    }


    public String getMethodName(Method method) {
        String name = method.getName();
        return name.substring(3, 4).toLowerCase() + name.substring(4);
    }


}
