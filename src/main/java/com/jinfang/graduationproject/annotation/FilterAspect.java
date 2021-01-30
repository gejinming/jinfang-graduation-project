package com.jinfang.graduationproject.annotation;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FilterAspect {

    boolean filter() default true;
}
