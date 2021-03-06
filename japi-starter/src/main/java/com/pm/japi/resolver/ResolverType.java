package com.pm.japi.resolver;


import java.lang.reflect.*;
import java.util.*;

public class ResolverType {
    private Class clazz;
    private Type type;
    //泛型数据
    private TypeVariable[] paramNames;//泛型参数名称
    private Map<String, Integer> map = new HashMap<String, Integer>();//泛型名称对应的下标记
    private Type[] paramsList;//泛型实际的类型

    public ResolverType(Type type) {
        this.type = type;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            paramsList = parameterizedType.getActualTypeArguments();//获取实际泛型
            clazz = (Class) parameterizedType.getRawType();
        } else if (type instanceof Class) {
            clazz = (Class) type;
        }

        paramNames = this.clazz.getTypeParameters();//获取泛型参数名称
        for (int i = 0; i < this.paramNames.length; i++) {
            map.put(this.paramNames[i].getName(), i);
        }
    }

    public void setParams(ParameterizedType parameterizedType) {
        paramsList = parameterizedType.getActualTypeArguments();//获取实际泛型
    }

    private int getTypeIndex(String name) {
        return map.get(name);
    }

    public Type getParam(String name) {
        return paramsList != null ? paramsList[map.get(name)] : null;
    }

    public Type getParam(int index) {
        return paramsList[index];
    }


    public List<FieldInfo> getTypeField() {
        //public的方法，包括父类的
        List<Method> list = Arrays.asList(clazz.getMethods());

        List<FieldInfo> methodList = new ArrayList<FieldInfo>(list.size() / 2);
        list.forEach(p -> {
            String methodName = p.getName();
            if (methodName.startsWith("get")) {
                if (Modifier.isStatic(p.getModifiers())) {
                    return;
                }
                if (p.getReturnType().equals(Void.TYPE)) {
                    return;
                }
                if (p.getParameterTypes().length != 0) {
                    return;
                }
                if (p.getReturnType() == ClassLoader.class) {
                    return;
                }
                if (methodName.equals("getClass")) {
                    return;
                }
                if (methodName.equals("getDeclaringClass") && clazz.isEnum()) {
                    return;
                }
                if (methodName.length() < 4) {
                    return;
                }
                String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                FieldInfo fieldInfo = new FieldInfo(fieldName, clazz, p);

                Field field = getField(clazz, fieldName);
                fieldInfo.setField(field);


                methodList.add(fieldInfo);
            }
        });

        return methodList;
    }
    /***
     * getField
     * 获取一个类的 ==public成员变量，包括基类== 。
     *
     * getDeclaredField
     *
     * 获取一个类的 ==所有成员变量，不包括基类== 。
     */
    public static Field getField(Class clazz, String name) {
        Field field = null;
        try {
            //先获取自己的,所有,不包括parent的
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {

        }
        if (field == null) {
            try {
                //包括基类== ,包括public的
                field = clazz.getField(name);
            } catch (NoSuchFieldException e) {

            }
        }
        return field;
    }

}
