package com.pm.japi.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiMethod {

    String value() default "";//【名称】

    String note() default "";

    Class paramType() default ApiMethod.class;//默认一个类型，这个类型不可能作为一个真实的数据类型

    //请求参数
    ApiParam[] params() default @ApiParam();

    // Class returnType() default Object.class ;

    //返回参数
    ApiParam[] result() default @ApiParam();

    int order() default 0;


}
