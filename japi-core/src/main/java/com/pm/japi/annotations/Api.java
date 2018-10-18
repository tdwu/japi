package com.pm.japi.annotations;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Api {
    String module() default "默认";//模块名称

    String value() default "";//【名称】

    String[] tags() default "";

    boolean hidden() default false;

    String markDown() default "";

    int order() default 0;
}
