package com.pm.japi.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiParam {
    String value() default "";// 【参数的名称】 如：编号，id，这时value为id
    Class type() default String.class;//默认字符串类型
    String title() default "";//显示的名称
    String note() default "";//描述
    From from() default From.body;

}
