package com.pm.japi.resolver.rs;

import com.pm.japi.resolver.BaseResolver;
import com.pm.japi.resolver.ResolverType;
import com.pm.japi.resolver.TypeConfig;
import com.pm.japi.resolver.TypeInfo;
import com.pm.japi.sacnner.ModelProvider;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class EnumResolver implements BaseResolver {
    private TypeConfig config;

    public EnumResolver(TypeConfig config) {
        this.config = config;

    }

    @Override
    public TypeInfo resolved(Type type, ModelProvider modelProvider, ResolverType lastNode) {
        ResolverType resolverType = new ResolverType(type);
        //创建信息
        TypeInfo typeInfo = new TypeInfo(type.getTypeName(), null);
        modelProvider.addTypeInfo(typeInfo);
        typeInfo.setType("枚举");
        Class eClass = (Class) type;
        Object[] t = eClass.getEnumConstants();
        Method method = null;
        try {
            method = ((Class) type).getDeclaredMethod("getDesc");
        } catch (NoSuchMethodException e) {

        }
        String[] desList = new String[t.length];
        for (int i = 0; i < t.length; i++) {
            Enum e = (Enum) t[i];
            desList[i] = e.name();
            if (method != null) {
                try {
                    desList[i] = desList[i] + " : " + method.invoke(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        typeInfo.setNote(" [ "+ StringUtils.join(desList,";    ")+" ]");

        return typeInfo;
    }

}
