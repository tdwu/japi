package com.pm.japi.resolver;


import com.pm.japi.resolver.rs.ArrayResolver;
import com.pm.japi.resolver.rs.EnumResolver;
import com.pm.japi.resolver.rs.JavaBeanResolver;
import com.pm.japi.resolver.rs.ListResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeConfig {
    public Map<String, BaseResolver> cache = new HashMap<String, BaseResolver>();

    public BaseResolver getResolver(Type type) {
        if (cache.get(type.getTypeName()) == null) {
            cache.put(type.getTypeName(), createResolver(type));
        }
        return cache.get(type.getTypeName());
    }

    public BaseResolver createResolver(Type type) {
         if (type.getTypeName().startsWith(List.class.getName())) {
             //List
            return new ListResolver(this);
        } else if (type.getTypeName().endsWith("[]")) {
             //数组
            return new ArrayResolver(this);
        } else {
            if(type instanceof Class && Enum.class.isAssignableFrom((Class)type)){
                //枚举
                return new EnumResolver(this);
            }else {
                //Java Bean
                return new JavaBeanResolver(this);
            }
        }
    }


    public String getClassName(Type type, ResolverType lastResolver) {

        if (type instanceof TypeVariable) {
            // 数据类型是【泛型】的如：T name;
            //lastResolver不能为空

            //从引用处获取泛型的实际类型
            Type pt = lastResolver.getParam(type.getTypeName());
            if (pt == null) {
                //泛型没有指定，则上线是Object
                return Object.class.getName();
            } else {
                //有指定,则使用
                return pt.getTypeName();
            }
        } else if (type instanceof ParameterizedType) {
            // 类里面使用了【泛型参数】
            //如：List<T> name;     List<UserInfo>
            //lastResolver 不能为空
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            String str = "";
            for (int i = 0; i < types.length; i++) {
                if (i != 0) {
                    str = str + ",";
                }

                String className=types[i].getTypeName();
                try {
                    str = str + lastResolver.getParam(className).getTypeName();
                }catch (Exception ex){
                    str = str +className;
                }
            }
            String name = ((ParameterizedType) type).getRawType().getTypeName();
            name = name + "<" + str + ">";
            return name;

        } else {
            return type.getTypeName();
        }

    }
}
