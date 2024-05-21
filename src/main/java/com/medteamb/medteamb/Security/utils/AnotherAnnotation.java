package mySecurityFramework.mySecurityFramework.user.utils;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnotherAnnotation {

    String text = "DOCTOR";
}