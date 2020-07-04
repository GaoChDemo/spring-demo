package top.gaoch.annotation;

import java.lang.annotation.*;

/**
 * 这是自己的注解类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAutowired {
}
