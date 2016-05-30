package com.tj.restaurant.annotation;

import java.lang.annotation.*;

/**
 * Created by user on 2016/4/29.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoPage {
}
