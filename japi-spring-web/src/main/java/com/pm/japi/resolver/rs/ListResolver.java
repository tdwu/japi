package com.pm.japi.resolver.rs;


import com.pm.japi.resolver.BaseResolver;
import com.pm.japi.resolver.ResolverType;
import com.pm.japi.resolver.TypeConfig;
import com.pm.japi.resolver.TypeInfo;
import com.pm.japi.sacnner.ModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ListResolver implements BaseResolver {
    private TypeConfig config;

    public ListResolver(TypeConfig config){
        this.config=config;

    }

    @Override
    public TypeInfo resolved(Type type, ModelProvider modelProvider, ResolverType lastNode) {
        TypeInfo typeInfo=null;
        if(type instanceof ParameterizedType){

            typeInfo=new TypeInfo(config.getClassName(type,lastNode),"Array");
            modelProvider.addTypeInfo(typeInfo);
            //创建信息

            //无filed

            //处理list的泛型，默认采用lastNode，本身无ResolverType
            ParameterizedType pt=(ParameterizedType) type;
            Type rType=pt.getActualTypeArguments()[0];
            if(rType instanceof TypeVariable){
                //说明是List<T>
                Type clazz=lastNode.getParam(((TypeVariable) rType).getName());
                typeInfo.setType("$"+clazz.getTypeName());
                modelProvider.addType(clazz,lastNode);
            }else{
                //说明是List<UserInfo>
                typeInfo.setType("$"+rType.getTypeName());
                modelProvider.addType(rType,lastNode);
            }


        }else{
            //只是一个list
            typeInfo=new TypeInfo(type.getTypeName(),"Array");
            modelProvider.addTypeInfo(typeInfo);
        }
        return typeInfo;
    }
}
